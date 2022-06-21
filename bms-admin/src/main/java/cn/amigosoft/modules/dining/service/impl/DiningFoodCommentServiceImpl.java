package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.DateUtils;
import cn.amigosoft.modules.dining.dao.DiningFoodCommentDao;
import cn.amigosoft.modules.dining.dto.DiningFoodCommentDTO;
import cn.amigosoft.modules.dining.dto.DiningFoodCommentPageDTO;
import cn.amigosoft.modules.dining.entity.DiningFoodCommentEntity;
import cn.amigosoft.modules.dining.service.DiningFoodCommentService;
import cn.amigosoft.modules.security.user.SecurityUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  每日菜品评价 服务实现类
 * </p>
 *
 * @author : 陈耀
 * @version : 1.0
 * @date : 2021-04-20 15:11:09
 */
@Service
public class DiningFoodCommentServiceImpl extends CrudServiceImpl<DiningFoodCommentDao, DiningFoodCommentEntity, DiningFoodCommentDTO> implements DiningFoodCommentService {

    @Override
    public QueryWrapper<DiningFoodCommentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String tenantId = (String)params.get("tenantId");
        String diningFoodDailyMenuId = (String)params.get("diningFoodDailyMenuId");
        String score = (String)params.get("score");
        String reviewerPersonId = (String)params.get("reviewerPersonId");
        String content = (String)params.get("content");
        String commentDate = (String)params.get("commentDate");
        String creator = (String)params.get("creator");
        String createDate = (String)params.get("createDate");
        QueryWrapper<DiningFoodCommentEntity> wrapper = new QueryWrapper<>();
  
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(tenantId), "tenant_id", tenantId);
        wrapper.eq(StringUtils.isNotBlank(diningFoodDailyMenuId), "dining_food_daily_menu_id", diningFoodDailyMenuId);
        wrapper.eq(StringUtils.isNotBlank(score), "score", score);
        wrapper.eq(StringUtils.isNotBlank(reviewerPersonId), "reviewer_person_id", reviewerPersonId);
        wrapper.eq(StringUtils.isNotBlank(content), "content", content);
        wrapper.eq(StringUtils.isNotBlank(commentDate), "comment_date", commentDate);
        wrapper.eq(StringUtils.isNotBlank(creator), "creator", creator);
        wrapper.eq(StringUtils.isNotBlank(createDate), "create_date", createDate);
        return wrapper;
    }

    @Override
    public PageData<DiningFoodCommentPageDTO> getCommentPage(Map<String, Object> params) {
        paramsToLike(params, "name");
        //分页
        IPage<DiningFoodCommentEntity> page = getPage(params, "", false);
        Long tenantId = SecurityUser.getUser().getTenantId();
        params.put("tenantId",tenantId);
        if (Objects.isNull(params.get("endMenuData")) ){
            params.put("endMenuData", DateUtils.format(new Date(),DateUtils.DATE_PATTERN));
        }
        List<DiningFoodCommentPageDTO> list = baseDao.getCommentPage(page,params);
        list.forEach( l -> {
            l.setPercentageByOne("0");
            l.setPercentageByTwo("0");
            l.setPercentageByThree("0");
            l.setPercentageByFour("0");
            l.setPercentageByFive("0");
            if ( l.getCommentTotal()!=0 ){
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                if ( l.getCountByOne() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByOne()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByOne(percentage);
                }
                if ( l.getCountByTwo() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByTwo()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByTwo(percentage);
                }
                if ( l.getCountByThree() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByThree()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByThree(percentage);
                }
                if ( l.getCountByFour() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByFour()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByFour(percentage);
                }
                if ( l.getCountByFive() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByFive()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByFive(percentage);
                }
            }
        } );

        return getPageData(list, page.getTotal(), DiningFoodCommentPageDTO.class);
    }
    @Override
    public List<DiningFoodCommentPageDTO> getCommentList(Map<String, Object> params) {
        paramsToLike(params, "name");
        Long tenantId = SecurityUser.getUser().getTenantId();
        params.put("tenantId",tenantId);
        if (Objects.isNull(params.get("endMenuData")) ){
            params.put("endMenuData",DateUtils.format(new Date(),DateUtils.DATE_PATTERN));
        }
        List<DiningFoodCommentPageDTO> list = baseDao.getCommentList(params);
        list.forEach( l -> {
            switch (l.getDiningType()) {
                case 1: l.setDiningTypeStr("早餐");break;
                case 2: l.setDiningTypeStr("午餐");break;
                case 3: l.setDiningTypeStr("晚餐");break;
            }
            l.setMenuDateStr(DateUtils.format(l.getMenuDate(), DateUtils.DATE_PATTERN));
            l.setPercentageByOne("0/0%");
            l.setPercentageByTwo("0/0%");
            l.setPercentageByThree("0/0%");
            l.setPercentageByFour("0/0%");
            l.setPercentageByFive("0/0%");
            if ( l.getCommentTotal()!=0 ){
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                if ( l.getCountByOne() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByOne()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByOne(String.valueOf(l.getCountByOne()) + '/' + percentage + '%');
                }
                if ( l.getCountByTwo() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByTwo()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByTwo(String.valueOf(l.getCountByTwo()) + '/' + percentage + '%');
                }
                if ( l.getCountByThree() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByThree()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByThree(String.valueOf(l.getCountByThree()) + '/' + percentage + '%');
                }
                if ( l.getCountByFour() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByFour()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByFour(String.valueOf(l.getCountByFour()) + '/' + percentage + '%');
                }
                if ( l.getCountByFive() != 0 ){
                    String percentage = numberFormat.format((float)l.getCountByFive()/(float)l.getCommentTotal() * 100 );
                    l.setPercentageByFive(String.valueOf(l.getCountByFive()) + '/' + percentage + '%');
                }
            }
        } );

        return list;
    }
}

