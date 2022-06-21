package cn.amigosoft.modules.dining.service.impl;

import cn.amigosoft.common.page.PageData;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.common.utils.StringUtil;
import cn.amigosoft.modules.constant.BmsConstant;
import cn.amigosoft.modules.dining.dao.BmsEvaluationDao;
import cn.amigosoft.modules.dining.dto.BmsEvaluationDTO;
import cn.amigosoft.modules.dining.entity.BmsEvaluationEntity;
import cn.amigosoft.modules.dining.service.BmsEvaluationService;
import cn.amigosoft.modules.security.user.SecurityUser;
import cn.amigosoft.modules.security.user.UserDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评价表
 */
@Service
public class BmsEvaluationServiceImpl extends CrudServiceImpl<BmsEvaluationDao, BmsEvaluationEntity, BmsEvaluationDTO> implements BmsEvaluationService {

    @Override
    public QueryWrapper<BmsEvaluationEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<BmsEvaluationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    // 评价列表
    @Override
    public PageData<BmsEvaluationDTO> queryPage(Map<String, Object> params) {
        try {
            UserDetail user = SecurityUser.getUser();
            Long userId = user.getId();
            params.put("userId", userId);
            IPage<BmsEvaluationEntity> page = getPage(params, "e.end_date", false);
            List<BmsEvaluationDTO> list = baseDao.selectPageList(page, params);
            for (BmsEvaluationDTO evaluationDTO : list) {
                this.SetStatus(evaluationDTO);
            }
            return getPageData(list, page.getTotal(), BmsEvaluationDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 评价详情
     */
    @Override
    public BmsEvaluationDTO getDetail(Long id) {
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        BmsEvaluationDTO dto = baseDao.getDetail(id, userId);
        this.SetStatus(dto);
        return dto;
    }

    /**
     * 根据评价内容与日期判断评价状态
     */
    private void SetStatus(BmsEvaluationDTO evaluationDTO) {
        String content = evaluationDTO.getContent();
        // 评价内容不为空，即已评价
        if (content != null) {
            evaluationDTO.setStatus(BmsConstant.EVALUATION_EVALUATED);
        }
        Date nowDate = new Date();
        Date endDate = evaluationDTO.getEndDate();
        // 当前时间在结束时间之前且评价内容为空，即未评价
        if (nowDate.before(endDate) && content == null) {
            evaluationDTO.setStatus(BmsConstant.EVALUATION_PENDING);
        }
        // 当前时间在结束时间之后且评价内容为空，即已过期
        if (nowDate.after(endDate) && content == null) {
            evaluationDTO.setStatus(BmsConstant.EVALUATION_INVALID);
        }
    }

    @Override
    public Result evaluate(BmsEvaluationDTO evaluationDTO) {
        Result result = new Result();
        Map<String, Object> params = new HashMap<>();
        if (StringUtil.isEmpty(evaluationDTO.getContent())) {
            return result.error("评价内容不能为空");
        }
        Long id = evaluationDTO.getId();
        BmsEvaluationEntity entity = baseDao.selectById(id);
        Date endDate = entity.getEndDate();
        if (new DateTime(endDate).isBeforeNow()){
            return result.error("已超过评价结束时间");

        }
        params.put("id", id);
        params.put("content", evaluationDTO.getContent());
        params.put("updateDate", new Date());
        UserDetail user = SecurityUser.getUser();
        Long userId = user.getId();
        params.put("userId", userId);
        baseDao.evaluate(params);
        return result;
    }
}