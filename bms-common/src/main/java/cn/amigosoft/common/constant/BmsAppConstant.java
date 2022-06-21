package cn.amigosoft.common.constant;

/**
 * Bms常量
 */
public interface BmsAppConstant {
    /**
     * 删除标识
     */
    interface DelFlag {
        int NOT_DEL = 0;   //未删除
        int DEL = 1;    //已删除
    }
    /**
     * 餐品类型
     */
    interface DiningItemType {
        int SET_MEAL = 0; //套餐
        int BUFFET = 1; //自助餐
    }
    /**
     * 报餐用餐/核销状态
     */
    interface DiningOrderStatus {
        int NOT_PAID = 0; //未核销
        int PAID = 1; //已核销
        int INVALID = 2; //已过期
        int CANCEL = 3; //已取消
    }
    /**
     * 报餐评价状态
     */
    interface DiningEvaluationStatus {
        int NOT_EVALUATED = 0; //待评价
        int EVALUATED = 1;     //已评价
        int INVALID = 2;       //已过期
    }
    /**
     * 接待餐申请状态
     */
    interface ReceptionMealApplyStatus {
        int PENDING_VERIFY = 0;//待审批
        int VERIFY = 1;   //已同意
        int REFUSE = 2;   //已拒绝
    }
    /**
     * 接待餐审批意见
     */
    interface ReceptionMealVerifyStatus {
        int APPROVAL = 0; //同意
        int REFUSE = 1;   //驳回
    }
    /**
     * 接待餐审批级别
     */
    interface ReceptionMealVerifyLevel {
        int APPLY = 0;  //提交申请
        int MANAGER_VERIFY = 1;  //主管审批
        int OFFICE_VERIFY = 2;    //办公室审批
    }
    /**
     * 访客报备状态
     */
    interface VisitApplyStatus {
        int PENDING_VERIFY = 0;//待审批
        int VERIFY = 1;   //同意
        int REFUSE = 2;   //拒绝
    }
    /**
     * 访客报备审批意见
     */
    interface VisitVerifyOption {
        int APPROVAL = 0; //同意
        int REFUSE = 1;   //拒绝
    }
    /**
     * 资产维修状态
     */
    interface AssetsRepairStatus {
        int PENDING_VERIFY = 0;//待审批
        int REFUSE = 1;   //驳回
        int VERIFY = 2;  //待处理（已审批）
        int PENDING_DEAL = 3;   //待处理
        int PENDING_EVALUATE = 4;  //待评价
        int COMPLETE = 5; //已完成
    }
    /**
     * 维修人员-维修状态
     */
    interface AssetsServiceStatus {
        int PENDING_REPAIR = 0;  //待维修
        int REPAIR = 1;  //已处理（转单/维修）
    }
    /**
     * 是否派单/转单
     */
    interface IsDispatch {
        int YES = 1;  //派单/转单
        int NO = 0;    //直接处理
    }
}
