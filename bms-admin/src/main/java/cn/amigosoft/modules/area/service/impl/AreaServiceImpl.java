package cn.amigosoft.modules.area.service.impl;

import cn.amigosoft.common.constant.Constant;
import cn.amigosoft.common.exception.ErrorCode;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.common.utils.TreeUtils;
import cn.amigosoft.modules.area.common.FacilityType;
import cn.amigosoft.modules.area.common.NumberToCN;
import cn.amigosoft.modules.area.dao.AreaDao;
import cn.amigosoft.modules.area.dto.AreaBriefInfoDTO;
import cn.amigosoft.modules.area.dto.AreaDTO;
import cn.amigosoft.modules.area.dto.AreaTreeData;
import cn.amigosoft.modules.area.dto.TreeData;
import cn.amigosoft.modules.area.entity.AreaEntity;
import cn.amigosoft.modules.area.entity.AreaInfoEntity;
import cn.amigosoft.modules.area.excel.AreaExcel;
import cn.amigosoft.modules.area.service.AreaInfoService;
import cn.amigosoft.modules.area.service.AreaService;
import cn.amigosoft.modules.person.entity.PersonEntity;
import cn.amigosoft.modules.person.service.PersonService;
import cn.amigosoft.modules.security.user.SecurityUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设施信息表
 *
 * @author ffcs ffcs@ffcs.cn
 * @since 1.0.0 2020-07-08
 */
@Slf4j
@Service
public class AreaServiceImpl extends CrudServiceImpl<AreaDao, AreaEntity, AreaDTO> implements AreaService {
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private PersonService personService;

    @Override
    public PageData<AreaBriefInfoDTO> query(Map<String, Object> params) {
        IPage<AreaEntity> iPage = getPage(params, null, false);
        List<AreaBriefInfoDTO> list = baseDao.query(params);
        return getPageData(list, iPage.getTotal(), AreaBriefInfoDTO.class);
    }

    /**
     * @param pid      pid
     * @param identity 是否包含本身节点，默认true
     * @param areaType
     * @Describe: 按照树形查询设施信息
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/10 14:59
     */
    @Override
    public List<TreeData> queryTreeData(Long pid, boolean identity, String areaType) {
        List<TreeData> list = this.queryAllTreeData(pid, identity);
        // 查询数据为空或者不限制树形二级类型时，直接返回
        if (list.size() == 0 || list.get(0) == null || StringUtil.isBlank(areaType)) {
            return list;
        }

        List<TreeData> children;
        if (identity) {
            children = list.get(0).getChildren();
            // 提取符合条件的树形二级类型
            List<TreeData> newChildren = new ArrayList<>();
            for (TreeData treeData : children) {
                if (areaType.equals(treeData.getType())) {
                    newChildren.add(treeData);
                }
            }
            // 重新设置数据
            list.get(0).setChildren(newChildren);
        } else {
            children = list;
            // 提取符合条件的树形二级类型
            List<TreeData> newChildren = new ArrayList<>();
            for (TreeData treeData : children) {
                if (areaType.equals(treeData.getType())) {
                    newChildren.add(treeData);
                }
            }
            // 重新设置数据
            list = newChildren;
        }
        return list;
    }

    /**
     * @param pid pid
     * @Describe: 按照list查询设施信息
     * @Required: pid
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/7/15 16:53
     */
    @Override
    public List<TreeData> queryBuildingFloorRoomData(Long pid, int facilityType) {
        LambdaQueryWrapper<AreaEntity> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper.eq(AreaEntity::getAreaType, facilityType);
        queryWrapper.eq(AreaEntity::getPid, pid);
        queryWrapper.eq(AreaEntity::getDel, Constant.AreaIsDel.VALID);
        // 排序，(area_name - 0 )作用在于将房间名102、103、104等转为数字类型后进行排序
        queryWrapper.last(" ORDER BY FIELD(area_type,30000,30100,30110), pid, ( area_name - 0 )");

        List<AreaEntity> areaEntityList = baseDao.selectList(queryWrapper);

        List<TreeData> resultList = new ArrayList<>();
        for (AreaEntity areaEntity : areaEntityList) {
            TreeData treeData = new TreeData();
            treeData.setId(areaEntity.getId());
            treeData.setType(areaEntity.getAreaType().toString());
            if (areaEntity.getAreaType().equals(FacilityType.BUILDING_FLOOR)) {
                try {
                    // 楼层为数字，将数字转为汉字
                    treeData.setName("第" + NumberToCN.format(Long.parseLong(areaEntity.getAreaName())) + "层");
                } catch (Exception e) {
                    // 转换失败，使用原名称
                    treeData.setName(areaEntity.getAreaName());
                }
            } else {
                treeData.setName(areaEntity.getAreaName());
            }
            treeData.setChildren(null);
            resultList.add(treeData);
        }
        return resultList;
    }

    @Override
    public List<TreeData> queryFacilityData(long areaId) {
        LambdaQueryWrapper<AreaEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(AreaEntity::getId, AreaEntity::getAreaType, AreaEntity::getAreaName);
        // 查询条件
        queryWrapper.eq(AreaEntity::getDel, Constant.AreaIsDel.VALID);
        queryWrapper.eq(AreaEntity::getPid, areaId);
        // 排序，(area_name - 0 )作用在于将房间名102、103、104等转为数字类型后进行排序
        queryWrapper.last(" ORDER BY FIELD(area_type,30000,30100,30110), pid, ( area_name - 0 )");

        List<AreaEntity> areaEntityList = baseDao.selectListByPidAndType(areaId);

        List<TreeData> resultList = new ArrayList<>();
        for (AreaEntity areaEntity : areaEntityList) {
            TreeData treeData = new TreeData();
            treeData.setId(areaEntity.getId());
            treeData.setType(areaEntity.getAreaType().toString());
            treeData.setName(areaEntity.getAreaName());
            treeData.setChildren(null);
            resultList.add(treeData);
        }
        return resultList;
    }

    @Override
    public List<AreaTreeData> queryAllByType(String typeStr, Integer del, Long pid, Integer type, String senceos) {
        List<AreaTreeData> resultList = new ArrayList<>();
        // 是否查询 pid 所在节点信息, 0 为根节点的 pid, 无实际数据,所以需要跳过查询 id 为 0 的节点数据
        if (pid != 0) {
            AreaTreeData areaNode = new AreaTreeData();
            AreaEntity areaEntity = areaService.selectById(pid);
            if (areaEntity == null) {
                return resultList;
            }
            areaNode.setId(areaEntity.getId());
            if (areaEntity.getAreaType() == FacilityType.BUILDING_FLOOR) {
                try {
                    // 楼层的getAreaName为数字，将数字汉字
                    areaNode.setName("第" + NumberToCN.format(Long.parseLong(areaEntity.getAreaName())) + "层");
                } catch (Exception e) {
                    log.error("楼层转化失败！", e);
                    // 转换失败，使用原名称
                    areaNode.setName(areaEntity.getAreaName());
                }
            } else {
                areaNode.setName(areaEntity.getAreaName());
            }
            areaNode.setType(String.valueOf(areaEntity.getAreaType()));

            resultList.add(areaNode);
        }

        // 查询出所有待处理的数据
        List<AreaTreeData> allList = this.baseDao.queryAllByType(typeStr, del);
        if (!StringUtil.isEmpty(senceos)) {
        /*    for (AreaTreeData areaTreeData : allList) {
                System.out.println(areaTreeData.getId() + "type.equals(areaTreeData.getType().trim()):       " + type.equals(areaTreeData.getType().trim()));
                System.out.println(areaTreeData.getId() + "!senceos.equals(areaTreeData.getScenes()):           " + !senceos.equals(areaTreeData.getScenes()));

                if (type.equals(areaTreeData.getType().trim()) && StringUtil.isEmpty(areaTreeData.getScenes()) || !senceos.equals(areaTreeData.getScenes())) {
                    allList.remove(areaTreeData);
                }
            }*/
            //将不是指定筛选的区域剔除
            for (int i = 0; i < allList.size(); i++) {
                if (type.equals(Integer.valueOf(allList.get(i).getType())) && !StringUtil.isEmpty(type)) {
                    if (!senceos.equals(allList.get(i).getScenes()) || StringUtil.isEmpty(allList.get(i).getScenes())) {
                        allList.remove(i);
                        i--;
                    }

                }
            }
        }


        // 将数据转成树形结构
        List<AreaTreeData> childrenNode = getAreaChildrenNode(pid, allList);

        // 装填数据
        resultList.get(0).setChildren(childrenNode);

        return resultList;
    }

    private List<AreaTreeData> getAreaChildrenNode(Long pid, List<AreaTreeData> allList) {
        List<AreaTreeData> childrenList = new ArrayList<>();

        // 获取迭代器
        Iterator<AreaTreeData> it = allList.iterator();
        while (it.hasNext()) {
            AreaTreeData treeData = it.next();

           /* if (!StringUtil.isEmpty(type)) {
                if (treeData.getType().equals(type) && !senceos.equals(treeData.getScenes())) {
                    it.remove();
                    continue;
                }
            }*/


            if (pid.equals(treeData.getPid())) {
                // 每个节点需要id、title、type、children三种信息
                AreaTreeData td = new AreaTreeData();
                td.setId(treeData.getId());
                if (treeData.getType().equals(String.valueOf(FacilityType.BUILDING_FLOOR))) {
                    try {
                        // 楼层为数字，将数字转为汉字
                        td.setName("第" + NumberToCN.format(Long.parseLong(treeData.getName())) + "层");
                    } catch (Exception e) {
                        log.error("楼层转换失败", e);
                        // 转换失败，使用原名称
                        td.setName(treeData.getName());
                    }
                } else {
                    td.setName(treeData.getName());
                }
                td.setType(treeData.getType());
                //加入子节点
                childrenList.add(td);

                // 删除当前节点，减轻递归遍历压力
                it.remove();
            }
        }

        childrenList.forEach((treeData) -> treeData.setChildren(getAreaChildrenNode(treeData.getId(), allList)));
        return childrenList;
    }

    /**
     * @Describe: 查询区域表area一整颗树形结构数据
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/8/1 17:30
     */
    private List<TreeData> queryAllTreeData(Long pid, boolean identity) {
        List<TreeData> resultList = new ArrayList<>();
        // 是否查询 pid 所在节点信息, 0 为根节点的 pid, 无实际数据,所以需要跳过查询 id 为 0 的节点数据
        if (identity && pid != 0) {
            TreeData areaNode = new TreeData();
            AreaEntity areaEntity = areaService.selectById(pid);
            if (areaEntity == null) {
                return resultList;
            }
            areaNode.setId(areaEntity.getId());
            if (areaEntity.getAreaType() == FacilityType.BUILDING_FLOOR) {
                try {
                    // 楼层的getAreaName为数字，将数字汉字
                    areaNode.setName("第" + NumberToCN.format(Long.parseLong(areaEntity.getAreaName())) + "层");
                } catch (Exception e) {
                    log.error("楼层转化失败！", e);
                    // 转换失败，使用原名称
                    areaNode.setName(areaEntity.getAreaName());
                }
            } else {
                areaNode.setName(areaEntity.getAreaName());
            }
            areaNode.setType(String.valueOf(areaEntity.getAreaType()));

            resultList.add(areaNode);
        }

        // 查询出所有待处理的数据
        List<TreeData> allList = this.baseDao.queryFacilityData();
        // 将数据转成树形结构
        List<TreeData> childrenNode = getChildrenNode(pid, allList);
        // 装填数据
        if (identity) {
            resultList.get(0).setChildren(childrenNode);
        } else {
            resultList = childrenNode;
        }

        return resultList;
    }

    /***
     * @Describe: 将list转成树形结构
     * @Author: ChenXiong
     * @Date: 2020/7/10 15:33
     */
    private List<TreeData> getChildrenNode(Long pid, List<TreeData> allList) {
        List<TreeData> childrenList = new ArrayList<>();

        // 获取迭代器
        Iterator<TreeData> it = allList.iterator();
        while (it.hasNext()) {
            TreeData treeData = it.next();
            if (pid.equals(treeData.getPid())) {
                // 每个节点需要id、title、type、children三种信息
                TreeData td = new TreeData();
                td.setId(treeData.getId());
                if (treeData.getType().equals(String.valueOf(FacilityType.BUILDING_FLOOR))) {
                    try {
                        // 楼层为数字，将数字转为汉字
                        td.setName("第" + NumberToCN.format(Long.parseLong(treeData.getName())) + "层");
                    } catch (Exception e) {
                        log.error("楼层转换失败", e);
                        // 转换失败，使用原名称
                        td.setName(treeData.getName());
                    }
                } else {
                    td.setName(treeData.getName());
                }
                td.setType(treeData.getType());
                //加入子节点
                childrenList.add(td);

                // 删除当前节点，减轻递归遍历压力
                it.remove();
            }
        }

        childrenList.forEach((treeData) -> treeData.setChildren(getChildrenNode(treeData.getId(), allList)));
        return childrenList;
    }


    @Override
    public List<AreaExcel> importExcel(List<AreaExcel> excelList) {
        List<AreaExcel> errorList = new ArrayList<>();
        log.info("开始处理excel数据");
        for (AreaExcel areaExcel : excelList) {
            String errMsg;

            try {
                // 检测当前行数据是否有缺失/异常
                if (!this.checkRowEffective(areaExcel)) {
                    throw new Exception("无效数据!(楼层请用数字表示例:第一层则填1 第二层则填2,且楼层最大支持1000层)");
                }

                if (areaExcel.getRoomName().length() > 10) {
                    throw new Exception("房间名最大支持10位");
                }

                // 每行数据中的社区名称
                String areaName = areaExcel.getAreaName();
                // 每行数据中的楼栋名称
                String buildingName = areaExcel.getBuildingName();
                // 每行数据中的楼层层数
                String floorNumber = areaExcel.getFloorNumber();
                // 每行数据中的房间名称
                String roomName = areaExcel.getRoomName();

                Long areaId;
                String areaNames;

                // 社区信息添加逻辑 ----------------------------------------------------------------------
                if (StringUtil.isBlank(areaName)) {
                    throw new Exception("社区名称不能为空!");
                } else {
                    if (areaName.length() > 128) {
                        throw new Exception("社区名称数据过大!");
                    }
                    // 检测当前用户是否已加入社区
                    LambdaQueryWrapper<AreaEntity> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.select(AreaEntity::getAreaName);
                    queryWrapper.eq(AreaEntity::getAreaType, FacilityType.AREA);
                    queryWrapper.eq(AreaEntity::getDel, Constant.AreaIsDel.VALID);
                    List<AreaEntity> list = areaService.selectList(queryWrapper);
                    if (list.size() > 1) {
                        throw new Exception("当前用户存在多个社区id，请联系管理员删除！");
                    } else if (list.size() == 1) {
                        String existAreaName = list.get(0).getAreaName();
                        if (!areaName.equals(existAreaName)) {
                            throw new Exception("当前用户已存在社区：" + existAreaName + "，无法导入其他社区信息！");
                        }
                    }
                }
                areaNames = areaName;
                // 根据 areaNames 名称查询节点信息, areaNames 由名称拼接成,例如：天翼智慧社区,停车场一,停车场一停车位1
                List<AreaDTO> list = getByAreaNames(areaNames);
                if (list.size() > 1) {
                    throw new Exception("找不到唯一 " + areaNames + " 的值");
                } else if (list.size() == 0) {
                    // 社区不存在，添加
                    Result<Object> insertResult = insertOnly(areaName, FacilityType.AREA, 0L);
                    if (insertResult.getCode() == 0) {
                        // 添加成功 保存社区areaId
                        areaId = ((AreaEntity) insertResult.getData()).getId();
                    } else {
                        // 添加失败，将错误信息当做异常抛出终止此条数据后续操作
                        throw new Exception(insertResult.getMsg());
                    }
                } else {
                    if (StringUtil.isBlank(buildingName)) {
                        // 社区存在，楼栋为空，重复数据
                        throw new Exception("社区已存在!");
                    } else {
                        if (buildingName.length() > 128) {
                            throw new Exception("楼栋名称数据过大");
                        }
                        // 保存查询到的社区id
                        areaId = list.get(0).getId();
                    }
                }

                // 楼栋信息添加逻辑 ----------------------------------------------------------------------
                if (StringUtil.isBlank(buildingName)) {
                    continue;
                }
                areaNames = areaName + "," + buildingName;
                list = getByAreaNames(areaNames);
                if (list.size() > 1) {
                    throw new Exception("找不到唯一 " + areaNames + " 的值");
                } else if (list.size() == 0) {
                    // 楼栋不存在，添加
                    Result<Object> insertResult = insertOnly(buildingName, FacilityType.BUILDING, areaId);
                    if (insertResult.getCode() == 0) {
                        // 添加成功 保存楼栋areaId
                        areaId = ((AreaEntity) insertResult.getData()).getId();
                    } else {
                        // 添加失败，将错误信息当做异常抛出终止此条数据后续操作
                        throw new Exception(insertResult.getMsg());
                    }
                } else {
                    if (floorNumber == null) {
                        throw new Exception("楼栋已存在!");
                    } else {
                        if (floorNumber.length() > 128) {
                            throw new Exception("楼栋数据值过大!");
                        }
                        // 保存查询到的楼栋id
                        areaId = list.get(0).getId();
                    }
                }

                // 楼层信息添加逻辑 ----------------------------------------------------------------------
                if (floorNumber == null) {
                    continue;
                }
                areaNames = areaName + "," + buildingName + "," + floorNumber;
                list = getByAreaNames(areaNames);
                if (list.size() > 1) {
                    throw new Exception("找不到唯一 " + areaNames + " 的值");
                } else if (list.size() == 0) {
                    //楼层不存在，添加
                    Result<Object> insertResult = insertOnly(floorNumber, FacilityType.BUILDING_FLOOR, areaId);
                    if (insertResult.getCode() == 0) {
                        // 添加成功 保存楼层areaId
                        areaId = ((AreaEntity) insertResult.getData()).getId();
                    } else {
                        // 添加失败，将错误信息当做异常抛出终止此条数据后续操作
                        throw new Exception(insertResult.getMsg());
                    }
                } else {
                    if (StringUtil.isBlank(roomName)) {
                        throw new Exception("楼层已存在!");
                    } else {
                        if (roomName.length() > 128) {
                            throw new Exception("楼层数据量过大!");
                        }
                        // 保存查询到的楼层id
                        areaId = list.get(0).getId();
                    }
                }

                // 房间信息添加逻辑 ----------------------------------------------------------------------
                if (StringUtil.isBlank(roomName)) {
                    continue;
                }
                areaNames = areaName + "," + buildingName + "," + floorNumber + "," + roomName;
                list = getByAreaNames(areaNames);
                if (list.size() > 1) {
                    throw new Exception("找不到唯一 " + areaNames + " 的值");
                }
                if (list.size() == 0) {
                    //房间不存在，添加
                    Result<Object> insertResult = insertOnly(roomName, FacilityType.BUILDING_ROOM, areaId);
                    if (insertResult.getCode() != 0) {
                        // 添加失败，将错误信息当做异常抛出终止此条数据后续操作
                        throw new Exception(insertResult.getMsg());
                    }
                } else {
                    throw new Exception("房间已存在!");
                }


            } catch (Exception e) {
                errMsg = e.getMessage();
                log.error(String.valueOf(e));
                areaExcel.setErrorMsg(errMsg);
                errorList.add(areaExcel);
            }
        }
        log.info("处理完毕，" +
                "共 " + excelList.size() + " 条数据，" +
                "成功处理 " + (excelList.size() - errorList.size()) + " 条数据，" +
                "处理失败 " + errorList.size() + " 条数据。");
        log.info("\n======================================\n");
        errorList.forEach(System.out::println);
        log.info("\n======================================\n");

        return errorList;
    }

    /**
     * 检查当前行数据有效性
     */
    private boolean checkRowEffective(AreaExcel areaExcel) {
        String areaName = areaExcel.getAreaName();
        String buildingName = areaExcel.getBuildingName();
        Integer floorNumber;
        if (areaExcel.getFloorNumber() != null) {
            try {
                floorNumber = Integer.valueOf(areaExcel.getFloorNumber());
            } catch (Exception e) {
                log.error("楼层转换异常");
                return false;
            }
            if (floorNumber > 1000) {
                return false;
            }
        } else {
            floorNumber = null;
        }

        String roomName = areaExcel.getRoomName();
        int count = 0;
        if (StringUtil.isNotBlank(areaName)) {
            count += 1000;
        }
        if (StringUtil.isNotBlank(buildingName)) {
            count += 100;
        }
        if (floorNumber != null && floorNumber > 0) {
            count += 10;
        }
        if (StringUtil.isNotBlank(roomName)) {
            count += 1;
        }
        return count == 1000 || count == 1100 || count == 1110 || count == 1111;
    }

    @Override
    public Result<Object> insertOnly(String areaName, Integer areaType, Long pid) {
        AreaEntity newArea = new AreaEntity();
        newArea.setAreaName(areaName);
        newArea.setAreaType(areaType);
        newArea.setDel(Constant.AreaIsDel.VALID);

        if (areaType != FacilityType.AREA &&
                areaType != FacilityType.BUILDING &&
                areaType != FacilityType.BUILDING_FLOOR &&
                areaType != FacilityType.BUILDING_ROOM) {
            return new Result<>().error("当前仅限区域、楼栋、楼层、房屋添加！");
        }

        if (pid == 0) {
            if (areaType != FacilityType.AREA) {
                return new Result<>().error("添加的节点不是社区！");
            } else {
                newArea.setPid(0L);
                newArea.setPids("0");
            }
        } else {
            LambdaQueryWrapper<AreaEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(AreaEntity::getId, pid);
            queryWrapper.eq(AreaEntity::getDel, 0);

            AreaEntity areaEntity = baseDao.selectOne(queryWrapper);
            if (areaEntity == null) {
                return new Result<>().error("pid 所在节点不存在，确保该节点已添加并未删除！");
            } else {
                newArea.setPid(areaEntity.getId());
                newArea.setPids(areaEntity.getPids() + "," + areaEntity.getId());
            }
        }
        // 添加area表信息
        baseDao.insert(newArea);

        // 添加各自详细信息表
        if (areaType == FacilityType.AREA) {
            AreaInfoEntity areaInfoEntity = new AreaInfoEntity();
            areaInfoEntity.setAreaName(areaName);
            areaInfoEntity.setAreaId(newArea.getId());
            areaInfoEntity.setDel(Constant.AreaIsDel.VALID);
            areaInfoService.insert(areaInfoEntity);
        }

        return new Result<>().ok(newArea);
    }

    @Override
    public List<AreaEntity> selectList(LambdaQueryWrapper<AreaEntity> queryWrapper) {
        return baseDao.selectList(queryWrapper);
    }

    @Override
    public List<AreaDTO> getByAreaNames(String areaNames) {
        return baseDao.getByAreaNames(areaNames);
    }

    @Override
    public List<AreaDTO> getByAreaNamesByDeviceImport(String areaNames) {
        return baseDao.getByAreaNamesByDeviceImport(areaNames);
    }

    /**
     * @param areaId
     * @Describe: 查询一个area节点下包含的住户数量
     * @Required:
     * @Optional:
     * @Author: ChenXiong
     * @Date: 2020/8/7 15:34
     */
    @Override
    public Integer queryResidentCount(Long areaId) {
        LambdaQueryWrapper<AreaEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(AreaEntity::getId);
        queryWrapper.like(AreaEntity::getPids, areaId);
        List<AreaEntity> areaList = baseDao.selectList(queryWrapper);
        List<Long> ids = new ArrayList<>();
        ids.add(areaId);
        areaList.forEach((item) -> ids.add(item.getId()));

        LambdaQueryWrapper<PersonEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(PersonEntity::getAreaId, ids);
        lambdaQueryWrapper.eq(PersonEntity::getCategory, 1);
        lambdaQueryWrapper.eq(PersonEntity::getDel, Constant.AreaIsDel.VALID);
        return personService.selectCount(lambdaQueryWrapper);
    }

    /***
     * 查询当前用户社区id
     * @return
     */
    @Override
    public Result<Long> getAaeaIdByUser() {
        Result<Long> result = new Result<>();

        LambdaQueryWrapper<AreaEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AreaEntity::getAreaType, FacilityType.AREA);
        queryWrapper.eq(AreaEntity::getDel, 0);
        List<AreaEntity> list = areaService.selectList(queryWrapper);
        if (list == null || list.size() == 0 || list.get(0) == null || list.get(0).getId() == null) {
            log.error("找不到该用户对应的社区id！");
            return result.error(ErrorCode.AREA_ID_NOT_FOUNT_51101);
        } else if (list.size() > 1) {
            log.error("查询到该用户存在多个社区id，请排查！");
            return result.error(ErrorCode.AREA_ID_MULTPLE_51102);
        } else {
            return result.ok(list.get(0).getId());
        }
    }

    @Override
    public String getAddressByBuildingAreaId(Long areaId) {
        return baseDao.getAddressByBuildingAreaId(areaId);
    }

    @Override
    public QueryWrapper<AreaEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<AreaDTO> getTopArea() {
        List<AreaDTO> areaDTOList = baseDao.getTopArea();

        return areaDTOList;
    }

    @Override
    public String selectAreaNameById(Long areaId) {
        AreaEntity areaEntity = selectById(areaId);
        return areaEntity.getAreaName();
    }

    @Override
    public List<AreaTreeData> getBuildingAndFloor(List<Integer> areaType, Long buildingId) {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();
        wrapper.in("area_type", areaType);
        wrapper.eq("del", 0);
        wrapper.eq(Objects.nonNull(buildingId), "pid", buildingId);
        List<AreaEntity> areaEntities = selectList(wrapper);
        List<AreaTreeData> areaTreeDataList = new ArrayList<>();
        for (AreaEntity areaEntity : areaEntities) {
            AreaTreeData areaTreeData = new AreaTreeData();
            areaTreeData.setId(areaEntity.getId());
            areaTreeData.setPid(areaEntity.getPid());
            areaTreeData.setType(areaEntity.getAreaType().toString());
            if (Constant.AreaType.FLOOR.getValue() == areaEntity.getAreaType()) {
                areaTreeData.setName("第" + NumberToCN.format(Long.valueOf(areaEntity.getAreaName())) + "层");
            } else {
                areaTreeData.setName(areaEntity.getAreaName());
            }
            areaTreeDataList.add(areaTreeData);
        }
        return TreeUtils.build(areaTreeDataList);
    }

    @Override
    public List<Long> getFloorIdsByBuildingId(Long buildingId) {
        List<AreaEntity> areaEntities = selectList(new QueryWrapper<AreaEntity>()
                .eq("pid", buildingId)
                .eq("del", Constant.IotSaasDel.status_0)
                .select("id"));
        return CollectionUtils.isEmpty(areaEntities)
                ? Collections.emptyList() : areaEntities.stream().map(AreaEntity::getId).collect(Collectors.toList());
    }

    @Override
    public List<AreaEntity> getByAreaType(Integer areaType) {
        return baseDao.getByAreaType(areaType);
    }

    @Override
    public void updateScenesById(String scenes, Long areaId) {
        baseDao.updateScenesById(scenes, areaId);
    }

    @Override
    public List<AreaTreeData> getAreaTree(String areaType, Integer scenesIsBind) {

        List<AreaTreeData> areaTreeDataList = baseDao.getAreaTree(areaType, SecurityUser.getUser().getTenantId(), scenesIsBind);
        for (AreaTreeData areaTreeData : areaTreeDataList) {
            if (areaTreeData.getType().equals(String.valueOf(Constant.AreaType.FLOOR.getValue()))) {
                areaTreeData.setName("第" + NumberToCN.format(Long.parseLong(areaTreeData.getName())) + "层");
            }
        }

        return TreeUtils.build(areaTreeDataList);
    }

    @Override
    public AreaEntity getAreaInfo(Long areaId) {

        AreaEntity area = baseDao.selectById(areaId);
        return area;
    }

    @Override
    public void updateScenesByIds(String scenes, List<Long> areaIds, Long tenantId) {
        baseDao.updateScenesByIds(scenes, areaIds, tenantId);
    }

    /**
     * 根据ids查询区域列表
     *
     * @param dutyAreaIds
     * @return
     */
    @Override
    public List<AreaDTO> getAreaListByIds(List<Long> dutyAreaIds) {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del", Constant.DelStatus.NOT_DEL);
        wrapper.in("id", dutyAreaIds);
        List<AreaEntity> areaEntities = baseDao.selectList(wrapper);
        List<AreaDTO> areaDTOS = ConvertUtils.sourceToTarget(areaEntities, AreaDTO.class);
        return areaDTOS;
    }

}