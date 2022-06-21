package cn.amigosoft.modules.assets.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.assets.dto.BmsAssetsRepairDTO;
import cn.amigosoft.modules.assets.entity.BmsAssetsRepairEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产维修表 
 *
 * @author amigosoft amigosoft@amigosoft.cn
 * @since 1.0.0 2021-10-22
 */
@Mapper
public interface BmsAssetsRepairDao extends BaseDao<BmsAssetsRepairEntity> {

    //申请人获取申请记录
    List<BmsAssetsRepairDTO> queryPage(Map<String, Object> params);

    //审批人员获取审批记录
    List<BmsAssetsRepairDTO> queryVerifyPage(Map<String, Object> params);

    //物业获取维修管理（处理）记录
    List<BmsAssetsRepairDTO> queryDealPage(Map<String, Object> params);

    //维修人员获取维修记录
    List<BmsAssetsRepairDTO> queryServicePage(Map<String, Object> params);

    //获取报修申请记录详情
    BmsAssetsRepairDTO getDetail(@Param("id") Long id);
}