package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.common.utils.ImgUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.dining.dao.DiningFoodLibDao;
import cn.amigosoft.modules.dining.dto.DiningFoodLibDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodLibPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodDailyMenuEntity;
import cn.amigosoft.modules.dining.entity.DiningFoodLibEntity;
import cn.amigosoft.modules.dining.excel.DiningFoodLibExcel;
import cn.amigosoft.modules.dining.service.DiningFoodDailyMenuService;
import cn.amigosoft.modules.dining.service.DiningFoodLibService;
import cn.amigosoft.modules.dining.service.DiningRoomService;
import cn.amigosoft.modules.oss.cloud.OSSFactory;
import cn.amigosoft.modules.sys.dto.SysDictDTO;
import cn.amigosoft.modules.sys.service.SysDictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  菜品库表 服务实现类
 * </p>
 *
 * @author : hupihshi
 * @version : 1.0
 * @date : 2021-04-20 14:31:04
 */
@Service
public class DiningFoodLibServiceImpl extends CrudServiceImpl<DiningFoodLibDao, DiningFoodLibEntity, DiningFoodLibDTO> implements DiningFoodLibService {

    @Autowired
    private SysDictService dictService;

    @Autowired
    private DiningFoodLibService diningFoodLibService;

    @Autowired
    private DiningFoodDailyMenuService diningFoodDailyMenuService;

    @Autowired
    private DiningRoomService diningRoomService;

    @Override
    public QueryWrapper<DiningFoodLibEntity> getWrapper(Map<String, Object> params){
        
        String libNo = (String)params.get("libNo");
        String name = (String)params.get("name");
        QueryWrapper<DiningFoodLibEntity> wrapper = new QueryWrapper<>();
  
        wrapper.like(StringUtils.isNotBlank(libNo), "lib_no", libNo);
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        if(StringUtils.isNotBlank((String)params.get("orderFile"))){
            wrapper.orderByDesc((String)params.get("orderFile"));
        }else{
            //默认创建时间降序
            wrapper.orderByDesc("create_date");
        }

        wrapper.eq( "status", 2);
        wrapper.eq(StringUtils.isNotBlank((String)params.get("isEnduring")), "is_enduring", params.get("isEnduring"));
        wrapper.eq(StringUtils.isNotBlank((String)params.get("onlyName")), "name", params.get("onlyName"));
        wrapper.ge(StringUtils.isNotBlank((String)params.get("todayDateStr")),"create_date",params.get("todayDateStr"));
        wrapper.eq(!StringUtil.isEmpty(params.get("diningRoomId")),"dining_room_id",String.valueOf(params.get("diningRoomId")));
        wrapper.ne(StringUtils.isNotBlank((String)params.get("isNotHeightTea")),"type",params.get("isNotHeightTea"));
        wrapper.last(StringUtils.isNotBlank((String)params.get("top")),"limit 0,"+params.get("top"));
        return wrapper;
    }

    @Override
    public PageData<DiningFoodLibPageDTO> getDiningFoodLibPage(Map<String, Object> params){
        Page page = (Page) this.getPage(params, "a.create_date", false);

        String libNo = (String)params.get("libNo");
        String name = (String)params.get("name");
        QueryWrapper<DiningFoodLibDTO> wrapper = new QueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(libNo), "a.lib_no", libNo);
        wrapper.like(StringUtils.isNotBlank(name), "a.name", name);

        wrapper.eq( "a.status", 2);
        wrapper.eq(StringUtils.isNotBlank((String)params.get("diningRoomId")),"a.dining_room_id",params.get("diningRoomId"));

        PageData<DiningFoodLibPageDTO> pageData = getPageData(baseDao.queryDiningFoodLibList(page, wrapper), page.getTotal(), DiningFoodLibPageDTO.class);
        return pageData;
    }


    //每日菜单-添加餐品列表
    @Override
    public PageData<DiningFoodLibDTO> addDailyMenuList(Map<String, Object> params) {
        if ( Objects.isNull(params.get("date")) ) {
            throw new RenException("日期不能为空");
        }
        if ( ObjectUtils.isNull(params.get("diningType")) ){
            throw new RenException("菜品时段不能为空");
        }
        if ( !("1".equals(params.get("diningType")))  &&  !("2".equals(params.get("diningType"))) && !("3".equals(params.get("diningType"))) ){
            throw new RenException("菜品时段错误");
        }
        if ( Objects.isNull(params.get("diningRoomId")) ) {
            throw new RenException("餐厅不能为空");
        }
        //获取当前日期、菜品时段的菜单列表
        List<DiningFoodDailyMenuEntity> menuList = diningFoodDailyMenuService.getTodayMenuList(params);
        //提取菜单列表中的dining_food_lib_id
        List ids = menuList.stream().map(DiningFoodDailyMenuEntity::getDiningFoodLibId).collect(Collectors.toList());
        if ( ids.size() > 0 ){
            params.put("ids",ids);
        }
        //模糊搜索
        paramsToLike(params, "name");
        //分页
        IPage<DiningFoodLibEntity> page = getPage(params, "create_date", false);

        List<DiningFoodLibDTO> list = baseDao.addDailyMenuLibList(page,params);

        return getPageData(list, page.getTotal(), DiningFoodLibDTO.class);
    }

    /**
     * 自动生成
     * @param dto
     * @return
     */
    @Override
    public DiningFoodLibDTO getFoodLibNo(DiningFoodLibDTO dto){
        String TimeNow = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

        Map<String, Object> params = new HashMap<>();
        params.put("libNo",TimeNow);
        params.put("orderFile","lib_no");
        params.put("top","1");
        QueryWrapper wrapper =  this.getWrapper(params);
        DiningFoodLibEntity diningFoodLibEntity =  baseDao.selectOne(wrapper);


        if(diningFoodLibEntity != null){
            String sqllibNo = diningFoodLibEntity.getLibNo();
            Long libNoInt = Long.parseLong(sqllibNo);
            Long libNoc = libNoInt + 1;
            dto.setLibNo(libNoc+"");
        }else{
            dto.setLibNo(TimeNow + "001");
        }

        return dto;
    }

    /**
     * 菜品导入excel数据校验
     * @param DiningFoodLibExcelList
     * @param errorDiningFoodLibExcelList
     */
    @Override
    public void checkDiningFoodLibExcel(List<DiningFoodLibExcel> DiningFoodLibExcelList, List<DiningFoodLibExcel> errorDiningFoodLibExcelList){
        for (DiningFoodLibExcel diningFoodLibExcel : DiningFoodLibExcelList) {
            DiningFoodLibDTO diningFoodLibDTO = new DiningFoodLibDTO();
            String errText = "";
            Boolean flag = true;
            diningFoodLibDTO.setIsEnduring(1);
            //6.餐厅名称必填
            if (StringUtil.isEmpty(diningFoodLibExcel.getDiningRoomName())) {
                errText = errText + "餐厅名称不能为空;";
                flag = false;
            }else{
                Long dingingRoomId =  diningRoomService.getIdByName(diningFoodLibExcel.getDiningRoomName());
                if ( dingingRoomId == null) {
                    errText = errText + "餐厅名称不存在;";
                    flag = false;
                }
                diningFoodLibDTO.setDiningRoomId(dingingRoomId);

                //1.验证菜名（必填）
                if (StringUtil.isEmpty(diningFoodLibExcel.getName())) {
                    errText = errText + "菜品名称为空;";
                    flag = false;
                } else {
                    if (diningFoodLibExcel.getName().trim().length() > 15) {
                        errText = errText + "菜品名称不能超过15个字符;";
                        flag = false;
                    } else {
                        int i = this.checkDiningFoodName(diningFoodLibExcel.getName(),dingingRoomId);
                        if (i > 0) {
                            errText = errText + "菜品名称重复;";
                            flag = false;
                        } else {
                            diningFoodLibDTO.setName(diningFoodLibExcel.getName().trim());
                        }
                    }
                }
            }

            //2.验证菜品类型
            if (!StringUtil.isEmpty(diningFoodLibExcel.getType())) {
                Boolean sFlag = false;
                List<SysDictDTO> dictDTOList = dictService.listByDictType("dining_food_type");
                for (SysDictDTO dictDTO : dictDTOList) {
                    if (dictDTO.getDictName().equals(diningFoodLibExcel.getType())) {
                        sFlag = true;
                        diningFoodLibDTO.setType(Integer.parseInt(dictDTO.getDictValue()));
                        break;
                    }
                }
                if (!sFlag) {
                    errText = errText + "菜品类型不存在;";
                    flag = false;
                }
            }

            //3.验证菜品描述
            if (!StringUtil.isEmpty(diningFoodLibExcel.getRemark())) {
                if (diningFoodLibExcel.getRemark().trim().length() > 25) {
                    errText = errText + "菜品描述不能超过25个字符;";
                    flag = false;
                } else {
                    diningFoodLibDTO.setRemark(diningFoodLibExcel.getRemark());
                }
            }

            //4.验证金额 保留小数点后2位
            if (!StringUtil.isEmpty(diningFoodLibExcel.getPrice())) {
                if (String.valueOf(diningFoodLibExcel.getPrice()).trim().length() > 6) {
                    errText = errText + "金额不能超过5个字符;";
                    flag = false;
                }
                if ( diningFoodLibExcel.getPrice() < 0) {
                    errText = errText + "该菜品价格需要不能小于0";
                    flag = false;
                }
                diningFoodLibDTO.setPrice(diningFoodLibExcel.getPrice());
            }

            if(diningFoodLibDTO.getType()!= null && diningFoodLibDTO.getType() == 9 &&  !StringUtil.isEmpty(diningFoodLibExcel.getDiningType())){
                errText = errText + "该菜品类型为外卖预约,不应有就餐时段";
                flag = false;
            }

            //5.验证是否长期上架
//            if (StringUtil.isEmpty(diningFoodLibExcel.getIsEnduring())) {
//                errText = errText + "是否长期上架不能为空;";
//                flag = false;
//            }else{
//                if(diningFoodLibExcel.getIsEnduring().equals("是")){
//                    if(diningFoodLibDTO.getType() == 5){
//                        errText = errText + "当菜品类型为下午茶时,不应为周期性菜品";
//                        flag = false;
//                    }else{
//                        if (StringUtil.isEmpty(diningFoodLibExcel.getDiningType()) || StringUtil.isEmpty(diningFoodLibExcel.getWeek()) ){
            if(diningFoodLibDTO.getType()!= null && diningFoodLibDTO.getType() != 9 ){
                        if (StringUtil.isEmpty(diningFoodLibExcel.getDiningType())){
                            errText = errText + "就餐时段不能为空";
                            flag = false;
                        }else{
                            //5-1 验证是否在时间段内
                            String diningType =  this.getDicValueStr("dining_type",diningFoodLibExcel.getDiningType());
                            if("false".equals(diningType)){
                                errText = errText + "不存在该就餐时段";
                                flag = false;
                            }else{
                                diningFoodLibDTO.setDiningType(diningType);
                            }
                            //5-2 验证是否在循环周期内
//                            String week =  this.getDicValueStr("week",diningFoodLibExcel.getWeek());
//                            if(week.equals("false")){
//                                errText = errText + "不存在该循环周期";
//                                flag = false;
//                            }else{
//                                diningFoodLibDTO.setWeek(week);
//                            }
//                        }
                    }
//                }else{
//                    diningFoodLibDTO.setIsEnduring(2);
//                }
//            }
            }
            //6.校验图片
            if (!StringUtil.isEmpty(diningFoodLibExcel.getImgFile())) {
                String imgPath = diningFoodLibExcel.getImgFile();
                String url = "";
                try {
                    ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                    byte[] imageByte = ImgUtils.localEncodeByte(imgPath,bOut);
                    if(imageByte.length>0){
                        url = OSSFactory.build().uploadSuffix(imageByte,"jpg","jpg");
                    }
                    bOut.flush();
                    bOut.close();
                } catch (Exception e) {
                    errText = errText + "图片上传失败";
                }

//              imgPath = "upload/" + imgPath.substring(7);
                File file = new File(imgPath);
                if(file.exists()){
                    file.delete();
                }
//                if (!flag) {
//                    errText = errText + "本地菜品库导入删除图片失败,请手动删除！";
//                    flag = false;
//                }
                diningFoodLibDTO.setImageUrl(url);
            }



            if (!flag) {
                diningFoodLibExcel.setErrorImport(errText);
                errorDiningFoodLibExcelList.add(diningFoodLibExcel);
            }else{
                Map<String,String> todayWeek = DateUtils.getTodayWeekDate(DateUtils.DATE_PATTERN);
                String dayInWeek = (String) todayWeek.get("dayInWeek");
                diningFoodLibDTO.setWeek(dayInWeek);

                DiningFoodLibDTO dto =  diningFoodLibService.getFoodLibNo(diningFoodLibDTO);

                //校验通过过插入表
                this.save(dto);

                //插入菜单表
                //diningFoodDailyMenuService.insertDingFoodLibMenu(dto,2);
                diningFoodDailyMenuService.insertDingFoodLibMenu(dto,1);
            }
        }
    }

    /**
     * 菜品名唯一性校验
     * @param name
     * @return
     */
    @Override
    public int checkDiningFoodName(String name,Long diningRoomId){
        Map<String, Object> params = new HashMap<>();
        params.put("onlyName",name);
        params.put("diningRoomId",diningRoomId);
        Map<String,String> todayWeek = DateUtils.getTodayWeekDate(DateUtils.DATE_PATTERN);
        String todayDateStr = todayWeek.get("todayDateStr");
        params.put("todayDateStr",todayDateStr);
        QueryWrapper wrapper = this.getWrapper(params);
        Integer i = baseDao.selectCount(wrapper);
        return i;
    }

    /**
     * 根据字典名称字符串获取字典值字符串
     * @param fielName
     * @param params
     * @return
     */
    public String getDicValueStr(String fielName,String params){
        String dicValueStr = "false";
        List<String> valueArr = new ArrayList<>();
        List<SysDictDTO> dictDTOList = dictService.listByDictType(fielName);
        String[] diningTypeExcel = params.split(",");
        for (SysDictDTO dictDTO : dictDTOList) {
            if(Arrays.asList(diningTypeExcel).contains(dictDTO.getDictName())){
                valueArr.add(dictDTO.getDictValue());
            }
        }
        if( valueArr.size() > 0 ){
            dicValueStr = StringUtils.join(valueArr.toArray(), ",");
        }
        return dicValueStr;
    }

    /**
     * 定时任务
     * 将周期菜品新增到菜单
     */
    @Override
    public void cronInsertDingFoodLibMenu() {
        //周期性菜品
        Map<String, Object> params = new HashMap<>();
        params.put("isEnduring",  "1");
        params.put("isNotHeightTea",  "9");
        QueryWrapper wrapper = this.getWrapper(params);
        List<DiningFoodLibEntity> diningFoodLibEntityList = baseDao.selectDiningList(wrapper);
        for (DiningFoodLibEntity diningFoodLibEntity:diningFoodLibEntityList) {
            DiningFoodLibDTO diningFoodLibDTO = new  DiningFoodLibDTO();
            diningFoodLibDTO.setId(diningFoodLibEntity.getId());
            diningFoodLibDTO.setWeek(diningFoodLibEntity.getWeek());
            diningFoodLibDTO.setType(diningFoodLibEntity.getType());
            diningFoodLibDTO.setIsEnduring(diningFoodLibEntity.getIsEnduring());
            diningFoodLibDTO.setDiningType(diningFoodLibEntity.getDiningType());
            diningFoodLibDTO.setCreator(diningFoodLibEntity.getCreator());
            diningFoodDailyMenuService.insertDingFoodLibMenu(diningFoodLibDTO,1);
        }
    }

    @Override
    public Result validateDtoData(DiningFoodLibDTO dto){
        //检验菜名是否有重复
        if(dto.getId() == null){
            int i = this.checkDiningFoodName(dto.getName(),dto.getDiningRoomId());
            if( i>0 ){
                return new Result().error("该菜品已存在");
            }
        }

        if (!StringUtil.isEmpty(dto.getPrice())) {
            int length = String.valueOf(dto.getPrice()).trim().length();
            if ( length > 6) {
                return new Result().error("该菜品价格不能超过5个字符");
            }
            if ( dto.getPrice() < 0 ) {
                return new Result().error("该菜品价格需要不能小于0");
            }
        }

        if (!StringUtil.isEmpty(dto.getRemark())) {
            if ( dto.getRemark().trim().length() > 25) {
                return new Result().error("该菜品备注不能超过25个字符");
            }
        }
        if (!StringUtil.isEmpty(dto.getName())) {
            if ( dto.getName().trim().length() > 15 ) {
                return new Result().error("该菜品名称不能超过15个字符");
            }
        }
        return new Result();
    }

    @Override
    public  void setFoodLibIsDelete(Long[] ids){
        String idsStr = StringUtils.join(ids,",");
        List<DiningFoodLibEntity> diningFoodLibEntityList = baseDao.selectList(
                new QueryWrapper<DiningFoodLibEntity>().in("id",idsStr));
        for (DiningFoodLibEntity  diningFoodLibEntity:diningFoodLibEntityList) {
            diningFoodLibEntity.setStatus(1);
            baseDao.updateById(diningFoodLibEntity);
        }
    }
}

