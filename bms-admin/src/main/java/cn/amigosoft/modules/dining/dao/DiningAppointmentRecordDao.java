package cn.amigosoft.modules.dining.dao;

import cn.amigosoft.common.dao.BaseDao;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordInfoDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRecordPageDTO;
import cn.amigosoft.modules.dining.dto.DiningAppointmentRemindDTO;
import cn.amigosoft.modules.dining.entity.DiningAppointmentRecordEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 下午茶预约记录 Dao 接口
 * </p>
 *
 * @author : cxb cy
 * @version : 1.0
 * @date : 2021-04-28 19:30:12
 */
@Mapper
public interface DiningAppointmentRecordDao extends BaseDao<DiningAppointmentRecordEntity> {
    /**
     * 菜品预约记录分页查询
     *
     * @param page
     * @param params
     * @return
     */
    List<DiningAppointmentRecordPageDTO> getList(@Param("page") IPage<DiningAppointmentRecordEntity> page, @Param("params") Map<String, Object> params);

    /**
     * 菜品预约记录详细
     *
     * @param id
     * @return
     */
    DiningAppointmentRecordInfoDTO getInfo(@Param("id") Long id);


    List<DiningAppointmentRemindDTO> getByStatusAndNoTenantIdAndCreateDate(@Param("status") Integer status, @Param("pickUpTime") String pickUpTime);
}
