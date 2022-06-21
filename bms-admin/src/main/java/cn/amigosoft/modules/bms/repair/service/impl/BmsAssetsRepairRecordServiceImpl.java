package cn.amigosoft.modules.bms.repair.service.impl;

import cn.amigosoft.common.utils.ConvertUtils;
import cn.amigosoft.common.utils.Result;
import cn.amigosoft.modules.bms.constant.BmsConstant;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsRepairDao;
import cn.amigosoft.modules.bms.repair.dao.BmsAssetsRepairRecordDao;
import cn.amigosoft.modules.bms.repair.dto.BmsAssetsRepairRecordDTO;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairEntity;
import cn.amigosoft.modules.bms.repair.entity.BmsAssetsRepairRecordEntity;
import cn.amigosoft.modules.bms.repair.service.BmsAssetsRepairRecordService;
import cn.amigosoft.modules.bms.weixin.service.BmsWxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.amigosoft.common.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 资产维修记录表
 */
@Service
public class BmsAssetsRepairRecordServiceImpl extends CrudServiceImpl<BmsAssetsRepairRecordDao, BmsAssetsRepairRecordEntity, BmsAssetsRepairRecordDTO> implements BmsAssetsRepairRecordService {

    @Autowired
    private BmsAssetsRepairDao assetsRepairDao;

    @Autowired
    private BmsWxService wxService;

    @Override
    public QueryWrapper<BmsAssetsRepairRecordEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BmsAssetsRepairRecordEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public Result insertAssetsRepairRecord(BmsAssetsRepairRecordDTO dto) {
        Integer status = dto.getStatus();
        Long assetsRepairId = dto.getAssetsRepairId();
        BmsAssetsRepairEntity data = assetsRepairDao.selectById(assetsRepairId);
        if (data == null) {
            return new Result().error("该报修记录不存在");
        }
        Long toUserId = dto.getToUserId();
        Long creator = data.getCreator();
        Integer dataStatus = data.getStatus();
        switch (status) {
            case 1:
                if (!BmsConstant.REPAIR_WAIT.equals(dataStatus)) {
                    return new Result().error("只能审核待审批的报修记录");
                }
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",审批已驳回", "pages/assets/repair/applyList","已驳回");
                break;
            case 2:
                if (!BmsConstant.REPAIR_WAIT.equals(dataStatus)) {
                    return new Result().error("只能审核待审批的报修记录");
                }
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",审批已通过", "pages/assets/repair/applyList","已通过");
                wxService.sendWeixinTemplateMsg(toUserId, "尊敬的用户，您有新的【报修审批】待处理", "请到平台进行审批", "pages/assets/deal/dealList","待审批");
                break;
            case 3:
                if (!BmsConstant.REPAIR_APPROVE.equals(dataStatus)) {
                    return new Result().error("只能将审核通过的报修记录修改为处理中");
                }
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",已指派维修", "pages/assets/repair/applyList","处理中");
                wxService.sendWeixinTemplateMsg(toUserId, "尊敬的用户，您有新的【报修】待处理", data.getTitle() + ",请尽快维修", "pages/assets/servicer/serviceList","待维修");
                break;
            case 4:
                if (!BmsConstant.REPAIR_DEAL.equals(dataStatus)) {
                    return new Result().error("只能将已处理的报修记录修改为待评价");
                }
                wxService.sendWeixinTemplateMsg(creator, "尊敬的用户，您的【报修】进度更新了", data.getTitle() + ",已维修完毕", "pages/assets/repair/applyList","待评价");
                break;
            case 5:
                if (!BmsConstant.REPAIR_EVALUATION.equals(dataStatus)) {
                    return new Result().error("只能将已评价的报修记录修改为已完成");
                }
                break;
            default:
                return new Result().error("该报修记录状态异常");
        }
        BmsAssetsRepairRecordEntity entity = ConvertUtils.sourceToTarget(dto, BmsAssetsRepairRecordEntity.class);
        baseDao.insert(entity);
        BmsAssetsRepairEntity assetsRepairEntity = new BmsAssetsRepairEntity();
        assetsRepairEntity.setId(assetsRepairId);
        assetsRepairEntity.setStatus(status);
        assetsRepairEntity.setDealUserId(toUserId);
        assetsRepairDao.updateById(assetsRepairEntity);
        return new Result();
    }
}