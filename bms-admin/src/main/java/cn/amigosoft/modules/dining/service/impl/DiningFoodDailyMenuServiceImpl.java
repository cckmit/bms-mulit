package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.exception.RenException;
import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.dining.dao.DiningFoodDailyMenuDao;
import cn.amigosoft.modules.dining.dto.DiningFoodAddDailyMenuDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodDailyMenuDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodDailyMenuListDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodLibDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodDailyMenuEntity;
import cn.amigosoft.modules.dining.service.DiningFoodDailyMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  每日菜品 服务实现类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 14:45:58
 */
@Service
public class DiningFoodDailyMenuServiceImpl extends CrudServiceImpl<DiningFoodDailyMenuDao, DiningFoodDailyMenuEntity, DiningFoodDailyMenuDTO> implements DiningFoodDailyMenuService {

    @Override
    public QueryWrapper<DiningFoodDailyMenuEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<DiningFoodDailyMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.in(StringUtils.isNotBlank((String)params.get("menuDate")),"menu_date",params.get("menuDate"));
        wrapper.eq(StringUtils.isNotBlank((String)params.get("diningFoodLibId")),"dining_food_lib_id",params.get("diningFoodLibId"));
        return wrapper;
    }
    /**
     * 每日菜品列表
     * @param params
     * @return
     */
    @Override
    public PageData<DiningFoodDailyMenuListDTO> getDailyData(Map<String, Object> params) {
        if ( Objects.isNull(params.get("date")) ) {
            throw new RenException("日期不能为空");
        }
        if ( Objects.isNull(params.get("diningType")) ) {
            throw new RenException("菜品时段不能为空");
        }
        if ( !("1".equals(params.get("diningType")))  &&  !("2".equals(params.get("diningType"))) && !("3".equals(params.get("diningType"))) ){
            throw new RenException("菜品时段错误");
        }
        if ( Objects.isNull(params.get("diningRoomId")) ) {
            throw new RenException("餐厅不能为空");
        }

        //分页
        IPage<DiningFoodDailyMenuEntity> page = getPage(params, "m.create_date", false);

        List<DiningFoodDailyMenuListDTO> list = baseDao.getDailyData(page,params);

        return getPageData(list, page.getTotal(), DiningFoodDailyMenuListDTO.class);
    }
    /**
     * 每日菜单移除
     * @param ids
     * @return
     */
    @Override
    public void removeDailyMenu(Long[] ids) {

        baseDao.changeStatus(ids,0); //'是否上架 0:否 1:是'

    }
    /**
     * 指定日期时段内菜单菜品
     *
     * @param params
     * @return
     */
    @Override
    public List<DiningFoodDailyMenuEntity> getTodayMenuList(Map<String, Object> params) {

//        List<DiningFoodDailyMenuEntity> menuList = baseDao.selectList(new QueryWrapper<DiningFoodDailyMenuEntity>().eq("status", 1).eq("menu_date", params.get("date")).eq("dining_type", params.get("diningType")));
        List<DiningFoodDailyMenuListDTO> menuDtoList = baseDao.getTodayMenuList(params);
        List<DiningFoodDailyMenuEntity> menuList =  ConvertUtils.sourceToTarget(menuDtoList,DiningFoodDailyMenuEntity.class);

        return menuList;
    }

    @Override
    public void addDailyMenu(DiningFoodAddDailyMenuDTO dto) {
        if ( dto.getIds().length <= 0 ){
            throw new RenException("菜品传输错误");
        }
        //先判断是否是当日当时段菜单内 已下架菜品
        List<DiningFoodDailyMenuEntity> menuList = baseDao.selectList(new QueryWrapper<DiningFoodDailyMenuEntity>().eq("status", 0).eq("menu_date", dto.getMenuDate()).eq("dining_type", dto.getDiningType()).in("dining_food_lib_id",Arrays.asList(dto.getIds())));
        //已下架菜单列表中的iotsaas_dining_food_daily_menu.id
        Long[] menuIds = menuList.stream().map(DiningFoodDailyMenuEntity::getId).toArray(Long[]::new);
        //下架菜单上架
        if ( menuIds.length > 0 ){
            baseDao.changeStatus(menuIds,1); //'是否上架 0:否 1:是'
        }
        //已下架菜单列表中的dining_food_lib_id.id
        List<Long> diningFoodLibIds = menuList.stream().map(DiningFoodDailyMenuEntity::getDiningFoodLibId).collect(Collectors.toList());
        //其他菜品新增 全部ids过滤已上架ids
        List<Long> allIds = Arrays.asList(dto.getIds());
        //过滤上架的菜品
        List<Long> addIds = allIds.stream().filter( a-> !diningFoodLibIds.contains(a) ).collect(Collectors.toList());
        //插入需要新增的菜品
        List<DiningFoodDailyMenuEntity> insertList = new ArrayList<>();
        if (addIds.size()>0){
            //重复插入校验，查询已存在并上架菜品
            List<DiningFoodDailyMenuEntity> aleardyList = baseDao.selectList(new QueryWrapper<DiningFoodDailyMenuEntity>().eq("status", 1).eq("menu_date", dto.getMenuDate()).eq("dining_type", dto.getDiningType()).in("dining_food_lib_id",addIds));
            List<Long> aleardyListIds = aleardyList.stream().map(DiningFoodDailyMenuEntity::getDiningFoodLibId).collect(Collectors.toList());
            for ( int i = 0; i < addIds.size(); i++ ){
                DiningFoodDailyMenuEntity menu = new DiningFoodDailyMenuEntity();
                menu.setMenuDate(dto.getMenuDate());
                menu.setDiningType(dto.getDiningType());
                menu.setStatus(1);
                menu.setDiningFoodLibId(addIds.get(i));
                //防止重复插入
                if ( !aleardyListIds.contains(addIds.get(i)) ) {
                    insertList.add(menu);
                }
            }
            insertBatch(insertList);
        }
    }



    /**
     * 主要逻辑做简化，不想对旧代码做大改,只能修补了
     * 菜单周期为当周,周一执行定时任务新增日志范围为周1至下周1（7天）
     * 将周期菜品新增到菜单
     * @param dto
     * @param type 1:会改变当天的菜单,2:不会改变当天的菜单
     */
    @Override
    public void insertDingFoodLibMenu(DiningFoodLibDTO dto, Integer type) {
        //1.删除旧数据
        Long[] dingFoodLibMenuDiningFoodLibs = {dto.getId()};
        this.deleteDingFoodLibMenuByDiningFoodLibIds(dingFoodLibMenuDiningFoodLibs,type);

        //菜品类型为下午茶,不加入菜单
        if(!StringUtil.isEmpty(dto.getType()) && dto.getType() == 9){
            return;
        }

        //非周期新增和导入菜品不加入菜单
        //if(dto.getIsEnduring() == 2){
        //return;
        //}

        //Map<String, List> weekDate = DateUtils.getWeekDateList(DateUtils.DATE_PATTERN,null);
        //List<String> lDate = weekDate.get("lDate");
        //List<Integer> lWeek = weekDate.get("lWeek");

        //新增每日菜单
        String weekStr = dto.getWeek();
        List<String> weekArr = Arrays.asList(weekStr.split(","));
        String[] diningType  =  dto.getDiningType().split(",");

        //获取当天日期
        Map<String,String> todayWeek = DateUtils.getTodayWeekDate(DateUtils.DATE_PATTERN);
        String todayDateStr = (String) todayWeek.get("todayDateStr");
        String dayInWeek = (String) todayWeek.get("dayInWeek");

        //设置默认值
        List<String> lDate = new ArrayList<>();
        List<String> lWeek = new ArrayList<>();
        lDate.add(todayDateStr);
        lWeek.add(dayInWeek);

        //2.根据菜品的用餐时间(早中午)新增用餐策略
        if(diningType.length > 0){
        for (String diningTypeMenu : diningType) {
            //判断周期是否需要新增用餐策略（今日之前的数据不会改变）
            for (int j = 0; j < lDate.size(); j++) {
//                Boolean isAfter  = true;
//                if( type == 2 ){
//                    isAfter = DateUtils.dateIsAfterToday(lDate.get(j));
//                    if(this.selectDiningFoodDailyMenu(lDate.get(j),dto.getId(),diningTypeMenu) == 0 && todayDateStr.equals(lDate.get(j))){
//                        isAfter = true;
//                    }
//                }
                //新增晚于今日菜单
//                if( isAfter ){
                String dayinWeek = lWeek.get(j) + "";
                if (weekArr.contains(dayinWeek)) {
                    DiningFoodDailyMenuEntity diningFoodDailyMenuEntity = new DiningFoodDailyMenuEntity();
                    diningFoodDailyMenuEntity.setDiningType(Integer.parseInt(diningTypeMenu));
                    diningFoodDailyMenuEntity.setDiningFoodLibId(dto.getId());
                    diningFoodDailyMenuEntity.setStatus(1);
                    diningFoodDailyMenuEntity.setCreator(dto.getCreator());

                    Date d = DateUtils.stringToDate(lDate.get(j), DateUtils.DATE_PATTERN);
                    diningFoodDailyMenuEntity.setMenuDate(d);
                    this.insert(diningFoodDailyMenuEntity);
                }
//             }
            }
        }
        }
    }

    /**
     * 根据菜品ID,删除菜品库中时间大于今日的菜品
     * @params type:1.删除今日和今日之后所有菜单  2.删除今日之后的菜单
     */
    @Override
    public void deleteDingFoodLibMenuByDiningFoodLibIds(Long[] diningFoodLibids,Integer type) {
        for (Long diningFoodLibid : diningFoodLibids){
            //1.根据菜品ID查询菜品库信息
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("dining_food_lib_id", diningFoodLibid + "");

            //2.删除当天之后的菜单
            Map<String,String> todayWeek = DateUtils.getTodayWeekDate(DateUtils.DATE_PATTERN);
            String todayDateStr = (String) todayWeek.get("todayDateStr");

            //2.是否需要删除当天日期的菜品
            if(type == 1){
                //是
                wrapper.ge("menu_date",todayDateStr);
            }else{
                //否
                wrapper.gt("menu_date",todayDateStr);
            }
            //3.查询
            List<DiningFoodDailyMenuDTO> diningFoodDailyMenuDTOList = baseDao.selectList(wrapper);

            //4.删除每日菜单中已存在的菜品
            if (diningFoodDailyMenuDTOList.size() > 0) {
                baseDao.delete(wrapper);
            }
        }
    }

    /**
     * 查询菜品当日是否在菜单中
     * @param menuDate
     * @param diningFoodLibId
     * @return
     */
    public Integer selectDiningFoodDailyMenu(String menuDate,Long diningFoodLibId,String diningType){
       return baseDao.selectCount(new QueryWrapper<DiningFoodDailyMenuEntity>().eq("menu_date",menuDate).eq("dining_food_lib_id",diningFoodLibId).eq("dining_type",diningType));
    }
}

