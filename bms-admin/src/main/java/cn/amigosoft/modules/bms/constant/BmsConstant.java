package cn.amigosoft.modules.bms.constant;


/**
 * 常量
 */
public class BmsConstant {

    /**
     * 未删除对应的值
     */
    public static final String NOT_DEL = "0";

    /**
     * 已删除对应的值
     */
    public static final Integer DEL = 1;

    /**
     * 删除字段名称
     */
    public static final String DEL_COLUMN_NAME = "del";

    /**
     * 状态字段名称
     */
    public static final String STATUS_COLUMN_NAME = "status";

    /**
     * 访客审批状态 同意
     */
    public static final Integer VISIT_STATUS_APPROVE = 1;

    /**
     * 访客审批状态 拒绝
     */
    public static final Integer VISIT_STATUS_REJECT = 2;

    /**
     * 下拉选择全部时对应的ID
     */
    public static final Long EMPTY_ID = -1L;

    /**
     * 下拉选择全部时显示的中文
     */
    public static final String EMPTY_NAME = "全部";

    /**
     * 订单类型 报餐
     */
    public static final Integer ORDER_TYPE_MEAL = 0;

    /**
     * 订单详情状态 未核销
     */
    public static final Integer CANCEL_BEFORE_VERIFICATION = 0;

    /**
     * 订单详情状态 已核销
     */
    public static final Integer CANCEL_AFTER_VERIFICATION = 1;

    /**
     * 权限 查看当前用户报餐记录
     */
    public static final String PERMISSION_ORDER_VIEW_CURRENT_USER = "bms:order:viewCurrentUser";

    /**
     * 权限 查看本部门报餐记录
     */
    public static final String PERMISSION_ORDER_VIEW_DEPT = "bms:order:viewDept";

    /**
     * 权限 查看全部报餐记录
     */
    public static final String PERMISSION_ORDER_VIEW_ALL = "bms:order:viewAll";

    /**
     * 权限 访客主管审批
     */
    public static final String PERMISSION_VISIT_VERIFY = "bms:visitApplyVerify:save";

    /**
     * 权限 接待餐主管审批
     */
    public static final String PERMISSION_MANAGER_VERIFY = "bms:receptionMeal:managerVerify";

    /**
     * 权限 接待餐办公室审批
     */
    public static final String PERMISSION_OFFICE_VERIFY = "bms:receptionMeal:officeVerify";

    /**
     * 权限 资产维修 内部人员
     */
    public static final String PERMISSION_ASSETS_REPAIR = "bms:assetsRepairRecord:repair";

    /**
     * 权限 资产维修 外部人员
     */
    public static final String PERMISSION_ASSETS_EXTERNAL_REPAIR = "bms:assetsRepairRecord:externalRepair";

    /**
     * 权限 资产报修申请 审批
     */
    public static final String PERMISSION_ASSETS_REPAIR_VERIFY = "bms:assetsRepairRecord:verify";

    /**
     * 权限 资产维修 处理
     */
    public static final String PERMISSION_ASSETS_REPAIR_DEAL = "bms:assetsRepairRecord:deal";

    /**
     * 权限 接待餐 餐票发放
     */
    public static final String PERMISSION_RECEPTION_MEAL_GRANT = "bms:receptionMeal:grant";

    /**
     * 权限 访问地点 管理
     */
    public static final String PERMISSION_ADDRESS_MANAGE = "bms:visitAddress:manage";

    /**
     * 权限 临时取消订餐通知
     */
    public static final String PERMISSION_TEMP_CANCEL_NOTICE = "bms:order:tempCancelNotice";

    /**
     * 访客申请状态 待审批
     */
    public static final Integer VISIT_STATUS_WAIT = 0;

    /**
     * 资产维修处理状态 待审批
     */
    public static final Integer REPAIR_WAIT = 0;

    /**
     * 资产维修处理状态 已驳回
     */
    public static final Integer REPAIR_REJECT = 1;

    /**
     * 资产维修处理状态 待处理（已审批）
     */
    public static final Integer REPAIR_APPROVE = 2;

    /**
     * 资产维修处理状态 处理中
     */
    public static final Integer REPAIR_DEAL = 3;

    /**
     * 资产维修处理状态 待评价
     */
    public static final Integer REPAIR_EVALUATION = 4;

    /**
     * 资产维修处理状态 已完成
     */
    public static final Integer REPAIR_FINISH = 5;

    /**
     * 是否派单 直接处理
     */
    public static final Integer NOT_DISPATCH = 0;

    /**
     * 是否派单 指派他人
     */
    public static final Integer IS_DISPATCH = 1;

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
     * 部门根节点ID
     */
    public static final Long DEPT_ROOT_ID = 0L;

    /**
     * 本月订单详情可临时取消次数
     */
    public static final String ORDER_DETAIL_CANCEL_COUNT = "ORDER_DETAIL_CANCEL_COUNT";

    /**
     * 权限 访客保安审批
     */
    public static final String PERMISSION_VISIT_GUARD_VERIFY = "bms:visitApplyVerify:guard";

    /**
     * 权限 新访客通知
     */
    public static final String PERMISSION_VISIT_NOTICE = "bms:visit:notice";

    /**
     * 权限 查看所有用户
     */
    public static final String PERMISSION_VIEW_USER_ALL = "bms:user:all";

    /**
     * 权限 查看所有部门
     */
    public static final String PERMISSION_VIEW_DEPT_ALL = "bms:dept:all";

    /**
     * 权限 查看所有角色
     */
    public static final String PERMISSION_VIEW_ROLE_ALL = "bms:role:all";

    /**
     * 状态 上线
     */
    public static final Integer STATUS_ONLINE = 0;

    /**
     * 状态 下线
     */
    public static final Integer STATUS_OFFLINE = 1;

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
}
