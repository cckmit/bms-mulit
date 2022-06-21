package cn.amigosoft.modules.constant;

public class BmsConstant {

    /**
     * 主管审批接待餐权限
     */
    public static final String PERMISSION_MANAGER_VERIFY = "bms:receptionMeal:managerVerify";

    /**
     * 办公室审批接待餐权限
     */
    public static final String PERMISSION_OFFICE_VERIFY = "bms:receptionMeal:officeVerify";

    /**
     * 权限 审批人员报备记录
     */
    public static final String PERMISSION_VISIT_VERIFY = "bms:visitApplyVerify:save";

    /**
     * 权限 资产报修申请 审批
     */
    public static final String PERMISSION_ASSETS_REPAIR_VERIFY = "bms:assetsRepairRecord:verify";

    /**
     * 权限 资产报修申请 处理（物业）
     */
    public static final String PERMISSION_ASSETS_REPAIR_DEAL = "bms:assetsRepairRecord:deal";

    /**
     * 权限 资产维修内部人员
     */
    public static final String PERMISSION_ASSETS_REPAIR_REPAIR = "bms:assetsRepairRecord:repair";

    /**
     * 权限 资产维修外部人员
     */
    public static final String PERMISSION_ASSETS_REPAIR_EXTERNAL_REPAIR = "bms:assetsRepairRecord:externalRepair";

    /**
     * 角色 游客
     */
    public static final String ROLE_NAME_VISITOR = "游客";

    /**
     * 微信信息 类别 小程序
     */
    public static final Integer WX_TYPE_APP = 0;

    /**
     * 微信信息 类别 公众号
     */
    public static final Integer WX_TYPE_OFFICIAL_ACCOUNT = 1;

    /**
     * 微信信息 类别 开放平台
     */
    public static final Integer WX_TYPE_PUBLIC = 2;

    /**
     * 微信 推送模板ID
     */
    public static final String WX_TEMPLATE_ID = "m05bp51QOmnEOHqe4GDmIU4lfIcw_qFbypuM_C3tu1k";

    /**
     * 访客审批状态 同意
     */
    public static final Integer VISIT_STATUS_APPROVE = 1;

    /**
     * 访客审批状态 拒绝
     */
    public static final Integer VISIT_STATUS_REJECT = 2;

    /**
     * 审批状态 0待审批
     */
    public static final Integer VERIFY_STATUS_PENDING = 0;
    /**
     * 审批状态 1同意
     */
    public static final Integer VERIFY_STATUS_APPROVE = 1;
    /**
     * 审批状态 2拒绝
     */
    public static final Integer VERIFY_STATUS_REJECT = 2;
    /**
     * 审批状态 3待提交（用于游客向用户发起报备时所处的状态）
     * */
    public static final Integer VERIFY_STATUS_VISIT = 3;
    /**
     * 审批状态 4草稿（用于用户将报备申请存为草稿）
     * */
    public static final Integer VERIFY_STATUS_DRAFT = 4;

    /**
     * 审批回复意见 0同意/1驳回
     */
    public static final Integer VERIFY_OPINION_APPROVE = 0;
    public static final Integer VERIFY_OPINION_REJECT = 1;

    /**
     * 订单类型 报餐
     */
    public static final Integer ORDER_TYPE_MEAL = 0;

    /**
     * 报餐用餐/核销状态（细分） 0未核销/1已核销/2已过期/3已取消
     */
    public static final Integer ORDER_NOT_PAID = 0;
    public static final Integer ORDER_PAID = 1;
    public static final Integer ORDER_INVALID = 2;
    public static final Integer ORDER_CANCEL = 3;

    /**
     * 报餐评价状态 0未评价/1已评价/2已过期
     */
    public static final Integer EVALUATION_PENDING = 0;
    public static final Integer EVALUATION_EVALUATED = 1;
    public static final Integer EVALUATION_INVALID = 2;

    /**
     * 接待餐审批级别 0提交申请/1一级审批/2二级审批
     */
    public static final Integer RECEPTION_PENDING = 0;
    public static final Integer RECEPTION_FIR_LEVEL = 1;
    public static final Integer RECEPTION_SEC_LEVEL = 2;

    /**
     * 访客审批级别 0提交申请/1一级审批/2二级审批
     */
    public static final Integer VISIT_PENDING = 0;
    public static final Integer VISIT_FIR_LEVEL = 1;
    public static final Integer VISIT_SEC_LEVEL = 2;

    /**
     * 资产维修状态
     * 0待审批/1驳回/2待处理（已审批）/3待处理/4待评价/5已完成
     */
    public static final Integer REPAIR_PENDING_VERIFY = 0;
    public static final Integer REPAIR_REJECT = 1;
    public static final Integer REPAIR_VERIFY = 2;
    public static final Integer REPAIR_PENDING_DEAL = 3;
    public static final Integer REPAIR_PENDING_EVALUATE = 4;
    public static final Integer REPAIR_COMPLETE = 5;

    /**
     * 维修人员-维修状态 0待维修/1已处理（转单或直接维修）
     */
    public static final Integer SERVICE_REPAIR_PENDING = 0;
    public static final Integer SERVICE_REPAIR = 1;

    /**
     * 是否转派 0直接处理/1转派
     */
    public static final Integer DISPATCH_NO = 0;
    public static final Integer DISPATCH_YES = 1;

    /**
     * 删除标识 0未删除/1已删除
     */
    public static final Integer NOT_DEL = 0;
    public static final Integer DEL = 1;

    /**
     * 权限 临时取消订餐通知
     */
    public static final String PERMISSION_TEMP_CANCEL_NOTICE = "bms:order:tempCancelNotice";

    /**
     * 权限 接待餐 餐票发放
     */
    public static final String PERMISSION_RECEPTION_MEAL_GRANT = "bms:receptionMeal:grant";

    /**
     * 本月订单详情可临时取消次数
     */
    public static final String ORDER_DETAIL_CANCEL_COUNT = "ORDER_DETAIL_CANCEL_COUNT";

    /**
     * 权限 访客保安审批
     */
    public static final String PERMISSION_VISIT_GUARD_VERIFY = "bms:visitApplyVerify:guard";

    /**
     * 权限 访问地点 管理
     */
    public static final String PERMISSION_ADDRESS_MANAGE = "bms:visitAddress:manage";

    /**
     * 权限 新访客通知
     */
    public static final String PERMISSION_VISIT_NOTICE = "bms:visit:notice";

    /**
     * 订单详情状态 未核销
     */
    public static final Integer CANCEL_BEFORE_VERIFICATION = 0;

    /**
     * 停止报餐开关 参数编码
     */
    public static final String STOP_ORDER_CODE = "STOP_ORDER";

    /**
     * 停止报餐开关 开
     */
    public static final String STOP_ORDER_VALUE_ON = "ON";

    /**
     * 停止报餐开关 关
     */
    public static final String STOP_ORDER_VALUE_OFF = "OFF";


    /**
     * 扫码类型
     */
    public static final Integer DINING_ORDER_VERIFICATION = 10;
}
