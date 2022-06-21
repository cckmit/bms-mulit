package cn.amigosoft.modules.dining.dto;

import lombok.Data;

/**
 * @Author: cxb
 * @Description: 预约超时提醒
 * @Date: create in 2021/5/8 9:41
 */

@Data
public class DiningAppointmentRemindDTO {

    /**
     * 预约人手机号
     */
    private String phone;

    /**
     * 餐厅名称
     */
    private String name;
}
