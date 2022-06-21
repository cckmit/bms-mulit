/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.amigosoft.common.constant;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface Constant {
    /**
     * 座机正则
     */
    String FIXED_PHONE_REGULAR = "^[0][1-9]{2,3}-[0-9]{5,10}$";
    /**
     * 手机号正则
     */
    String PHONE_REGULAR = "((^(13|15|18|17|19|14|16)[0-9]{9}$)|(^0[1,2]{1}\\\\d{1}-?\\\\d{8}$)|(^0[3-9] {1}\\\\d{2}-?\\\\d{7,8}$)|(^0[1,2]{1}\\\\d{1}-?\\\\d{8}-(\\\\d{1,4})$)|(^0[3-9]{1}\\\\d{2}-? \\\\d{7,8}-(\\\\d{1,4})$))";
    /**
     * 手机号+座机正则
     */
    String MOBILE_REGULAR = "^((0\\d{2,3}-\\d{7,8})|(1[0-9]\\d{9}))$";

    /**
     * 邮箱正则
     */
    String EMAIL_REGULAR = "^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\\.[a-z]{2,}$";

    /**
     * 密码正则(必须包含字母、数字和特殊字符且长度在8-18位)
     */
    String PASSWORD_REGULAR = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}:\";'<>?,./]).{8,18}$";

    /**
     * 车牌号正则表达式
     */
    String CAR_NO_REGULAR = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})";

    /**
     * 身份证正则表达式
     */
    String ID_CARD_REGULAR = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * 仓库编号前缀
     */
    String HOUSER_NO_PREFIX = "CK";

    /**
     *
     */
    String POSITION_NO_PREFIX = "KW";

    /**
     * 供应商编号前缀
     */
    String SUPPLIER_NO_PREFIX = "GYS";

    /**
     * php转发前旧请求属性中地址标识
     */

    String PHP_FORWARD_ATTR = "php_forward_attr";
    /**
     * php转发地址
     */

    String PHP_FORWARD_URL = "/php/forward";

    /**
     * 租户标识
     */
    String TENANT_KEY = "tenantId";
    /**
     * 系统默认租户ID
     */
    Long DEFAULT_TENANT_ID = 10000L;

    /**
     * 顶级租户派单
     */
    Long SUPER_TENANT_SEND = -1L;

    /**
     * 成功
     */
    int SUCCESS = 1;
    /**
     * 失败
     */
    int FAIL = 0;
    /**
     * OK
     */
    String OK = "OK";
    /**
     * 用户标识
     */
    String USER_KEY = "userId";

    /**
     * 租户根节点
     */
    Long TENANT_ROOT = 0L;

    /**
     * 菜单根节点标识
     */
    Long MENU_ROOT = 0L;
    /**
     * 部门根节点标识
     */
    Long DEPT_ROOT = 0L;

    /**
     * 部门根节点的pids
     */
    String DEPT_ROOT_PIDS = "0";

    /**
     * 设备关联关系 跟节点
     */
    Long DEVICE_RELATION_ROOT = 0L;

    /**
     * 设备关联关系的pids
     */
    String DEVICE_RELATION_PIDS = "0";

    /**
     * 数据字典根节点标识
     */
    Long DICT_ROOT = 0L;

    /**
     * 部门排序
     */
    int DEPT_SORT = 0;

    /**
     * 区域根节点
     */
    Long AREA_ROOT = 0L;

    /**
     * 区域 上级ID，逗号分隔
     */
    String AREA_TOP = "0";

    /**
     * 升序
     */
    String ASC = "asc";
    /**
     * 降序
     */
    String DESC = "desc";
    /**
     * 创建时间字段名
     */
    String CREATE_DATE = "create_date";

    /**
     * 创建时间字段名
     */
    String ID = "id";

    //系统创建者
    Long DEFAULT_CREATOR_UPDATER = 0L;

    /**
     * 数据权限过滤
     */
    String SQL_FILTER = "sqlFilter";

    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 每页显示记录数
     */
    String LIMIT = "limit";
    /**
     * 排序字段
     */
    String ORDER_FIELD = "orderField";
    /**
     * 排序方式
     */
    String ORDER = "order";
    /**
     * token header
     */
    String TOKEN_HEADER = "token";

    /**
     * 云存储配置KEY
     */
    String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";
    /**
     * 短信配置KEY
     */
    String SMS_CONFIG_KEY = "SMS_CONFIG_KEY";
    /**
     * 邮件配置KEY
     */
    String MAIL_CONFIG_KEY = "MAIL_CONFIG_KEY";
    /**
     * 排班班次初始化KEY
     */
    String SCHEDULE_SHIFT = "SCHEDULE_SHIFT";
    /**
     * 随手拍超时提醒配置KEY
     */
    String REALTY_REPAIR_REMIND = "REALTY_REPAIR_REMIND";
    /**
     * 工单超时提醒配置KEY
     */
    String ORDER_REMIND = "ORDER_REMIND";

    /**
     * 检验时间是否为HH:mm类型格式的正则表达式
     */
    String CHECK_TIME_REGULAR_EXPRESSION = "(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})";

    /**
     * 入库类型KEY
     */
    String IN_TYPE_KEY = "IN_TYPE_KEY";
    /**
     * 出库类型KEY
     */
    String OUT_TYPE_KEY = "OUT_TYPE_KEY";

    /**
     * 创建租户验证码失效时长  单位:分钟
     */
    int CREATE_TENANT_CAPTCHA_PRESCRIPTION = 5;

    //音箱设备类型id
    /**
     * 100036：音柱
     * 100037：播控器
     * 100071：收扩机
     */
    List<Long> SPEAKER_DEVICE_TYPE = Arrays.asList(100036L, 100037L, 100071L);

    List<Long> ENERGY_DEVICE_TYPE = Arrays.asList(100030L, 100001L, 100002L, 100055L, 10000017L);
    /**
     * IP话筒
     */
    Long SPEAKER_DEVICE_IP = 100037L;

    /**
     * 收扩机
     */
    Long SPEAKER_DEVICE_EXPANDER = 100071L;

    /**
     * 音柱
     */
    Long SPEAKER_DEVICE_SOUND = 100036L;

    Long STREETLIGHT_DEVICE = 100011L;


    /**
     * geomagnetism
     * 地磁
     */
    Long GEOMAGNETISM_DEVICE = 100007L;

    /**
     * 车位摄像头
     **/
    Long PARKING_CAMERA = 31000001L;

    /**
     * 电子围栏
     **/
    Long ELECTRONIC_FENCE = 31000002L;

    /**
     * 道闸
     */
    Long BARRIER = 100010L;

    /**
     * 预约时间
     */
    String DINING_APPOINTMENT_TIME = "17:00:00";
    Integer DINING_APPOINTMENT_TIME_INT = 170000;
    Integer DINING_APPOINTMENT_TIME_HOUR = 17;

    /**
     * app端选择住户和员工
     */
    interface PersonTypeApp {
        int RESIDENT = 1;//住户
        int STAFF = 2;//员工
    }

    /**
     * 井盖设备类型id
     */
    List<Long> MANHOLE_COVER_DEVICE_TYPE_IDS = Arrays.asList(10000001L, 100005L);

    /**
     * 垃圾箱设备id
     */
    List<Long> DUSTBIN_DEVICE_TYPE_IDS = Arrays.asList(100022L);


    /**
     * 住宿的设备类型id
     */
    String Hotel_Device_Type_Id = "100002,100001,100030,100055,100029,10000019,10000002,100004,10000017";

    /**
     * 住宿房源合同提前预警天数
     */
    Integer HOTEL_ROOM_CONTRACT_EXPIRATION_ALERT_DAYS = 1;

    /**
     * 房源的月数时间
     */
    Integer HOTEL_MONTH_DAYS_NUM = 30;
    /**
     * 房源房间默认数量
     */
    Integer HOTEL_ROOM_DEFAULT_NUM = 0;


    /**
     * 定时任务状态
     */
    enum ScheduleStatus {
        /**
         * 暂停
         */
        PAUSE(0),
        /**
         * 正常
         */
        NORMAL(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3),
        /**
         * FASTDFS
         */
        FASTDFS(4),
        /**
         * 本地
         */
        LOCAL(5),
        /**
         * MinIO
         */
        MINIO(6);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 短信服务商
     */
    enum SmsService {
        /**
         * 阿里云
         */
        ALIYUN(1),
        /**
         * 腾讯云
         */
        QCLOUD(2);

        private int value;

        SmsService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 同步类型
     * 1：同步租户 2:同步aep产品 3:同步设备  4.绑定产品
     */
    enum ReceiverLogActive {
        TENANT(1),
        AEPPRODUCT(2),
        DEVICE(3),
        BINDPPRODUCT(4);

        private int value;

        ReceiverLogActive(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    enum OpenRole {
        /**
         * 是
         */
        YES(1),

        /**
         * 否
         */
        NO(0);

        private int value;

        OpenRole(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 社区租户状态
     */
    enum TenantStatus {

        /**
         * 启用
         */
        ENABLE(1),

        /**
         * 禁用
         */
        DISABLE(0);

        private int value;

        TenantStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 是否是顶级租户
     */
    enum SuperTenant {

        /**
         * 顶级租户
         */
        NORMAL(-1),
        /**
         * 顶级租户
         */
        ISSUPER(1),

        /**
         * 非顶级租户
         */
        NOSUPER(0);

        private int value;

        SuperTenant(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 区域类型
     */
    enum AreaType {


        /**
         * 小区
         */
        VILLAGE(10000),

        /**
         * 楼栋
         */
        BULIDING(20000),

        /**
         * 楼层
         */
        FLOOR(20100),

        /**
         * 房间
         */
        ROOM(20110),

        /**
         * 停车场
         */
        PRKING(30000),

        /**
         * 分区
         */
        PARTITION(30100),

        /**
         * 车位
         */
        PARKINGLOT(30110),

        /**
         * 出入口
         */
        GATE(40000);

        private int value;

        AreaType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 区域使用状态
     */
    enum UseState {

        /**
         * 使用
         */
        USE(1),

        /**
         * 未使用
         */
        NOTUSED(0);

        private int value;

        UseState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 产品绑定解绑类型
     */
    enum BindingType {
        /**
         * 绑定
         */
        BINDING("1"),


        /**
         * 解绑
         */
        UNBIND("2");

        private String value;

        BindingType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 设备是否删除标志
     */
    enum DeviceDel {
        //未删除
        NO(0),
        //已删除
        YES(1);
        private int value;

        DeviceDel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设备视频产品删除状态
     */
    enum DeviceDelOperate {
        //视频产品不需删除状态
        VIEW(0),
        //视频产品需删除状态
        DEL(1);

        private int value;

        DeviceDelOperate(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 是否需要国标码标识
     */
    enum DeviceHasGbCode {
        //设备包含国标码
        YES(1),
        //设备不包含国标码
        NO(0);

        private int value;

        DeviceHasGbCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 告警总表 状态
     * 0：实时 1：历史
     */
    enum AlarmStatus {
        NOW(0),//实时
        HISTORY(1);//历史
        private int value;

        AlarmStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 告警总表 告警类型
     * 0-设备告警 1-陌生人告警 2-重点人员告警 3-人流告警 4-周界告警  5-非工作时段告警 6-未带口罩告警 7-体温告警 9-黑名单车辆告警 10-车辆长时间未出告警 11-车辆异常进出告警 12-车位有效期到期告警'
     */
    enum AlarmType {
        DEVICE(0),//设备告警
        STRANGER(1),//陌生人告警
        IMPORTANT_PERSON(2),//重点人员告警
        REN_LIU(3),//人流告警
        BORDER(4),//周界告警
        NO_WORK_TIME(5),//非工作时段告警
        NO_FACE_MASK(6),//未带口罩告警
        BODY_TEMPERATURE(7),//体温告警
        GKPW(8),//高空抛物
        PARKING_BACKLIST(9),//停车场 黑名单
        PARKING_LONG_TERM(10),//停车场长时间停留告警
        PARKING_IN_OUT_ABNORMAL(11),//停车场 进出异常
        PARKING_EXPIRE(12), //停车场 有效期告警
        OVER_LINE(13),//跨线违停
        SPECIAL_SPACE(14),//专车专位违停
        FENCE(15), //电子围栏告警
        PERSON_BACKLIST(16),//黑名单人员告警
        PERSON_CAREOUTLIST(17),//关爱人员连续未出门告警
        PERSON_CAREINLIST(18),//关爱人员连续未归告警
        SITE_CONTROL(19),//特殊场所告警
        STRANGER_CONTROL(20),//陌生人布控告警
        EPIDEMIC_CONTROL(21),//疫情布控告警
        ABNORMAL_ELECTRIC_USE(22),//用电异常告警
        ABNORMAL_WATER_USE(23),//用水异常告警
        ABNORMAL_GAS_USE(24),//用气异常告警
        ELECTRIC_USE_EXCEEDS_THRESHOLD(25),//用电超过阈值,
        ELECTRIC_CONSUMPTION_BELOW_THRESHOLD(26),//用电低于阈值
        WATER_USE_EXCEEDS_THRESHOLD(27),//用水超过阈值,
        WATER_CONSUMPTION_BELOW_THRESHOLD(28),//用水低于阈值
        GAS_USE_EXCEEDS_THRESHOLD(29),//用气超过阈值,
        GAS_CONSUMPTION_BELOW_THRESHOLD(30),//用气低于阈值
        VISITOR_RETENTION(31); // 用于滞留访客告警
        private int value;

        AlarmType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 视频人流监控点策略 是否启用人流告警
     *
     * @Author yangjian
     * @Date 2020/7/23
     **/
    enum MonitorWarn {
        //启用
        OPEN(1),
        //关闭
        CLOSE(0);
        private int value;

        MonitorWarn(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    //指令下发执行结果
    enum CmdResult {
        SUCCESS(1, "执行成功"),
        FAIL(2, "执行失败");
        private int value;
        private String desc;

        CmdResult(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 视频人流监控点策略  类型
     *
     * @Author yangjian
     * @Date 2020/7/23
     **/
    enum MonitorFlag {
        IN(0, "入口"),//入口
        //设备不包含国标码
        OUT(1, "出口"),//出口
        WITHIN(2, "范围内");//范围内
        private int value;
        private String desc;

        MonitorFlag(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 视频监控上报数据状态码
     *
     * @Author yangjian
     * @Date 2020/7/24
     **/
    enum CameraCode {
        FACE_GUARD("34320"),//人脸门禁（现在用）
        FACE_GUARD_2("24123"),//人脸门禁（不知道有没有用了，待验证）
        FACE_CAPTURE("24116"),//人脸抓拍 24116
        FACE_CAPTURE2("24117"),//人脸抓拍 24117
        MASK_TEMPERATURE("24121"),//体温口罩
        SEND_RESULT("34301"),//下发结果推送
        UP_AND_DOWN("34302");//设备上下线

        private String value;

        CameraCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 设备模型场景
     *
     * @Author yangjian
     * @Date 2020/7/23
     **/
    interface DeviceModelScenes {
        String MONITOR = "binocularcameras";//人流监控 device_type = 100075
        String GKPW = "gkpw";//高空抛物  device_type = 10043

        interface Energy {//能耗场景
            String WATER = "water";//水表         device_type = 100001
            String WATER_BIG = "waterbig";    //水表  大  device_type=100002
            String GAS = "gas";//燃气表           device_type = 100055
            String ELECTRIC = "electric";//电表   device_type = 100030
        }

        interface CAMERA {//视频监控场景
            String CAMERA = "camera";//视频监控 device_type = 100038
            String GUARD = "guard";//人脸门禁   device_type = 100024
        }

        interface PARKING {//停车场景
            String BARRIER = "barrier";//车辆识别---->道闸 device_type = 100010
            String GEOMAGNETISM = "geomagnetism";//地磁   device_type = 100007
            String PARKING_SPACE_CAMERA = "parkingspacecamera";//车位摄像头 device_type = 31000001
        }

        interface SMOKE {//烟感
            String SMOKE = "smoke";
            String INTELSMOKE = "intelsmoke";
        }

        interface DOORLOCK {//门锁
            String DOORLOCK = "doorlock";
        }

        interface DOORSENSOR {//门磁
            String INTEL_DOORSENSOR = "inteldoorsensor";//智能门磁
        }

        interface ENVIRONMENTAL {//环境
            String INTEL_ENVIRONMENT = "intelenvironment";//智能环境监测
            String ENVIRONMENT = "environment";//环境监测
        }

        interface GASALARM {//燃气报警
            String INTEL_GAS_ALARM = "intelgasalarm";//智能燃气报警
            String GAS_ALARM = "gasalarm";//燃气报警
        }

        //音箱场景
        interface SPEAKER {
            String SOUND = "sound";//智慧音箱-外置音柱         device_type = 100036
            String MIC = "mic";//智慧音箱-播控器           device_type = 100037
            String VOICEBOX = "voicebox";//智慧音箱-收扩机   device_type = 100071
        }

        String STREE_TLAMP = "streetlamp";//路灯

        /**
         * 水质监测
         */
        interface WATERQUALITY {
            String WATER_QUALITY = "waterquality";
        }

        //电子围栏
        String ELECTRONICFENCE = "electronicfence";//

        //土壤监测
        String SOIL = "soil";

        //垃圾箱
        String DUSTBIN = "dustbin";

        interface MANHOLE_COVER {//井盖
            String INTEL_MANHOLE_COVER = "intelmanholecover";//智能井盖
            String MANHOLE_COVER = "manholecover";//井盖
        }
    }

    interface GuideScreenDeviceType {
        Long type = 100062L;      // 停车引导屏
    }

    /**
     * 路灯开关时间类型
     */
    enum StreetlightSwitchType {
        // 根据城市日出日落时间
        CITY_SUNSET_AND_SUNRISE(0),
        // 固定时间
        FIXED_TIME(1);

        private int value;

        StreetlightSwitchType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    /**
     * 路灯周期类型
     */
    enum StreetlightCycleType {
        // 单次
        SINGLE(0),
        // 每天
        EVERY_DAY(1),
        // 每周
        EVERY_WEEK(2);

        private int value;

        StreetlightCycleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 路灯策略状态
     */
    enum StreetlightPolicyStatus {
        // 启用
        ENABLE(1),
        // 禁用
        DISABLE(2);

        private int value;

        StreetlightPolicyStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 路灯分组开启状态
     */
    enum StreetlightGroupStatus {
        // 开启状态
        OPEN(1),
        // 关闭状态
        CLOSE(0);
        private int value;

        StreetlightGroupStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 路灯下发指令结果
     */
    enum StreetlightActionStatus {
        // 成功
        SUCCESS(0),
        // 失败
        FAIL(1);
        private int value;

        StreetlightActionStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 路灯下发指令类型
     */
    enum StreetlightActionType {
        // 开灯
        OPEN(0),
        // 关灯
        CLOSE(1);
        private int value;

        StreetlightActionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 路灯下发指令操作类型
     */
    enum StreetlightOperateType {
        // 自动(定时任务下发)
        AUTOMATIC(0),
        // 手动(用户点击一键开关灯)
        MANUAL(1);
        private int value;

        StreetlightOperateType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 进出标识 0:进 1：出
     */
    enum InOutMark {
        IN("0"),
        OUT("1");

        private String value;

        InOutMark(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 停车场景，地磁上报报文 车辆出入类型
     */
    enum geomagnetismMessageInOutMark {
        // 进
        IN("1"),
        // 出
        OUT("0");

        private String value;

        geomagnetismMessageInOutMark(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 车辆信息表记录是否删除
     * 0-否 1-是
     */
    enum IsDel {
        NO(0),
        YES(1);

        private int value;

        IsDel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 车辆类型 1:固定 2:长租（废弃） 3:临时
     */
    enum CarType {

        FIXED(1, "固定"),
        //RENT(2, "长租"),
        TEMPORARY(3, "临时");
        //UNKNOWN(4, "未知");

        private int value;

        private String name;

        CarType(int value, String name) {
            this.name = name;
            this.value = value;
        }

        public static int getCarTypeByName(String name) {
            for (CarType carType : CarType.values()) {
                if (carType.getName().equals(name)) {
                    return carType.getValue();
                }
            }
            return -1;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 人员类型 1:业主 2:租户 3:外卖 4:快递 5:装修 6:访客  99.其他
     */
    enum ParkingPersonType {
        OWNER(1),
        LESSEE(2),
        TAKEOUT(3),
        EXPRESSAGE(4),
        DECORATION(5),
        CALLER(6),
        STAFF(7),//员工
        OTHER(99);

        private int value;

        ParkingPersonType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 人员类型 1:业主 2:租户 3:外卖 4:快递 5:装修 6:访客  99.其他
     */
    enum PictureType {
        JPG("jpg"),
        ;

        private String value;

        PictureType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 是否为黑名单 0-否 1-是
     */
    enum IsBlack {
        NO(0),
        YES(1),
        ;

        private int value;

        IsBlack(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停车位使用状态
     */
    enum ParkingSpaceStatus {
        // 空闲
        FREE(0),
        // 使用中
        USING(1),
        // 预约
        SUBSCRIBE(2),
        //失联-》未知
        LOSTCONTACT(3);
        private int value;

        ParkingSpaceStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停车位类型
     */
    enum ParkingSpaceType {
        // 固定
        FIXED(1, "固定"),
        // 出租
        LEASE(2, "出租"),
        // 公用
        PUBLIC(3, "公用"),
        //专用
        SPECIAL_PURPOSE(4, "专用"),
        // 非专用
        NOT_SPECIAL_PURPOSE(5, "非专用");
        private int value;

        private String valueStr;

        ParkingSpaceType(int value, String valueStr) {
            this.value = value;
            this.valueStr = valueStr;
        }

        public int getValue() {
            return value;
        }

        public String getValueStr() {
            return valueStr;
        }

        public static String getSpaceStr(int value) {
            ParkingSpaceType[] values = ParkingSpaceType.values();
            for (ParkingSpaceType parkingSpaceType : values) {
                if (parkingSpaceType.getValue() == value) {
                    return parkingSpaceType.getValueStr();
                }
            }
            return "未知";
        }

        public static int getSpaceNum(String name) {
            ParkingSpaceType[] values = ParkingSpaceType.values();
            for (ParkingSpaceType parkingSpaceType : values) {
                if (parkingSpaceType.getValueStr().equals(name)) {
                    return parkingSpaceType.getValue();
                }
            }
            return 0;
        }
    }

    //门锁下发指令类型
    //1.远程开锁 2:打开常开 3:关闭常开 4:添加密码 5.删除密码 6:添加身份证 7:删除身份证 8:添加指纹 9:删除指纹
    enum DoorLockCmdType {
        OPEN_DOOR(1, "远程开锁", "openDoor"),
        SET_NORMALLY_OPEN(2, "打开常开", "normallyOpenDoor"),//指令下发中的常开设置中的
        CLOSE_NORMALLY_OPEN(3, "关闭常开", "normallyOpenDoor"),//指令下发中的常开设置中的
        ADD_PWD(4, "添加密码", "openDoorByPassword"),
        DEL_PWD(5, "删除密码", ""),//delPwd
        ADD_IDENTIFY(6, "添加身份证", ""),//addIdentify
        DEL_IDENTIFY(7, "删除身份证", ""),//delIdentify
        ADD_FINGER(8, "添加指纹", ""),//addFinger
        DEL_FINGER(9, "删除指纹", ""),
        SET_SECRET(10, "设置暗码", ""),//delFinger
        DEL_DOORLOCK(11, "删除卡号", "");//delIdentify

        private int value;
        private String valueStr;
        private String cmdSign;

        DoorLockCmdType(int value, String valueStr, String cmdSign) {
            this.value = value;
            this.valueStr = valueStr;
            this.cmdSign = cmdSign;
        }

        public int getValue() {
            return value;
        }

        public String getValueStr() {
            return valueStr;
        }

        public String getCmdSign() {
            return cmdSign;
        }

        public static int getValueByCmdSign(String cmdSign) {
            DoorLockCmdType[] values = DoorLockCmdType.values();
            for (DoorLockCmdType doorLockCmdType : values) {
                if (doorLockCmdType.getCmdSign().equals(cmdSign)) {
                    return doorLockCmdType.getValue();
                }
            }
            return -1;
        }

        public static String getSpaceStr(int value) {
            DoorLockCmdType[] values = DoorLockCmdType.values();
            for (DoorLockCmdType doorLockCmdType : values) {
                if (doorLockCmdType.getValue() == value) {
                    return doorLockCmdType.getValueStr();
                }
            }
            return "未知";
        }
    }

    /**
     * 授权下发动作
     * 1：添加指纹 2：删除指纹 3：添加卡号 4：删除卡号  5:添加身份证 6:删除身份证
     */
    interface DoorlockAuthAction {
        int ADD_FINGER_IDENTIFIER = 1; //1：添加指纹
        int DEL_FINGER_IDENTIFIER = 2; //2：删除指纹
        int ADD_DOORLOCK_IDENTIFIER = 3; //3：添加卡号
        int DEL_DOORLOCK_IDENTIFIER = 4; //4：删除卡号
        int ADD_IDCARD_IDENTIFIER = 5; //5:添加身份证
        int DEL_IDCARD_IDENTIFIER = 6; //6:删除身份证
    }

    /**
     * 授权下发结果  1:执行成功  2:执行失败
     */
    interface DoorlockAuthResult {
        int SUCCESS = 1; // 1:执行成功
        int FAIL = 2; // 2:执行失败
    }

    /**
     * 授权下发识别码状态  0：失效  1:有效
     */
    interface DoorlockAuthStatus {
        int EFFECTIVE = 0; // 0：失效
        int EXPIRATION = 1; // 1:有效
    }

    /**
     * 获取方式  0:平台下发  1：用户获取
     */
    interface DoorlockAuthLogGetType {
        int PLATFORM = 0; //0:平台下发
        int USERACCESS = 1; //1：用户获取
    }

    /**
     * 授权下发门锁设备产品
     */
    interface DoorlockAuthDeviceProductId {
        int ANXUNTONG = 94; //安迅通门锁
        int RUNCHAN = 92; //若禅门锁
    }

    /**
     * 授权下发授权类型 1:nfc卡  2:身份证  3:指纹 4:密码
     */
    interface DoorlockAuthType {
        int DOORLOCK_IDENTIFIER = 1; //卡号
        int IDCARD_IDENTIFIER = 2; //身份证
        int FINGER_IDENTIFIER = 3; //指纹
        int PASSWORD = 4; //密码
    }


    /**
     * 安迅通门锁操作
     */
    interface ANXUNTONGOPERATETYPE {
        int ADD_DOORLOCK_IDENTIFIER = 0; //新增NFC
        int DEL_DOORLOCK_IDENTIFIER = 1; //删除NFC
    }

    /**
     * 若禅门锁操作
     */
    interface RUNCHANOPERATETYPE {
        int ADD_DOORLOCK_IDENTIFIER = 3; //3：添加卡号
        int DEL_DOORLOCK_IDENTIFIER = 4; //4：删除卡号
        int ADD_IDCARD_IDENTIFIER = 5; //5:添加身份证
        int DEL_IDCARD_IDENTIFIER = 6; //6:删除身份证
        int ADD_FINGER_IDENTIFIER = 7; //1：添加指纹
        int DEL_FINGER_IDENTIFIER = 8; //2：删除指纹
    }

    //门磁
    //1:布防 2:撤防 3:防拆设置开启 4:防拆设置关闭 5:声光报警开启 6:声光报警关闭
    enum DoorSensorCmdType {
        ARMED(1, "布防", "setArmingState"),
        DISARM(2, "撤防", "setArmingState"),
        TAMPER_OPEN(3, "防拆设置开启", "setTamperState"),
        TAMPER_CLOSE(4, "防拆设置关闭", "setTamperState"),
        AUDIBLE_ALARM_OPEN(5, "声光报警开启", "setAudibleAlarm"),
        AUDIBLE_ALARM_CLOSE(6, "声光报警关闭", "setAudibleAlarm");

        private int value;
        private String valueStr;
        private String cmdSign;

        DoorSensorCmdType(int value, String valueStr, String cmdSign) {
            this.value = value;
            this.valueStr = valueStr;
            this.cmdSign = cmdSign;
        }

        public int getValue() {
            return value;
        }

        public String getValueStr() {
            return valueStr;
        }

        public String getCmdSign() {
            return cmdSign;
        }

        public static int getValueByCmdSign(String cmdSign) {
            DoorSensorCmdType[] values = DoorSensorCmdType.values();
            for (DoorSensorCmdType doorSensorCmdType : values) {
                if (doorSensorCmdType.getCmdSign().equals(cmdSign)) {
                    return doorSensorCmdType.getValue();
                }
            }
            return -1;
        }


        public static String getSpaceStr(int value) {
            DoorLockCmdType[] values = DoorLockCmdType.values();
            for (DoorLockCmdType doorLockCmdType : values) {
                if (doorLockCmdType.getValue() == value) {
                    return doorLockCmdType.getValueStr();
                }
            }
            return "未知";
        }
    }

    /**
     * 是否有分区
     */
    enum ParkingHasPartition {
        // 没有
        NO(1),
        // 有
        YES(0);
        private int value;

        public int getValue() {
            return value;
        }

        ParkingHasPartition(int value) {
            this.value = value;
        }

    }

    /**
     * 人脸抓拍告警表 type值
     */
    enum CameraAlarmType {
        STRANGERPERSON(1, "陌生人告警", 20),
        KEYPERSON(2, "重点人员告警", 2),
        MASK(3, "未带口罩告警", 6),
        TEMPERATURE(4, "体温告警", 7),
        SITE_CONTROL(5, "特殊场所布控", 19);//特殊场所告警
        private int value;
        private String desc;
        private int alarmType;//对应告警表iotsaas_alarm的type

        CameraAlarmType(int value, String desc, int alarmType) {
            this.value = value;
            this.desc = desc;
            this.alarmType = alarmType;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public int getAlarmType() {
            return alarmType;
        }

        public static int getAlarmTypeByValue(int value) {
            CameraAlarmType[] values = CameraAlarmType.values();
            for (CameraAlarmType cameraAlarmType : values) {
                if (cameraAlarmType.getValue() == value) {
                    return cameraAlarmType.getAlarmType();
                }
            }
            return -1;
        }

    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 告警类型  1：黑名单车辆告警 2：长时间未离场告警 3：车位有效期到期告警 4：车辆进出异常告警
     */
    enum ParkingAlarmType {
        BLACK(1, "黑名单车辆"),
        LONGTIME(2, "长时间未离场"),
        EXPIRE(3, "车位有效期到期"),
        ABNORMITY(4, "车辆进出异常"),
        OVER_THE_LINE(5, "跨线违停告警"),
        ILLEGAL_USE(6, "专车专位违停");

        private int value;
        private String desc;

        ParkingAlarmType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 停车场场景（车辆识别（道闸）/地磁）
     * 是否启用告警 1是 0否
     **/
    enum ParkingWarn {
        //启用
        OPEN(1),
        //关闭
        CLOSE(0);
        private int value;

        ParkingWarn(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 区域的 删除 状态
     */
    interface AreaIsDel {
        int DEL = 1; // 删除
        int VALID = 0; // 未删除 有效
    }

    /**
     * 0 ,1 状态统一标识
     */
    interface IotSaasDel {
        int status_1 = 1;
        int status_0 = 0;
    }

    /**
     * 视频摄像头类告警策略 状态的开启关闭
     */
    interface CameraPolicyStatus {
        int OPEN = 1;//开启
        int CLOSE = 0;//关闭
    }


    interface IsHit {
        int HIT = 1;//比对
        int NOHIT = 0;//未比对
    }

    /**
     * 人员性质
     */
    interface PersonCategory {
        int HOUSEHOLD = 1;//住户
        int KEYPERSON = 2;//重点人员
        int VISITOR = 3;//访客
        int HOTEL = 4;//住宿
        int STAFF = 5;//员工 会议室
    }

    /**
     * 住户状态
     */
    interface ResidentStatus {
        int USE = 0;//启用
        int CLOSE = 1;//关闭
        int OTHER = 2;//其他
    }


    /**
     * 戴口罩标识  0：否，1：是，2：检查失败，3：没有检测（设备不做检测）
     */
    interface MaskFlag {
        String NOT_MASK = "0";//没带口罩
        String WEAR_MASK = "1";//带口罩
        String CHECK_FAIL = "2";//检查失败
        String NOT_CHECK = "3";//设备没做检测
    }

    /**
     * 图片格式
     */
    interface ImgTypeFormat {
        String TYPE_JPG = "jpg";//jpg格式
    }

    /**
     * 图片的ContentType
     */
    //todo 根据真实设备上传补充
    interface ImgContentType {
        String JPG_CONTEN_TYPE = "image/jpeg";
    }

    /**
     * 门禁出入记录 类型值
     */
    interface GuardRecordType {
        int CARD = 1;//刷卡
        int FACE = 2;//人脸
        int ORDER = 3;//指令
        int PWD = 4;//密码
        int FINGER_PRINT = 5;//指纹
        int BLUETOOTH = 6;//蓝牙
    }

    /**
     * 下发结果通知  数据回传 result值
     */
    interface SendResult {
        int SUCESS = 1;//成功
        int FAIL = 0;//失败
    }

    /**
     * 人员表  人脸添加状态
     */
    interface PersonFaceStatus {
        int NO_ADD = -1;//不添加
        int READY_ADD = 0;//预添加
        int ADD_SUCESS = 1;//添加成功
        int ADD_FAIL = 2;//添加失败
        int DEL_SUCESS = 3;//删除成功
        int DEL_FAIL = 4;//删除失败
    }

    /**
     * 停车场告警策略 类型值
     */
    interface ParkingPolicyType {
        int BLACKLIST = 1;//黑名单
        int LONG_TERM = 2;//长时间未离场
        int EXPIRE = 3; //有效期告警
        int IN_OUT_ABNORMAL = 4;//进出异常
    }

    /**
     * 停车场 车位信息   是否删除状态
     */
    interface ParkingSpaceDel {
        int NOT_DEL = 0;//未删除
        int DEL = 1;//已删除
    }


    /**
     * 进入车辆类型 1:固定 2:长租 3:临时
     */
    interface parkingCarType {

        int FIXED = 1;

        int RENT = 2;

        int TEMPORARY = 3;

    }

    /**
     * user表 是否是超级管理员
     */
    interface UserSuperAdmin {
        int IS_SUPER_ADMIN = 1;//是超级管理员
        int NO_SUPER_ADMIN = 0;//不是超级管理员
    }

    /**
     * user表 是否是超级管理员
     */
    interface UserSuperTenant {
        int IS_SUPER_TENANT = 1;//是顶级租户
        int NO_SUPER_TENANT = 0;//不是顶级租户
    }

    /**
     * user表   状态
     */
    interface UserStatus {
        int STOP = 0;//停用
        int OPEN = 1;//开启正常
    }

    /**
     * user 工作状态
     */
    interface UserWorkStatus {
        int WORKING = 10;//在职
        int QUIT = 20;//离职
        int RETIRE = 30;//退休
        int OTHER = 40;//其他

    }

    /**
     * 停车场告警策略 开始关闭
     */
    interface ParkingPolicyWarn {
        int WARN_OPEN = 1;//告警开启
        int WARN_CLOSE = 0;//告警关闭
    }

    /**
     * 出入口类型
     */
    interface ParkingCategory {
        //小区
        int AREA = 0;
        //停车场
        int PARKING = 1;
    }

    /**
     * 租户人脸库  类型
     */
    interface TenantRepositoryType {
        int FACE = 2;//人脸
        int ACCESS_CONTROL = 1;//门禁
    }

    /**
     * 人脸添加下发结果推送 结果
     */
    interface CameraFaceResultResult {
        int SUCESS = 1;//成功
        int FAIL = 0;//失败
    }

    /**
     * 人脸添加下发结果推送 状态
     */
    interface CameraFaceResultStatus {
        int YES = 1;//要补
        int NO = 0;//不补
    }

    /**
     * 人员性质
     */
    interface PersonType {
        int OWNER = 1;//业主
        int TENANT = 2;//租户
        int TAKE_OUT = 3;//外卖
        int EXPRESS = 4;//快递
        int RENOVATION = 5;//装修
        int VISITOR = 6;//访客
        int STAFF = 7;//员工
        int VISITORSYS = 8; //访客(访客系统)
        int COMPANY_PERSONNEL = 10;//园区企业人员
        int APARTMENT_TENANT = 11;//公寓租户
        int PROPERTY_PERSONNEL = 12;//物业人员
        int OTHER = 99;//其他
    }

    /**
     * 人员性质
     */
    interface UserTag {
        String OWNER = "owner";//业主
        String TENANT = "tenant";//租户
        String MANAGER = "manager";//管理员
        String STAFF = "staff"; //物业
    }

    /**
     * 用户黑名单
     */
    interface PersonBlackList {
        int IS_BLACK = 1;//是黑名单
        int NOT_BALC = 0;//不是黑名单
    }

    /**
     * 设备上下线 事件类型
     */
    interface DeviceOnlineOfflineEvenTtype {
        int ONLINE = 1;//上线
        int OFFLINE = 0;//下线
    }

    /**
     * 视频告警 描述
     */
    interface CameraAlarmNote {
        String STRING_NOTE = "陌生人员"; //陌生人描述
        String BLACK_NOTE = "黑名单人员"; //黑名单描述
        String SITE_NOTE = "非白名单人员";//非白名单 描述
        String EPIDEMIC_NOTE = "疫情人员";//疫情人员 描述
    }

    /**
     * 高空抛物描述
     */
    interface GkpwAlarmNote {
        String DEVICE_NOTE = "设备地址:";//设备描述
        String GKPW_NOTE = "发生高空抛物";//高空抛物描述
    }

    /**
     * 流程定义key
     */
    enum WorkflowDefKey {
        /**
         * 入库流程定义key
         */
        INWORKFLOW("stock_in"),
        /**
         * 出库流程定义key
         */
        OUTWORKFLOW("stock_out");

        private String value;

        WorkflowDefKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 审核状态 (0：待提交审核 1：审核中 2：审核成功 3：审核失败)
     */
    enum AuditStatus {
        /**
         * 待提交审核
         */
        UNSTART(0),
        /**
         * 审核中
         */
        INAUDIT(1),
        /**
         * 审核成功
         */
        PASS(2),
        /**
         * 审核失败
         */
        FAIL(3);

        private int value;

        AuditStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    // ---- 电信学院
    enum EatTimeConfig {
        /**
         * 早餐
         */
        BREAKFAST_START("07:15:00"),
        BREAKFAST_END("09:00:59"),
        /**
         * 午餐
         */
        LUNCH_START("11:15:00"),
        LUNCH_END("13:00:59"),
        /**
         * 晚餐
         */
        SUPPER_START("17:15:00"),
        SUPPER_END("19:00:59");

        private String value;

        EatTimeConfig(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 餐厅人员类型
     */
    enum diningRoomPersonType {
        /**
         * 主办方
         */
        HOST(0, "主办方"),
        /**
         * 学生
         */
        STUDENT(1, "学生"),
        /**
         * 教师
         */
        TEACHER(2, "教师");

        private Integer value;
        private String name;

        diningRoomPersonType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 设备数据推送有感无感
     *
     * @author liujc
     * @Date 2020/1/13 15:05
     */
    enum EatFeelConfig {
        /**
         * 有感
         */
        HASFEEL(1),
        /**
         * 无感
         */
        NONFEEL(2);

        private int value;

        EatFeelConfig(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 就餐类别值
     *
     * @author liujc
     * @Date 2020/1/13 15:06
     */
    enum EatKeyConfig {
        /**
         * 早餐
         */
        BREAKFAST("BREAKFAST"),
        /**
         * 午餐
         */
        LUNCH("LUNCH"),
        /**
         * 晚餐
         */
        SUPPER("SUPPER");

        private String value;

        EatKeyConfig(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 无感设备出入口类型（1、入口  0、出口）
     *
     * @author liujc
     * @Date 2020/1/10 10:32
     */
    enum NonfeelInOutType {
        /**
         * 入口
         */
        IN(1),
        /**
         * 出口
         */
        OUT(0);

        private int value;

        NonfeelInOutType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 食堂就餐人数排行方式
     */
    enum CanteenRankType {
        /**
         * 天
         */
        DAY(1),
        /**
         * 周
         */
        WEEK(2),
        /**
         * 月
         */
        MONTH(3);

        private int value;

        CanteenRankType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum CanteenStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        CanteenStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 厂家类型,1是视频部2是vep,3:宏目，4：拉风
     **/
    enum ManufacturerType {
        //视频部
        VIDEO(1),
        //vep
        VEP(2),
        //弘目
        HONGMU(3),
        //拉风
        FAFENG(4);
        private int value;

        ManufacturerType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum ParkingSpaceCameraUsage {
        Free(0),
        //使用中
        USING(1),
        //跨线违停告警
        OVER_THE_LINE(2),
        //专车专用违停
        ILLEGAL_USE(3);
        private int value;

        ParkingSpaceCameraUsage(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设备属性值配置 类型
     */
    interface DeviceTypeMetricType {
        int NORMAL_DATA = 0;//正常数据
        int ALARM = 1;//告警
        int HEARTBEAT = 2;//心跳
        int CLEAR_SINGLE_WARN = 3;//接触单一告警
        int CLEAR_ALL_WARN = 4;//接触全部告警
    }

    /**
     * 设备动作  是否默认
     */
    interface AlarmActionHasDefault {
        int IS_DEFAULT = 1;//默认
        int NO_DEFAULT = 0;//非默认
    }

    /**
     * 告警动作 开关状态
     */
    interface AlarmActionSwitchStatus {
        int OPEN = 1;//开
        int CLOSE = 0;//关
    }

    /**
     * 设备属性值配置 是否开启阈值
     */
    interface DeviceTypeMetricHasThreshold {
        int OPEN = 1;//开启
        int CLOSE = 0;//关闭
    }

    /**
     * 告警阈值表 阈值类型
     */
    interface AlarmConditionsThresholdType {
        int GREATER_THAN = 1;//大于
        int LESS_THAN = 2;//小于
        int BE_EQUAL_TO = 3;//等于
        int GREATER_THAN_OR_EQUAL = 4;//大于等于
        int LESS_THAN_OR_EQUAL = 5;//小于等于
    }

    /**
     * 告警阈值表 设备条件类型
     */
    interface AlarmConditionsType {
        int EVENT = 1;//事件
        int ATTRIBUTR = 2;//属性
    }

    /**
     * 删除状态
     */
    interface DelStatus {
        int IS_DEL = 1;//已删除
        int NOT_DEL = 0;//未删除
    }

    /**
     * 告警动作 是否执行 TODO 可以备注下哪几个字段共用
     */
    interface AlarmActionsHasValue {
        int NEED = 1;//需要
        int NOT_NEED = 0;//不需要
    }

    /**
     * 设备责任人   工单发送
     */
    interface DeviceDutyOrderSend {
        int NEED = 1;//需要
        int NOT_NEED = 0;//不需要
    }

    /**
     * 设备责任人   短信语音发送
     */
    interface DeviceDutyNoticeSend {
        int NEED = 1;//需要
        int NOT_NEED = 0;//不需要
    }

    /**
     * 设备告警 状态
     */
    interface DeviceAlarmStatus {
        int PENDING = 10;//待处理
        int MANUAL_DISPATCH = 30;//手动派单
        int AUTOMATIC_DISPATCH = 31;//自动派单
        int NO_ORDER = 32;//无派单
        int MANUAL_RELEASE = 40;//手动解除
        int RELEASE_OF_WORK_ORDER = 41;//工单解除
        int NORMAL = 0; // 正常
        int EQUIPMENT_ALARM = 1; // 设备告警
        int BUSINESS_ALARM = 2; // 业务告警
    }

    /**
     * 设备在线状态
     */
    interface DeviceStatus {
        int ONLINE = 1; // 在线
        int OFFLINE = 0; // 离线
    }

    /**
     * 设备告警状态
     */
    interface DeviceWarnStatus {
        int NORMAL = 0; // 正常
        int EQUIPMENT_ALARM = 1; // 设备告警
        int BUSINESS_ALARM = 2; // 业务告警
    }

    /**
     * 报警次数
     */
    int ALARM_TIMES = 1;

    /**
     * 系统派单填0
     */
    Long SYS_SEND = 0L;

    /**
     * 系统 派单  派发者名称
     */
    String SYS_SEND_NAME = "系统派单";
    String TOP_TENANT_SEND_NAME = "顶级租户派单";

    /**
     * 责任人 是否是责任人标识
     */
    interface DeviceDutyFisrtDuty {
        int YES = 1;//是
        int NO = 0;//不是
    }

    /**
     * 工单表 工单类型
     */
    interface OrderType {
        int DEVICE = 1;//设备
    }

    /**
     * 顶顶那 派单类型
     */
    interface OrderDispatchType {
        int AUTOMATIC = 1;//自动生成
        int MANUAL_WORK = 2;//手动
    }

    /**
     * 订单状态
     */
    interface OrderStatus {
        int FINISHED = 1;//已完成
        int WAIT_TO_DO = 2;//等待处理
        int TO_BE_CONFIRMED = 3;//待确认
        int SYS_RELIEVE = 4;//系统解除
    }

    /**
     * 设备工单状态
     */
    interface OrderDeviceStatus {
        int NORMAL = 10;//正常
        int CHANGE = 20;//更换
        int REPAIR = 30;//维修
        int OTHER = 40;//其他
    }

    /**
     * 设备绑定状态
     */
    interface DeviceBind {
        int IS_BIND = 1;//绑定
        int NO_BIND = 0;//没绑定
    }

    /**
     * webscoketUseType
     * 告警用途 0 默认告警，1 电子围栏， 2 车辆进入通知 3 解除告警
     */
    interface webscoketUseType {
        int ALARM = 0;//默认告警
        int ELECTRONIC_FENCE = 1;//电子围栏
        int CARENTER = 2;//车辆进入通知
        int REMOVEALARM = 3;//解除告警
    }

    /**
     * 工单删除 状态
     */
    interface OrderIsDel {
        int IS_DEL = 1; // 删除
        int NOT_DEL = 0; // 未删除 有效
    }

    /**
     * 判断工单转派还是确认
     */
    interface OrderConfirm {
        // 转派
        int TRANSFER = 20;
        // 确认
        int CONFIRM = 10;
    }

    /**
     * 短信模板id 已废弃
     */
      /*  interface SmsTemplateId {
            String DEVICE_ALARM = "91556324";//设备告警
            String DOOR_LOCK_ALARM = "91556325";//门磁告警
            String LOGIN_CODE = "91556326";//登入验证码
            String UPDATE_PHONE = "91556327";//修改手机号
            String VISITOR = "91556328";//访客预约
            String BILL_CALL = "90000249"; //账单催缴
            String MAKE_AN_APPOINTMENT = "90000251";//预约申请
            String ENERGY_TIME_SLOT_USAGE_ABNORMAL="90000252";//能耗 规定时间内 用量异常
            String ENERGY_TIME_SLOT_USAGE_LOWER_THAN="90000254";//能耗昨日低于阈值 用量异常
            String ENERGY_TIME_SLOT_USAGE_GREATER_THAN="90000253";//能耗昨日超过阈值 用量异常
            String BUS_NOTICE="";//公车派车短信模板
        }

        /**
         * 短信模板id
         */
    interface AlarmNote {
        String MASK_ALARM = "当前人员未佩戴口罩经过";//口罩告警
        String BODY_TEMPERATURE_ALARM = "前方出现体温超过37摄氏度人员经过，当前体温为";//体温告警
    }

    /**
     * 设备模块常量定义
     */
    interface Device {
        String guard = "guard";
        String camera = "camera";
        int NOT_DEL = 0;//未删除
        int DEL = 1;//已删除
        int INITIATIVE_PASSIVE = 0;  // 注册方式 0-被动（aep同步）
        int INITIATIVE_ACTIVE = 1; // 注册方式 1-主动（saas注册）
        int DEVICE_ONLINE = 1; // 在线
        int DEVICE_OFFLINE = 0; // 离线
        int DEVICE_WARN_STATUS_0 = 0; // 告警状态 正常
        int has_status = 1; // 有
        int has_no_status = 0; // 无
    }

    interface LinkPolicy {
        int NOT_DEL = 0;//未删除
        int DEL = 1;//已删除
        int ENABLE = 1; //启用
        int DISABLE = 0; //禁用
        int TRIGGER = 1; // 设备触发
    }

    interface Policys {
        int DEFAULT = 1;//未删除
        int NODEFAULT = 0;//已删除
        int ENABLE = 1; //启用
        int DISABLE = 0; //禁用
    }

    /**
     * 人员移动端标识
     */
    enum MobileFlag {
        NO(0),
        YES(1),
        CHECKING(2),
        DENY(3);

        private int value;

        MobileFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 人员告警标识
     */
    enum WarnFlag {
        NO(0),
        YES(1),
        ;

        private int value;

        WarnFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 人员证件类型
     */
    enum LicenseType {
        IDCARD(1),
        RESIDENCE_PERMIT(2),
        VISA(3),
        PASSPORT(4),
        ACCOUNT_BOOK(5),
        MILITARY_ID(6),
        OTHER(99);

        private int value;

        LicenseType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 人员性别
     */
    interface UserGender {
        int man = 0;
        int feman = 1;
        int secret = 2;
    }

    /**
     * 房源策略类型
     */
    interface HotelPolicyType {
        int FAILED_OPEN_DOOR = 1;//长时间未开门告警
        int NOT_CLOSING_DOOR = 2;//长时间未关门告警
    }

    /**
     * 房源策略是否启用
     */
    interface HotelPolicyWarn {
        int ON = 1; //启用
        int OFF = 0; //未启用
    }

    /**
     * 设备责任人是否派单
     *
     * @Author yangjian
     * @Date 2020/9/10
     **/
    interface DeviceDutySendOrder {
        int YES = 1;//是
        int NO = 0;//否
    }

    /**
     * 设备责任人是否派发送短信、短信语音
     *
     * @Author yangjian
     * @Date 2020/9/10
     **/
    interface DeviceDutySendNotice {
        int YES = 1;//是
        int NO = 0;//否
    }

    /**
     * 项目启用名枚举
     */
    enum ProjectName {

        LAFENG("lafeng"),
        NINGXIA("ningxia"),
        OTHER("other");

        private String value;

        ProjectName(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 信号等级的范围值
     */
    interface signalIntensity {
        String LEVEL_TOO_BAD = "-105,-95";//极差
        String LEVEL_BAD = "-95,-85";//差
        String LEVEL_MEDIUM = "-85,-75";//一般
        String LEVEL_GOODS = "-75,null";//极差
    }

    /**
     * 云广播媒体敏感词审核状态
     */
    interface SensitiveAuditStatus {
        int INAUDIT = 0; //待审核
        int PASS = 1; //通过
        int REFUSE = 2; //拒绝
    }

    /**
     * 云广播媒体类型
     */
    interface SpeakerMediaType {
        int TEXT = 1; // 文本
        int AUDIO = 2; // 音频
        int RECORDING = 3; //录音
    }

    /**
     * 云广播媒体类型名称
     */
    interface SpeakerMediaTypeName {
        String TEXT = "文本";
        String AUDIO = "音频";
        String RECORDING = "录音";
    }

    /**
     * 云广播媒体音色
     */
    interface SpeakerMediaVoiceType {
        int FEMALEeVOICE = 0; // 男声
        int MALEVOICE = 1; // 女声
    }

    /**
     * 云广播媒体处理情况
     */
    interface SpeakerMediaProcessStatus {
        int PROCESSING = 0; // 处理中
        int PROCESSINGCOMPLETE = 1; // 加工完成
        int PROCESSINGFAILURE = 2; // 加工失败
    }

    /**
     * 云广播组件推送媒体库状态码
     */
    interface CloudRadioPushMediaStatus {
        String PROCESSINGCOMPLETE = "0"; // 加工完成
        String PROCESSINGFAILURE = "1"; // 加工失败
    }


    /**
     * 云广播媒体共享资源
     */
    interface SpeakerMediaShared {
        int INDIVIDUAL = 0; // 0不共享
        int SHARE = 1; // 1 同租户下可见

    }

    /**
     * 云广播媒体上传状态  0:失败、1:成功
     */
    interface SpeakerMediaUploadStatus {
        int FAIL = 0; // 0失败
        int SUCCESS = 1; // 1 成功

    }

    /**
     * 云广播组件接口返回状态  0：成功  其他：失败
     */
    enum CloudRadioReturnStatus {
        SUCCESS(0);

        private int value;

        CloudRadioReturnStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // 来源0-门户 1-小程序 2-大屏 3-ip话筒
    interface SpeakerTaskSource {
        int PLATFORM = 0;
        int APP = 1;
        int BIGSCREEN = 2;
        int IPMICROPHONE = 3;
    }

    /**
     * 云广播任务相关常量
     */
    interface SpeakerTask {

        int DEFAULT_VOLUME = 11; // 默认音量

        /**
         * 任务播放日期类型
         */
        enum CycleType {

            /**
             * 周期
             */
            CYCLE(0),

            /**
             * 固定日期
             */
            FIXED_DATE(1);

            private int value;

            CycleType(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * 播放形式
         */
        enum PlayMode {

            /**
             * 单次
             */
            SINGLE(0),

            /**
             * 循环
             */
            LOOP(1);

            private int value;

            PlayMode(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * 关系类型
         */
        enum LinkType {

            /**
             * 任务与分组关联
             */
            GROUP(0),

            /**
             * 任务与设备关联
             */
            DEVICE(1);

            private int value;

            LinkType(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * 任务类型
         */
        enum TaskType {

            /**
             * IP话筒任务
             */
            IPMICROPHONE(2),

            /**
             * 日常任务
             */
            DAILY(1),

            /**
             * 应急任务
             */
            URGENT(0);

            private int value;

            TaskType(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * 任务状态
         */
        enum TaskStatus {

            /**
             * 播放中
             */
            PLAYING(0),

            /**
             * 播放成功
             */
            PLAY_SUCCESSFUL(1),

            /**
             * 播放失败
             */
            PLAY_FAIL(2),

            /**
             * 未开始
             */
            NOT_START(3);

            private int value;

            TaskStatus(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * 日常任务策略状态
         */
        enum DailyTaskPolicyStatus {
            /**
             * 启用
             */
            ENABLE(1),

            /**
             * 禁用
             */
            DISABLE(2);

            private int value;

            DailyTaskPolicyStatus(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
    }

    /**
     * 播控器开播和关播
     */
    interface microphoneStatus {
        String BROCASTMICROPHONE = "record"; // 开播指令
        String STOPMICROPHONE = "record_stop"; // 关播指令
    }

    /**
     * 开门方式 -接收到的报文数据
     */
    interface openDoorModeDataReceive {
        int PWD = 1;//密码
        int QR_CODE = 2;//二维码
        int FACE = 3;//人脸识别
        int FINGER_PRINT = 4;//指纹识别
        int APP_OPEN = 6;//app开门
        int PAY_BY_CARD = 7;//刷卡
        int KEY = 8;//钥匙
        int BLUETOOTH = 9;//蓝牙开门
        int TEMPORARY_Password = 10;//临时密码开门
        int INSIDE = 11;//内部开门
        int FIRE = 12;//火灾报警开门
        int NB = 13;//NB开门
        int ID_CARD = 14;//身份证开门
        int ALL_USERS = 15;//全部用户
        int INVALID_CARD = 16;//无效卡
        int COERCION = 17;//胁迫开门
        int LOANG_RANGE = 18;//远程开锁
        int INVALID_TYPE = 255;//无效类型
    }

    /**
     * 开门方式-存储数据
     */
    interface openDoorModeDataStorage {
        int PAY_BY_CARD = 0;//刷卡
        int LOANG_RANGE = 1;//远程开锁
        int QR_CODE = 2;//二维码
        int FACE = 3;//人脸识别
        int FINGER_PRINT = 4;//指纹识别
        int APP_OPEN = 6;//app开门
        int PWD = 7;//密码
        int KEY = 8;//钥匙
        int BLUETOOTH = 9;//蓝牙开门
        int TEMPORARY_Password = 10;//临时密码开门
        int INSIDE = 11;//内部开门
        int FIRE = 12;//火灾报警开门
        int NB = 13;//NB开门
        int ID_CARD = 14;//身份证开门
        int ALL_USERS = 15;//全部用户
        int INVALID_CARD = 16;//无效卡
        int COERCION = 17;//胁迫开门
        int INVALID_TYPE = 255;//无效类型
    }

    /**
     * 门锁上报的数据中 对应门锁表的属性值
     */
    interface deviceDoorlockKey {
        String BATTERY = "batteryLevel";//电量
        String SIGNAL_INTENSITY = "signal_intensity";//信号强度
        String STATUS = "lock_status";//锁状态
        String DOORSTATUS = "door_status";//门状态
    }

    Double DOORLOCK_BATTERY_DIMENSION = 25.00; //门锁电量维度

    Integer AN_XUN_TONG_LOCKSTATUS = 255;//按讯通门锁上传的额外状态

    /**
     * 门锁数据接收  对应的key
     */
    interface DOORLOCK_RECEIVE_KEY {

        interface DEVICE_DATA {//设备上报数据
            String OPEN_DOOR_MODE_KEY = "open_door_mode";//上传的报文中 开门方式的key
            String CARD_TIME = "card_time";//刷卡时间
            String CARD_ID = "card_id";//卡号
            String USER_ID = "user_id";//用户id
            String PASSWORD = "password";//密码开锁对应的数字密码数据
        }

        String RECEIVE_TIME = "receiveTime";//接收时间
    }

    /**
     * 门锁开启成功失败标识
     */
    interface DoorOpenFlag {
        int SUCESS = 1;//成功
        int FAIL = 2;//失败
    }

    /**
     * 工作流业务表流程状态及结果状态
     */

    interface Activiti {

        /**
         * 业务流程状态
         */
        enum Status {

            /**
             * 状态 未开始
             */
            STATUS_TO_APPLY(0),

            /**
             * 状态 处理中
             */
            STATUS_DEALING(1),

            /**
             * 状态 结束
             */
            STATUS_FINISH(2);

            private int value;

            Status(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * 结果状态
         */
        enum Result {

            /**
             * 结果 草稿
             */
            RESULT_TO_SUBMIT(0),

            /**
             * 结果 处理中
             */
            RESULT_DEALING(1),

            /**
             * 结果 审核通过
             */
            RESULT_PASS(2),

            /**
             * 结果 审核失败（驳回、终止）
             */
            RESULT_FAIL(3);

            private int value;

            Result(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        // 工作流 流程KEY
        interface Key {
            // 新建媒体工作流KEY
            String SPEAKERMEDIA = "mediaProcess";
        }
    }


    /**
     * 门锁存储的状态
     */
    interface DoorLockStorageStatus {
        int NOT_KNOW = 0;//未知
        int OPEN = 1;//打开
        int CLOSE = 2;//关闭
    }

    /**
     * 门锁接收的状态
     */
    interface DoorLockReceiveStatus {
        int OPEN = 1;//打开
        int CLOSE = 0;//关闭
    }

    /**
     * 门 存储状态
     */
    interface DoorLockStorageDoorStatus {
        int NOT_KNOW = 0;//未知
        int OPEN = 1;//打开
        int CLOSE = 2;//关闭
    }

    /**
     * 门接收的状态
     */
    interface DoorLockReceiveDoorStatus {
        int OPEN = 1;//打开
        int CLOSE = 0;//关闭
    }

    /**
     * 人员是否删除
     */
    interface PersonDel {
        int DEL = 1;//删除
        int NOT_DEL = 0;//未删除
    }

    /**
     * 人员告警
     */
    interface PersonWarnFlag {
        int YES = 1;//告警
        int NO = 0;//不告警
    }

    /**
     * 是否开通后端标识
     */
    interface StaffWebFlag {
        int NOT_OPEN = 0;//不开通
        int OPEN_WEB = 1;//开通后端
        int BIND = 2;//绑定用户
           /* int YES = 1;//开通
            int NOT = 0;//不开通*/
    }

    /**
     *
     */
    interface StaffMobileFlag {
        int NOT_OPEN = 0;//不开通
        int OPEN_MOBILE = 1;//开通移动端
            /*int YES = 1;//开通
            int NOT = 0;//不开通*/
    }

    /**
     * 通知方式，0=>无，1=>邮件，2=>手机，3=>邮件+手机
     */
    interface NoticeType {
        int NOT = 0;//无
        int EMAIL = 1;//邮件
        int PHONE = 2;//手机
        int EMAILANDPHONE = 3;//邮件+手机
    }

    /**
     * 门锁标识0-默认 1-主要
     */
    interface DoorMask {
        int DEF = 0;//默认
        int MAIN = 1;//主要
    }

    /**
     * 设备告警类型
     */
    String DEVICE_ALARM_DEVICE_METRIC = "device_alarm_device_metric";

    interface DeviceRegType {
        int IOT = 1;//华为
        int AEP = 2;//AEP
        int MANUFACTURE = 3;//厂商
    }

    /**
     * 区域场景
     */
    interface Scenes {
        String MEETING = "meeting";//会议室
        String PARKING = "parking"; // 停车场
        String HOTEL = "hotel";//住宿
    }


    interface SpeakerDeviceType {
        int SPEAKER = 0;//音箱，音柱
        int IP = 1;//IP话筒
    }

    /**
     * 小程序设备装维表 验证状态
     */
    interface DeviceInstallvalidStatus {
        int WAIT_INSTALL = 1;//等待安装校验
        int WAIT_POWER_ON = 2;//待上电校验
        int WAIT_ALARM = 3;//待告警校验
        int ALL_COMPLETE = 4;//全部完成
    }

    /**
     *
     */
    interface PositionCoordinateType {
        int PARKING_SPACE = 1;  //车位
        int ROOM = 2;       // 房间
    }

    /**
     * 当前员工是否运行修改
     */
    interface PersonStaffUpdate {
        int IS_UPDATE = 1;//运行修改
        int NO_UPDATE = 0;//不允许修改
    }

    /**
     * 登入类型
     */
    interface LoginType {
        int PHONE_LOGIN = 1;//手机号登入
        int PWD_LOGIN = 2;//密码登入
    }

    /**
     * 数据是否合格
     */
    interface DataQualified {
        int IS_QUALIFIED = 1;//合格
        int NO_QUALIFIED = 0;//不合格
    }

    /**
     * 环境监测设备  心跳
     */
    interface EnvironmentHeartData {
        String PM25 = "pm25"; //PM2.5
        String PM10 = "pm10";//PM10
        String TEMPERATURE = "temperature";//温度
        String HUMIDITY = "humidity";//湿度
        String WIND = "wind";//风速
        String RAIN = "Rain";//雨量
        String WINDDIR = "WindDir";//风向
        String PRESSURE = "pressure";//气压
        String VALUE = "value";//指标值
        String METRIC = "metric";//指标
    }

    /**
     * 水质监测数据  心跳
     */
    interface WaterHeartData {
        String CONDUCTIVITY = "conductivity";//电导率
        String WATER_TURBIDITY = "water_turbidity";//水质浊度
        String PH = "ph"; //ph
        String VALUE = "value";//指标值
        String METRIC = "metric";//指标
    }

    /**
     * 土壤监测心跳数据
     */
    interface SoilHeartData {
        String SOIL_MOISTURE = "soil_moisture";//突然湿度
        String SOIL_PH = "soil_ph";//土壤ph
        String SOIL_TEMPERATURE = "soil_temperature"; //土壤温度
        String VALUE = "value";//指标值
        String METRIC = "metric";//指标
    }

    /**
     * 空气监测  pm等级
     */
    interface pmLevel {
        int EXCELLENT = 1;//优 0-50
        int GOOD = 2;//良 50-100
        int LINGHT = 3;//轻度  100-150
        int MODERATE = 4;//中度污染 150-200
        int SEVERE = 5;//重度污染 200-300
        int SERIOUS = 6;//严重污染  大于300
    }

    interface pmLevelStr {
        String EXCELLENT = "优";//优 0-50
        String GOOD = "良";//良 50-100
        String LINGHT = "轻度污染";//轻度  100-150
        String MODERATE = "中度污染";//中度污染 150-200
        String SEVERE = "重度污染";//重度污染 200-300
        String SERIOUS = "严重污染";//严重污染  大于300
    }

    /**
     * 联动策略
     */
    interface TriggerPolicy {
        /**
         * 策略触发条件逻辑且和或
         */
        interface ConditionLogic {
            int OR = 0;  //或
            int AND = 1;   //且
        }

        /**
         * 触发条件类型 条件类型： 1：事件，2：阈值
         */
        interface ConditionType {
            int EVENT = 1;  // 1：事件
            int THRESHOLD = 2; // 2：阈值
        }

        /**
         * 阈值判断逻辑  (1:>,2:<,3:=,4:>=,5:<=)
         */
        interface ThresholdLogic {
            int GT = 1;
            int LT = 2;
            int EQ = 3;
            int GTE = 4;
            int LTE = 5;
        }
    }

    interface ShengYangMonthCard {
        interface operType {
            String ADD = "1";
            String DELETE = "2";
            String UPDATE = "3";
        }

        interface carType {
            String MONTH = "2"; //月卡
        }

        interface cardStatus {
            int NORMAL_USING = 0; // 正常使用
            int BLACK = 1;  // 黑名单
            int REFUND_CARD = 5; // 退卡
        }
    }

    interface ParkingSpaceDeviceCategory {
        int GEOMAGNETISM = 1;   // 地磁
        int PARKING_SPACE_CAMERA = 2; // 车位摄像头
    }

    interface ParkingRecordType {
        int NOW = 0;//实时
        int HISTORY = 1;//历史
    }

    interface ParkingOnSiteVehicleType {
        String THIS_MONTH_TOTAL_TRAFFIC = "1";  // 本月总车流
        String THIS_MONTH_TEMPORARY_TRAFFIC = "2";  // 本月临时车流
        String THIS_MONTH_FIXED_TRAFFIC = "3";  // 本月总车流

    }

    /**
     * 环境监测风向
     */
    interface EnvironmentWindDir {
        String NORTH = "北";//0
        String NORTHEAST = "东北";//1
        String EAST = "东";//2
        String SOUTHEAST = "东南";//3
        String SOUTH = "南";//4
        String SOUTHWEST = "西南";//5
        String WEST = "西";//6
        String NORTHWEST = "西北";//7
    }

    /**
     * 水表（大）设备类型ID
     */
    String WATER_BIG_TYPE_ID = "100002";
    /**
     * 水表（大）设备类型ID
     */
    String WATER_SMALL_TYPE_ID = "100001";

    /**
     * 电表设备类型ID
     */
    String ELECTRIC_TYPE_ID = "100030";

    /**
     * 水质设备类型id
     */
    String WATER_DEVICE_TYPE_ID = "100054";

    /**
     * 土壤设备类型id
     */
    String SOIL_DEVICE_TYPE_ID = "100047";

    /**
     * 环境设备类型id
     */
    String ENV_DEVICE_TYPE_ID = "100017";

    /**
     * 垃圾桶设备类型id
     */
    String GAR_DEVICE_TYPE_ID = "100022";

    /**
     * 设备类型id
     */
    interface DeviceTypeId {
        Long ELECTRONIC_FENCE_DEVICE_ID = 31000002L;//电子围栏
        Long GUAN_LIN_DOOR_LOCK_DEVICE_TYPE_ID = 100029L;//冠林门锁
        Long WATER_LEVEL_MONITOR_TYPE_ID = 100060L;//水质检测设备类型id
        String WATER_DEVICE_TYPE_ID = "100002,100001";//水表设备类型id
        String GAS_DEVICE_TYPE_ID = "100055";//燃气设备类型id
        String ELECTRIC_DEVICE_TYPE_ID = "100030";//电表设备类型
        String GAS_ALARM_DEVICE_TYPE_ID = "10000017";//燃气报警设备类型id
        String DOOR_LOCK_DEVICE_TYPE_ID = "100029";//门锁设备类型id
        String DOOR_MAGNETISM_DEVICE_TYPE_ID = "10000019";//门磁设备类型id
        String SMOKE_DEVICE_TYPE_ID = "10000002,100004";//烟感设备类型id
        String PARKING_LOCK_DEVICE_TYPE_ID = "100067";//车位锁设备类型id
        String CAMERA_DEVICE_TYPE_ID = "100038";//摄像头设备类型id
        String FIRE_HYDRANT_DEVICE_TYPE_ID = "100003";//消火栓水压监测
        String SOUND_DEVICE_TYPE_ID = "100036";//外置音柱
        String VOICE_BOX_DEVICE_TYPE_ID = "100071";//收扩机
    }

    /**
     * 房源房间设备类型
     */
    interface HotelRoomDeviceType {
        int DOOR_LOCK = 1;//门锁
        int WATER = 2;//水表
        int ELECTRIC = 3;//电表
        int GAS = 4;//燃气
        int SMOKE = 5;//烟感
        int DOOR_MAGNETISM = 6;//门磁
        int GAS_ALARM = 7;//燃气告警
    }

    /**
     * 环境监测头部统计指标
     */
    String ENVI_TOP_COUNT_METRIC = "temperature,pressure,Rain";
    /**
     * 环境监测底部统计指标
     */
    String ENVI_BOTTOM_COUNT_METRIC = "pm25,pm10";

    /**
     * 水质监测统计指标
     */
    String WATER_COUNT_METRIC = "ph,water_turbidity,conductivity";

    /**
     * 土壤监测统计指标  顺序不能调
     */
    String SOIL_COUNT_METRIC = "soil_ph,soil_temperature,soil_moisture";

    /**
     * 门锁设备类型id
     */
    String DOOR_LOCK_TYPE_ID = "100029";

    /**
     * 环境监测  IAQI
     */
    Integer[] IAQI = {0, 50, 100, 150, 200, 300, 400, 500};

    /**
     * 环境监测PM25
     */
    Integer[] PM25 = {0, 35, 75, 115, 150, 250, 350, 500};

    //一周天数
    Integer WEEK_DAY_NUM = 7;
    //一个月天数
    Integer MONTH_DAY_NULL = 30;

    //路灯上报的报文数据为数组的key
    String[] STREET_LAMP_DATA_KEY = {"voltage", "electric_quantity", "brightness", "lighting_status", "power", "signal_intensity", "powerfactory", "mode", "electricity", "reserve", "energy", "dim", "light_time", "light_count", "lux", "angle", "lcu_date"};
    //路灯上报 告警类型 的key
    String STREET_LAMP_ARLAM_TYPE_KEY = "alarm_type";

    //路灯设备类型id
    Long STREET_LAMP_DEVICE_TYPE_ID = 100011L;

    //导出类型
    interface ExportType {
        int ALL = 1;//全部导出
        int CHECK = 2;//勾选导出
    }

    /**
     * 短信开放平台  短信模板id
     */
    interface SmsOtherTemplateId {
        String DEVICE_ALARM = "90000233";//设备告警
        String DOOR_LOCK_ALARM = "90000237";//门磁告警
        String LOGIN_CODE = "90000236";//登入验证码
        String UPDATE_PHONE = "90000234";//修改手机号
        String VISITOR = "90000235";//访客预约
        String UPDATE_PWD = "90000238";//修改密码
        String MEETING_START = "90000232";//会议开始前
        String MEETING_ORDER = "90000231";//会议预约
        String SECURITY_CONTROL_BLACK = "90000240";//黑名单布控告警
        String SECURITY_CONTROL_CARE = "90000241";//关爱人员布控告警
        String SECURITY_CONTROL_SITE = "90000242";//特殊场所布控告警
        String SECURITY_CONTROL_EPIDEMIC = "90000243";//疫情布控告警
        String SECURITY_CONTROL_STRANGER = "90000244";//陌生人布控告警
        String ENERGY_ALARM_TEMPLATE = "90000245";//能耗布控告警
        String CONVENIENTLY_TRANSFER_ORDER = "90000250";//随手拍转单
        String BUS_NOTICE = "90000255";//公车派车短信模板-司机
        String BUS_APPLY_NOTICE = "90000256";//公车派车短信模板-申请人
        String BUS_NOTICE_MANAGE = "90000261";//公车派车短信模板-通知管理人手机号
        String BUS_NOTICE_ASSIGN = "90000262";//公车派车短信模板-通知车队长手机号
        String BUS_CANCEL_NOTICE_DRIVER = "90000263";//公车派车短信模板-取消申请通知司机
        String BUS_CANCEL_NOTICE_ASSIGN = "90000264";//公车派车短信模板-取消申请通知车队长
        String BUS_EDIT_NOTICE_DRIVER = "90000265";//公车派车短信模板-修改申请通知司机
        String BUS_EDIT_NOTICE_ASSIGN = "90000266";//公车派车短信模板-取消申请通知车队长
        String BILL_CALL = "90000249"; //账单催缴
        String MAKE_AN_APPOINTMENT = "90000251";//预约申请
        String ENERGY_TIME_SLOT_USAGE_ABNORMAL = "90000252";//能耗 规定时间内 用量异常
        String ENERGY_TIME_SLOT_USAGE_LOWER_THAN = "90000254";//能耗昨日低于阈值 用量异常
        String ENERGY_TIME_SLOT_USAGE_GREATER_THAN = "90000253";//能耗昨日超过阈值 用量异常
        String DINING_APPOINTMENT_REMIND = "90000260";//菜品预约取餐提醒
        String REALTY_REPAIR_REMIND = "90000258";//随手拍超时提醒
        String ORDER_REMIND = "90000259";//工单超时提醒
        String CLEANER_NOTICE = "90000267";//保洁指派通知保洁员
        String CONTRACT_EXPIRE_NOTICE = "90000268";//合同到期告警通知
        String ASSETS_NOTICE = "90000269"; // 设备资产到期提醒
        String ENVIRONMENTAL_SANITATION = "90000270";// 智慧环卫告警催缴
        String FIREFIGHTING_SMS_TEMPLATE_FIRST = "90000271"; //消防预案
        String FIREFIGHTING_SMS_TEMPLATE_SECOND = "90000272";//消防预案
    }

    /**
     * APP端登入权限
     */
    interface AppUserLoginAuthority {
        String ZW = "1";//装维
        String WY = "2";//物业
        String ZY = "3";//社区住户
        String YX = "4";//音箱
        String DW = "5";//党委
    }

    /**
     * app默认密码
     */
    String APP_DEFAULT_PWD = "980518c6dd7a71b7774a5d5e946d3e7090dbb51dca9ce80a432866ce3f0984db";

    /**
     * 当前用户是否可以派发工单
     */
    interface CurrentUserIsOrder {
        int SEND_ORDER = 1;//已派发工单
        int NO_SEND_ORDER = 0;//未派发工单
        int ORDER_INFO = 2;//工单信息
    }

    /**
     * 当前用户是否为工单的接收者
     */
    interface UserIsReceiver {
        int YES = 1;//是
        int NO = 0;//不是
    }

    /**
     * 共动感确认状态
     */
    interface OrderConfirmStatus {
        int SUCESS_STATUS = 1;//成功
        int FAIL_STATUS = 0;//失败
    }

    /**
     * 用户是否可以确认工单
     */
    interface UserCanConfirmOrder {
        int YES = 1;//可以确认工单
        int NO = 0;//不能确认工单
    }

    /**
     * 工单创建类型
     */
    interface OrderCreateType {
        int SEND = 1;//派发
        int TRANSFER = 2;//转派
    }

    /**
     * 工单派发类型
     */
    interface OrderSendType {
        int SYS = 0;//系统
        int SUPER_TENANT = 1;//顶级租户
        int STAFF = 2;//员工
    }

    /**
     * 人脸下发记录操作类型
     */
    interface CameraFaceLogOperationType {
        int ADD = 1;//新增
        int DEL = 2;//删除
    }

    /**
     * 人脸下发记录人像库类型
     */
    interface CameraFaceLogRepositoryType {
        int GUARD = 1;//门禁
        int FACE = 2;//人脸
    }


    /**
     * 人脸下发记录删除标识
     */
    interface CameraFaceLogDel {
        int DEL = 1;//删除
        int NOT_DEL = 0;//未删除
    }

    /**
     * 人脸下发记录状态标识
     */
    interface CameraFaceLogStatus {
        int SUCESS = 1;//成功
        int FAIL = 0;//失败
    }

    interface TenantRepositoryCreateResult {
        int SUCCESS = 1;//成功
        int FAIL = 0;//失败
    }

    /**
     * 布控类型标识
     */
    interface SECURITYCONTROLPOLICY {
        int BLACK = 1;//黑名单布控
        int CARE = 2;//关爱人员布控
        int SITE = 3;//特殊场所布控
        int EPIDEMIC = 4;//疫情布控
        int STRANGER = 5;//陌生人布控
    }

    /**
     * 布控类型标识
     */
    interface SECURITYCONTROLPOLICYRULE {
        int CHECKED = 1;//选中
        int UNCHECKED = 0;//未选中
    }

    /**
     * 住户是否关联住户
     */
    interface RedisentIsRelation {
        int IS_RELATION = 1;//关联
        int IS_NOT_RELATION = 0;//不关联
    }

    /**
     * 安全布控人员类型
     */
    interface CONTROLPERSONTYPE {
        String OPTIONAL = "0";//自选人员
        String black = "1";//黑名单人员
        String CARE = "2";//关爱人员
    }

    /**
     * 布控通知
     * 是否语音通知 0:否  1:是
     */
    interface SECURITYCONTROLVOICENOTICE {
        int YES = 1;//是
        int NO = 0;//不是
    }

    /**
     * 布控通知
     * 是否短信通知 0:否  1:是
     */
    interface SECURITYCONTROLSMSNOTICE {
        int YES = 1;//是
        int NO = 0;//不是
    }

    /**
     * app用户审核状态
     */
    interface AppUserCheckStatus {
        int WAIT_CHECK = 0;//待审核
        int CHECK_SUCESS = 1;//审核通过
        int CHECK_FAIL = 2;//审核失败
    }

    /**
     * app用户是否需要审核
     */
    interface AppUserNeedCheck {
        int IS_NEED_CHECK = 1;//需要审核
        int IS_NOT_NEED_CHECK = 0;//不需要审核
    }

    /**
     * app用户状态
     */
    interface AppUserStatus {
        int STOP_USING = 0;//停用
        int NORMAL = 1;//正常
    }

    /**
     * 与业主关系
     */
    interface PersonResidentOwnerRelation {
        int NO = 0;//无
        int SPOUSE = 1;//配偶
        int SON = 2;//儿子
        int DAUGHTER = 3;//女儿
        int GRANDCHILDREN = 4;//孙子 孙女 外孙子 外孙女
        int PARENT = 5;//父母
        int GRANDPARENTS = 6;//祖父母或外祖父母
        int BROTHERS = 7;//兄弟姐妹
        int OTHER_RELATIVES = 8;//其他亲属
        int SON_IN_LAW = 9;//女婿
        int DAUGHTER_IN_LAW = 10;//儿媳
        int IS_TENANT = 11;//租户
        int IS_RESIDENT = 12;//业主
    }

    /**
     * 是否是业主
     */
    interface PersonResidentIsOwner {
        int IS_OWNER = 1;//是业主
        int NOT_OWNER = 0;//不是业主
    }

    /**
     * 住户类型
     */
    interface PersonResidentType {
        int IS_RESIDENT = 1; //业主
        int IS_TENANT = 2;//租户
    }

    /**
     * app端标识
     */
    interface AppItemSign {
        String ZHUANGWEI = "zhuangwei";//装维
        String SHEQU = "shequ";//社区
        String SPEAKER = "speaker";//音箱
        String DANGWEI = "dangwei";
    }

    /**
     * 公告发布
     */
    interface NoticeIsRelease {
        int IS_RELEASE = 1;//已发布
        int NOT_RELEASE = 0;//未发布
    }

    /**
     * 能耗设备总分关系表  设备类型
     **/
    enum EnergyDeviceRelationDeviceType {
        ELECTRICE("100030", 1), //电表
        WATER("100001,100002", 2), //水表
        GAS("100055", 3);//燃气

        private String deviceTypeId;
        private int deviceType;

        EnergyDeviceRelationDeviceType(String deviceTypeId, int deviceType) {
            this.deviceTypeId = deviceTypeId;
            this.deviceType = deviceType;
        }

        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public int getDeviceType() {
            return deviceType;
        }
    }

    /**
     * 能耗设备类型
     */
    interface EnergyDeviceType {
        //电表
        int ELECTRICE = 1;
        //水表
        int WATER = 2;
        //燃气
        int GAS = 3;
    }

    /**
     * 是否平衡
     */
    interface EnergyLossAnalysisIsBalance {
        int IS_BALANCE = 1;//平衡
        int IS_NOT_BALANCE = 0;//不平衡
    }

    /**
     * 能耗损耗率平衡值  百分比单位
     */
    Double LOSS_RATE_BALANCE_VALUE = 2.0;

    /**
     * 设备单位
     */
    interface DeviceUnit {
        String ELECTRICE = "kwh";//电表单位
        String WATER = "m³";//水表单位
        String GAS = "m³";//气表单位
    }

    enum TenantEnergyMode {
        USE(1),
        NOT_USED(0);

        private Integer code;

        TenantEnergyMode(Integer code) {
            this.code = code;
        }

        public Integer getValue() {
            return code;
        }
    }

    enum EnergyType {
        ELECTRICE("electric", 1), //电表
        WATER("water", 2), //水表
        GAS("gas", 3);//燃气

        private String typeName;

        private Integer deviceType;

        EnergyType(String typeName, Integer deviceType) {
            this.typeName = typeName;
            this.deviceType = deviceType;
        }

        public Integer getDeviceType() {
            return deviceType;
        }

        public String getDeviceTypeName() {
            return typeName;
        }
    }

    interface EnergyDateType {
        String DAY = "day";
        String WEEK = "week";
        String MONTH = "month";
    }

    /**
     * 能耗报表类型
     */
    interface EnergyReportType {
        int MONTH = 1;//月

        int QUARTERLT = 2;//季度

        int YEAR = 3;//年
    }

    /**
     * 能耗走私统计类型
     */
    interface EnergyTrendCountType {
        int DAY = 1;//日
        int MONTH = 2;//月
    }

    /**
     * 能耗报表停机类型
     */
    interface EnergyReportCountType {
        int ALL = 1;//全部

        int QUARTERLT = 2;//住户
    }

    /**
     * 能耗告警类型
     */
    interface EnergyAlarmType {
        //用电异常
        int ABNORMAL_ELECTRIC_USE = 1;
        //用电超过阈值
        int ELECTRIC_USE_EXCEEDS_THRESHOLD = 2;
        //用电低于阈值
        int ELECTRIC_CONSUMPTION_BELOW_THRESHOLD = 3;

        //用水异常
        int ABNORMAL_WATER_USE = 4;
        //用水超过阈值
        int WATER_USE_EXCEEDS_THRESHOLD = 5;
        //用水低于阈值
        int WATER_CONSUMPTION_BELOW_THRESHOLD = 6;

        //用气异常
        int ABNORMAL_GAS_USE = 7;
        //用气超过阈值
        int GAS_USE_EXCEEDS_THRESHOLD = 8;
        //用气低于阈值
        int GAS_CONSUMPTION_BELOW_THRESHOLD = 9;
    }

    /**
     * 能耗布控告警通知方式
     */
    interface EnergyAlarmNoticeType {
        int VOICE = 1;//语音
        int MESSAGE = 2;//短信
    }

    /**
     * 顶顶那 派单类型
     */
    interface EnergyOrderDispatchType {
        int MANUAL_WORK = 1;//手动

        int AUTOMATIC = 2;//自动生成
    }

    /**
     * 指令下发参数是否需要替换
     */
    interface CommandIsNeedUpdateParams {
        int IS_NEED = 1;//需要
        int NOT_NEED = 0;//不需要
    }

    /**
     * 设备产品id
     */
    interface DeviceProductId {
        Long TAI_HUA = 184L;
        Long GUAN_LIN_DOOR_LOCK_PRODUCT_ID = 1105L;//冠林门锁
        Long LA_FENG_VIDEO_PRODUCT_ID = 1106L;//拉风摄像头
    }

    /**
     * 住宿人员是否删除
     */
    interface HotelPersonDel {
        int DEL = 1;//已删除
        int NOT_DEL = 0;//未删除
    }

    /**
     * 房源公摊项目费用类型
     */
    interface HotelPublicItemType {
        int WATER = 1; // 水费
        int ELECTRICITY = 2; // 电费
        int ELEVATOR = 3; // 电梯费
        int OTHER = 4; // 其他
    }

    /**
     * 房源公摊项目费用计费方式
     */
    interface HotelPublicItemChargeWay {
        int FIXED = 0; // 固定
        int TIME_SHARING = 1; // 分时
    }

    enum HotelPublicItemTypeName {
        WATER(1, "公摊水费"),
        ELECTRICITY(2, "公摊电费"),
        ELEVATOR(3, "公摊电梯费"),
        OTHER(4, "其他公摊费");
        private int type;

        private String name;

        HotelPublicItemTypeName(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public static String getNameByType(Integer type) {
            if (Objects.isNull(type)) {
                return "未知";
            }
            HotelPublicItemTypeName[] values = HotelPublicItemTypeName.values();
            for (HotelPublicItemTypeName hotelPublicItem : values) {
                if (hotelPublicItem.type == type) {
                    return hotelPublicItem.name;
                }
            }
            return "未知公摊费用";
        }

    }

    enum HotelPublicItemRuleName {
        FAMILY(1, "按户均摊"),
        AREA(2, "按面积均摊");
        private int rule;

        private String name;

        HotelPublicItemRuleName(int rule, String name) {
            this.rule = rule;
            this.name = name;
        }

        public static String getNameByType(Integer rule) {
            if (Objects.isNull(rule)) {
                return "未知";
            }
            HotelPublicItemRuleName[] values = HotelPublicItemRuleName.values();
            for (HotelPublicItemRuleName hotelPublicItem : values) {
                if (hotelPublicItem.rule == rule) {
                    return hotelPublicItem.name;
                }
            }
            return "未知公摊规则";
        }

    }

    /**
     * 房源公摊项目计费规则
     */
    interface HotelPublicItemRule {
        int FAMILY = 1; // 按户均摊
        int AREA = 2; // 按面积均摊
    }

    interface HotelOnlinePayApplyMerchantType {
        int PERSONAL = 1; // 个人
        int BUSINESS = 2; // 企业
    }

    interface HotelOnlinePayApplyCheckStatus {
        int PENDING_APPROVAL = 1; // 待审批
        int REFUSE = 2; // 拒绝
        int AGREE = 3; // 同意
        int NOT_APPLIED = 4; // 未申请
    }

    /**
     * 房源查询类型
     */
    interface HotelListType {
        int LIST = 1;//列表
        int BLOCK = 2;//方块
    }

    /**
     * 房源类型
     */
    interface HotelType {
        int APARTMENT = 1; //公寓/出租房
        int TO_WORK = 2;//办公场所/产业园区/商业办公
        int DORMITORY = 3;//宿舍
    }

    /**
     * 房源房间是否可以租住
     */
    interface HotelRoomIsRent {
        int YES = 1;//可以租住
        int NO = 0;//不可以租住
    }

    /**
     * 房源房间能耗计费方式
     */
    interface HotelRoomEnergyrentChargeWay {
        int FIXED = 0;//固定收费
        int TIME_SHARING = 1;//分时收费
    }

    /**
     * 房源房间物业费收费类型
     */
    interface HotelRoomPropertyType {
        int FIXED = 0;//固定物业费
        int SQUARE = 1;//按面积收费
    }

    /**
     * 房源房间状态
     */
    interface HotelRoomStatus {
        int VACANT_ROOM = 0;//空房
        int ALREADY_LIVED = 1;//已住
    }

    /**
     * 房源房间账单状态
     */
    interface HotelRoomBillStatus {
        int OUTSTANDING = 0;//未出账
        int IS_PAY = 1;//已支付
        int IS_NOT_PAY = 2;//未支付

    }

    /**
     * 房间宿舍类型
     */
    interface HotelRoomAccommodationType {
        int BOY = 0;//男生宿舍
        int GIRL = 1;//女生宿舍
        int BOY_AND_GIRL = 2;//男女混合
    }

    /**
     * 房源账单支付方式
     */
    interface HotelPayWay {
        int WECHAT = 1; // 微信
        int ALIPAY = 2; // 支付宝
        int UNIONPAY = 3; // 银联
        int CASH = 4; // 现金
        int OTHER = 5; // 其他
    }

    /**
     * 水费计费方式
     */
    interface WaterRentChargeWay {
        int FIXED_UNIT_PRICING = 0; //固定单价计费
        int TIME_SHARING_BILLING = 1; //分时计费
    }

    /**
     * 电费计费方式
     */
    interface ElectricRentChargeWay {
        int FIXED_UNIT_PRICING = 0; //固定单价计费
        int TIME_SHARING_BILLING = 1; //分时计费
    }

    /**
     * 燃气计费方式
     */
    interface GasRentChargeWay {
        int FIXED_UNIT_PRICING = 0; //固定单价计费
        int TIME_SHARING_BILLING = 1; //分时计费
    }

    /**
     * 物业费计费方式
     */
    interface PropertyChargeWay {
        int FIXED_CHARGE = 0; //固定计费
        int CHARGE_BY_AREA = 1; //按面积计费
    }

    /**
     * 住宿房间的 人员类型
     */
    interface HotelRoomPersonType {
        int STUDENT = 1;//学生
        int TEACHER = 2;//老师
        int COMPANY = 3;//公司
        int APARTMENT = 4;//公寓
    }

    /**
     * 住户是否是关爱人员
     */
    interface PersonResidentCareFlag {
        int YES = 1;//是
        int NO = 0;//不是
    }

    /**
     * 是否是特殊人群
     */
    interface PersonResidentSpecialFlag {
        int NOT = 0;//不是
        int OLD_PERSON = 1;//老人
        int CHILD = 2;//小孩
    }

    /**
     * 若禅门锁下发 暗码设置
     */
    interface NormallyOpenSet {
        String OPEN_CIPHER = "0400";//打开暗码固定传0400(暂不用)
        String OPEN_NORMALLY_OPEN = "1000"; //常开打开
        String CLOSE_NORMALLY_OPEN = "0000"; //关闭常开
        String OPEN_CIPHER_AND_OPEN_NORMALLY_OPEN = "1400";//同时下发暗码和常开传
    }

    /**
     * 若禅门锁下发 常开
     */
    interface NormallyOpenSetIsOpen {
        int OPEN = 1;//打开
        int CLOSE = 0;//关闭
    }

    /**
     * 设备是否需要指令下发
     */
    interface DeviceIsNeedCommand {
        int NEED = 1;//需要
        int NOT_NEED = 0;//不需要
    }

    /**
     * 账单状态
     */
    interface HotelBillStatus {
        int UNPAID = 1; // 未支付
        int PAID = 2; // 已支付
        int CANCEL = 3; // 作废
    }

    /**
     * 能耗关系等级
     */
    interface EnergyRelationLevel {
        int FIRST_LEVEL = 1;//一级
        int SECOND_LEVEL = 2;//二级
    }

    //指令下发渠道  1:小程序  2:web
    interface SendCmdChannel {
        int APP = 1; //web端
        int WEB = 2; //小程序端
    }

    /**
     * 门锁常开
     */
    interface DoorlockOpen {
        int OPEN = 1;//开
        int CLOSE = 0;//关
    }

    interface HotelBillTreeDataType {
        int HOUSE_RESOURCE = 0; // 房源
        int BUILDING = 1; // 楼栋
        int FLOOR = 2; // 楼层
        int ROOM = 3; // 房间
        int BILL = 4; // 订单
    }

    /**
     * 产品功能指令下发映射表 sign
     */
    interface ProductCommandDictCommandSign {
        interface DoorLock {//门锁
            String NORMALLY_OPEN_DOOR = "normallyOpenDoor";//常开
            String OPEN_DOOR = "openDoor";//远程开锁
        }
    }

    /**
     * 房屋合同状态
     */
    interface HotelRoomContractStatus {
        int TAKE_EFFECT = 0;//生效
        int TERMINATION = 1;//终止
        int INVALID = 2;//已作废
        int NOT_IN_FORCE = 3;//未生效
    }

    /**
     * 账单配置是否延迟
     */
    interface HotelRoomBillConfigCtrl {
        int YES = 1;//是
        int NOT = 0;//否
    }

    /**
     * 账单类型
     */
    interface HotelBillType {
        int ROOM_RENT = 1;//房租账单
        int DEPOSIT = 2;//押金账单
        int RENT_REFUND = 3;//退租账单
        int PROPERTY = 4;//物业费
        int WATER = 5;//水费账单
        int ELECTRIC = 6;//电费账单
        int GAS = 7;//燃气
        int SHARING_WATER = 8;//公摊水费账单
        int SHARING_ELECTRIC = 9;//公摊电费账单
        int SHARING_ELEVATOR = 10;//公摊电梯维护费订单
        int SHARING_OTHER = 11;//其他公摊费用账单
    }

    /**
     * 账单说明
     */
    interface HotelBillCostSituation {
        String ROOM_RENT = "房屋押金";
        String FIRST_ROOM_RENT = "首次房屋租金";
        String PROPERTY_FEE = "物业费";
        String ROOM_DEPOSIT = "房屋租金";
    }

    /**
     * 合同是否开启预警
     */
    interface HotelRoomContractExpirationAlert {
        int OPEN = 1;//开启
        int CLOSE = 0;//关闭
    }

    enum HotelBillTypeName {
        ROOM_RENT(1, "房租"),
        DEPOSIT(2, "押金"),
        RENT_REFUND(3, "退租"),
        PROPERTY(4, "物业"),
        WATER(5, "水费"),
        ELECTRIC(6, "电费"),
        GAS(7, "燃气"),
        SHARING_WATER(8, "公摊水费"),
        SHARING_ELECTRIC(9, "公摊电费"),
        SHARING_ELEVATOR(10, "公摊电梯费维护费用"),
        SHARING_OTHER(11, "其他公摊费用");

        public static String getNameByType(int type) {
            HotelBillTypeName[] values = HotelBillTypeName.values();
            for (HotelBillTypeName value : values) {
                if (value.type == type) {
                    return value.name;
                }
            }
            return "未知项目";
        }


        private int type;
        private String name;

        HotelBillTypeName(int type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    /**
     * 巡检模块
     */
    interface PatrolPolicy {
        int DISTANCE = 130;//巡检打卡限制距离
    }

    interface EnergyTimeType {
        String PEAK = "peak";
        String PEACETIME = "peacetime";
        String LOWPEAKTIME = "lowPeakTime";
    }

    /**
     * 区域场景是否绑定
     */
    interface AreaScenesIsBind {
        int IS_BIND = 1;//绑定
        int NOT_BIND = 0;//未绑定
    }

    /**
     * 设备能耗类型名称
     */
    interface DeviceEnergyName {
        String ELECTRIC = "电";
        String WATER = "水";
        String GAS = "燃气";
    }

    /**
     * 10：小程序-总主页，20：访客-子主页，21：访客-预约物业页
     */
    interface QrCodeType {
        int HOME_PAGE = 10;
        int CHILD_PAGE = 20;
        int PROPERTY_PAGE = 21;
    }

    interface IntervieweeType {
        int PROPRIETOR = 1; //业主
        int PROPERTY_COMPANY = 2; //物业公司
    }

    interface PositionCoordinateParkingType {
        int PARKING = 1; //停车场
        int PARTITION = 2; // 分区
    }

    interface NingxiaPartitionType {
        // 宁夏停车场分区
        String PARTITIONB1 = "B1";
        String PARTITIONB2 = "B2";
    }

    /**
     * 宁夏b1分区
     */
    interface NingxiaParkingAreaB1 {
        int CENTER_1 = 1;
        int CENTER_2 = 2;
        int CENTER_3 = 3;
        int CENTER_4 = 4;
        int CENTER_5 = 5;
        int LEFT_1 = 6;
    }

    /**
     * 宁夏b2分区
     */
    interface NingxiaParkingAreaB2 {
        int CENTER_1 = 7;
        int CENTER_2 = 8;
        int CENTER_3 = 9;
        int CENTER_4 = 10;
        int CENTER_5 = 11;
        int LEFT_1 = 12;
    }

    /**
     * 小程序 公车派车员工应用权限
     */
    interface BusIdentificationType {
        String BUSAPPLY = "busapply";//申请人
        String BUSMANAGE = "busmanage";//管理员
        String BUSASSIGN = "busassign"; //车队长
    }

    /**
     * 小程序 公车派车员工类型
     */
    interface BusStaffType {
        int INITIALIZATION = 0; //0 未定义
        int STAFF = 1; //1 员工
        int MANAGER = 2; //2 管理员
        int CAPTAIN = 3; //3 车队长
    }

    /**
     * 公车派车申请状态
     */
    interface BusApplyStatus {
        int PENDING = 0; //0:待审批
        int TO_BE_ASSIGNED = 1; // 1:待指派
        int PROCESSING = 2; // 2:进行中
        int ENDING = 3; //3:已结束
        int REJECTED = 4; //4:已拒绝
        int CANCELLED = 5; // 5:已取消
    }

    /**
     * 公车派车车辆状态
     */
    interface BusStatus {
        int AVAILABLE = 0; //0:可用
        int SERVICE = 1; // 1:维修
        int USING = 2; // 2:出车
    }

    /**
     * 公车派车司机状态
     */
    interface BusDriverStatus {
        int ON_DUTY = 1; //1:在岗  2:出车
        int USING = 2; // 2:出车
    }

    /**
     * 公车派车角色名
     */
    interface BusRoleName {
        String MANAGE = "用车审批";
        String ASSIGN = "车队管理员";
    }

    interface WebSocketNoticeType {
        int ALARM_NOTICE = 0; //设备告警
        int GUARANTEE = 1;//物业报修
    }

    interface ParkingRecordInOutType {
        String IN = "in";
        String OUT = "out";
    }

    /**
     * 产品功能指令下发映射表 指令标签
     */
    interface ProductCommandCommandSign {
        /**
         * 门磁
         */
        interface DoorSensor {
            String SET_ARMING_STATE = "setArmingState";//布防设置
            String SET_AUDIBLE_ALARM = "setAudibleAlarm";//声光报警设置
            String SET_TAMPER_STATE = "setTamperState";//防拆设置
        }

        /**
         * 车位锁
         */
        interface ParkingLock {
            String UP_AND_DOWN = "upAndDown";//车位锁升降
        }
    }


    /**
     * 设备指令下发 属性指标值
     */
    interface DeviceCommandMetricValue {
        /**
         * 车位锁
         */
        interface ParkingLock {
            String UP_AND_DOWN = "tarPos";//上下降
        }

        /**
         * 门磁
         */
        interface DoorSensor {
            String SET_ARMING_STATE = "armingState";//布防设置
            String SET_AUDIBLE_ALARM = "audibleAlarm";//声光报警设置
            String SET_TAMPER_STATE = "tamper";//防拆设置
        }
    }

    /**
     * 车位锁状态
     */
    interface ParkingLockStatus {
        int UP = 1;//上升
        int DOWN = 2;//下降
    }

    /**
     * 门磁 布防状态
     */
    interface DoorSensorArmingState {
        int OPEN = 1;//布防
        int CLOSE = 0;//撤防
    }

    /**
     * 门磁 防拆设置
     */
    interface DoorSensorTamper {
        int OPEN = 1;//开
        int CLOSE = 0;//关
    }

    /**
     * 门磁 声光报警设置
     */
    interface DoorSensorAudibleAlarm {
        int OPEN = 1;//开
        int CLOSE = 0;//关
    }

    /**
     * 门磁状态
     */
    interface DoorSensorStatus {
        int OPEN = 1;//开
        int CLOSE = 0;//关
    }

    /**
     * 考核管理
     */
    interface Assessment {
        int ASSESSOR_NUM_MAX = 10;
        int STATUS_NOTDEL = 0;//删除状态0：未删除
        int STATUS_DEL = 1;//删除状态1：删除

        int POLICY_STATUS_UNENFORCED = 0;//策略未生效
        int POLICY_STATUS_WORKING = 1;//策略已生效
        int POLICY_STATUS_OVERDUE = 2;//策略已过期
        int POLICY_STATUS_INVALID = 3;//策略已作废

        int POLICY_CYCLE_DAY = 1;//策略考核频率：每天
        int POLICY_CYCLE_WEEK = 2;//策略考核频率：每周
        int POLICY_CYCLE_MONTH = 3;//策略考核频率：每月

        int TARGET_PERSON = 1;//考核对象：个人类型
        int TARGET_DEPT = 2;//考核对象：公司组织类型

        int TASK_STATUS_WORKING = 0;//任务状态：0：进行中
        int TASK_STATUS_OVERDUE = 1;//任务状态：1：已结束

        int SCORE_STATUS_UNEXAMINED = 0;//考核状态 0：待考核
        int SCORE_STATUS_EXAMINED = 1;//考核状态 1：已考核

        String PARAM_MYTASK_STATUS_UNEXAMINED = "0";//我的考核状态 0：待考核
        String PARAM_MYTASK_STATUS_EXAMINED = "1";//我的考核状态 1：已考核
        String PARAM_MYTASK_STATUS_OVERDUE = "2";//我的考核状态 2：已失效的
    }

    /**
     * 住户与合同关系 状态
     */
    interface PersonContractStatus {
        int TAKE_EFFECT = 0;//生效
        int TERMINATION = 1;//终止
        int INVALID = 2;//作废
        int NOT_IN_FORCE = 3;//未生效
    }

    interface HotelSelectType {
        int HOUSE_RESOURCE = 0; //房源
        int DORM = 1; // 宿舍
    }

    /**
     * 菜品预约记录取消权限
     */
    interface DiningAppointmentRecordCancelAuthority {
        int REFUSE = 0;//拒绝
        int ALLOW = 1;//允许
    }

    /**
     * 菜品预约记录状态
     */
    interface DiningAppointmentRecordStatus {
        int WAIT = 0;//等待取餐
        int FINISH = 1;//已完成
        int CANCEL = 2; //已取消
    }

    /**
     * 拉风摄像头离在线 接收通知种类
     */
    interface LafengVideoOfflineSubscribetype {
        int UP_AND_DOWN = 0;//设备上下线通知
    }

    /**
     * 拉风摄像头离在线 操作类型
     */
    interface LafengVideoOfflineOperation {
        int SUBSCRIBE = 0;//订阅
        int DEL = 1;//删除
        int FIND = 2;//查询
    }


    /**
     * 意见反馈(党委)
     */
    interface Feedback {
        int PLATFORM_WEB = 1;//web端新增意见反馈
        int PLATFORM_APP = 2;//小程序端新增意见反馈
        String MANAGER_ROLE_NAME = "管理员";//web/小程序管理员角色名称
    }

    /**
     * 随手拍
     */
    interface RealtyRepair {
        int PROGRESS_TODO = 0;//待处理
        int PROGRESS_EVALUATED = 1;//待评价
        int PROGRESS_FINISHED = 2;//已完成
        int PROGRESS_WORKING = 3;//处理中

        String REALTY_REPAIR_URL = "repair/repair";//随手拍（物业报修）路由
    }

    /**
     * 告警策略的开关状态
     */
    interface PolicyStatus {
        int OPEN = 1;//开启
        int CLOSE = 0;//关闭
    }

    /**
     * 班次是否跨天
     */
    interface ScheduleShiftIsSkip {
        int YES = 1;//跨天
        int NO = 0;//不跨天
    }

    /**
     * 是否是班组长
     */
    interface ScheduleGroupMemberIsMonitor {
        int IS_MONITOR = 1;//是班组长
        int NO_MONITOR = 0;//不是班组长
    }

    //保洁服务申请表状态
    interface CleaningApplyStatus {
        int WAIT_TO_ASSIGN = 1;//待指派
        int WAIT_TO_CLEAN = 2;//待保洁
        int WAIT_TO_EVALUATE = 3;//待评价
        int FINISHED = 4;//已结束
        int CANCELED = 5;//已取消
    }

    //保洁服务角色名
    interface CleaningRoleName {
        String CLEANER = "保洁员";//保洁员
    }

    //保洁服务应用名
    interface CleaningIdentificationType {
        String CLEANER = "cleaner";//保洁员
    }

    /**
     * web端菜单后缀
     */
    interface WebMenuUrlSuffix {
        String NOTICE_SUFFIX = "notice-noticeManage";
        String REALTY_SUFFIX = "repair-repair";
        String REALTY_DETAIL_SUFFIX = "repair/repair/repair-detail";
    }

    /**
     * 编号前缀
     */
    interface NoPrefixP {
        String ASSETS_EQUIPMENT_TYPE_NO = "LB";//自测类别编号
    }

    //资产领用状态
    interface AssetsRequisitionStatus {
        int WAIT_TO_APPROVAL = 1;//待审批
        int WAIT_TO_REQUISITION = 2;//待领用
        int USING = 3;//使用中
        int RETURNED = 4;//已归还
        int REFUSED = 5;//已拒绝
        int CANCELED = 6;//已取消
    }

    //资产领用审核状态
    interface AssetsRequisitionVerifyStatus {
        int WAIT_TO_APPROVAL = 0;//待审批
        int AGREED = 1;//同意
        int REFUSED = 2;//拒绝
        int REQUISITION_TO_CONFIRM = 3;//领用确认
        int RETURN_TO_CONFIRM = 4;//归还确认
        int CANCELED = 5;//取消
    }

    //资产设备相关状态
    interface AssetsEquipmentAllStatus {
        int STATUS_USING = 1;//在用
        int STATUS_FREE = 2;//闲置
        int STATUS_SCRAP = 3;//报废
        int MAINTAIN_STATUS_NORMAL = 0;//正常
        int MAINTAIN_STATUS_REPAIRING = 1;//维修中
        int NOT_DEL = 0;//正常
        int DEL = 1;//删除
    }

    /**
     * 资产维修状态
     */
    interface AssetesRepairStaus {
        int STATUS_TODO = 0;//待处理
        int STATUS_WORKING = 1;//已处理
        int STATUS_EVALUATED = 2;//待评价
        int STATUS_FINISHED = 3;//已完成
        int STATUS_CANCEL = 4;//已取消
    }

    /**
     * 管理员角色名称
     */
    String MANAGER_ROLE_NAME = "管理员";

    /**
     * 资产类型 物联网设备id
     */
    Long EQUIPMENT_TYPE_INTERNET_OF_THINGS = 20210528002L;

    /**
     * 资产类型  通用设备类型
     */
    Long EQUIPMENT_TYPE_UNIVERSAL = 20210528003L;

    /**
     * 资产维修状态
     */
    interface AssetsMaintenanceTaskStatus {
        Integer STATUS_BEGIN = 0;//已开始
        Integer STATUS_OVERTIME = 1;//已超时
        Integer STATUS_OVERTIME_FINISHED = 2;//超时完成
        Integer STATUS_FINISHED = 3;//已完成
    }

    /**
     * 访客记录 进出
     */
    interface VisitorRecordInOrOut {
        int IN = 1;//进
        int OUT = 2;//出
    }

    /**
     * 资产类型前缀
     */
    interface AssetsEquipmentTypePrefix {
        String ASSETS_EQUIPMENT_FIXED = "GD"; //固定资产
        String ASSETS_EQUIPMENT_UNIVERSAL = "TY"; //通用设备
        String ASSETS_INTERNET_OF_THINGS = "WL";  //物联网设备
    }

    /**
     * 资产类型ID
     */
    interface AssetsEquipmentTypeId {
        String ASSETS_TYPE_FIXED = "20210528001";  //固定资产
        String ASSETS_TYPE_UNIVARSAL = "20210528003";  //通用设备
        String ASSETS_TYPE_INTERNET_OF_THINGS = "20210528002"; //物联网设备
    }

    /**
     * 资产设备编辑当前使用人，生成领用数据时，默认领用事由
     */
    String ASSETS_REQUISITION_REQUISITION_REASON = "系统默认";

    /**
     * 设备类型指标
     */
    interface DeviceTypeMetric {
        interface WaterLevelMonitorType {
            String ACQUISITION_VALUE = "acquisition_value";//液位值
        }
    }

    /**
     * 水位状态
     */
    interface WaterLevelMonitorStatus {
        int NORMAL = 1;//正常
        int EXCEED = 2;//超过
    }

    /**
     * 是否有水位告警阈值
     */
    interface WaterLevelIsAlarmThreshold {
        int NO_ALARM_THRESHOLD = 0;//没有告警阈值
        int HAS_ALARM_THRESHOLD = 1;//有告警阈值
    }

    /**
     * 心跳统计表 前缀
     */
    String STATISTICS_TABLE_PREFIX = "iotsaas_statistics_";

    /**
     * 心跳基础表 前缀
     */
    String DEVICE_DATA_TABLE_PREFIX = "iotsaas_data_";


    /**
     * 设备统计配置 统计周期
     */
    interface StatisticsConfigPeriod {
        int HOUR = 1;//小时
        int DAY = 2;//天
        int MONTH = 3;//月
    }

    /**
     * 设备统计配置 表类型
     */
    interface StatisticsConfigDataType {
        String HOUR = "hour";//小时
        String DAY = "day";//天
        String MONTH = "month";//月
        String YEAR = "year";//年
    }

    /**
     * 设备统计配置 当前值
     */
    interface StatisticsConfigOldOrNew {
        int EARLIEST = 0;//最早
        int LATEST = 1;//最晚
        int AVG = 2;//平均
    }

    /**
     * 南平党校租户id
     */
    Long NPDX_TENANT_ID = 32000L;

    /**
     * 南平党校日志相关
     */
    interface NPDXReveice {
        int ACTION_SAVE = 1;//新增
        int ACTION_UPDATE = 2;//编辑
        int ACTION_DELETE = 3;//删除

        int TYPE_USER = 1;//用户信息数据
        int TYPE_DEPT = 2;//部门数据
        int TYPE_USER_DEPT = 3;//部门用户关系数据
    }

    /**
     * 教师角色
     */
    String TEACHER_ROLE = "教师";
    /**
     * 教师职位
     */
    String TEACHER_POSITIN = "教师";

    public interface PersonOperType {
        Integer ADD = 1; // 新增
        Integer UPDATE = 2; // 修改
        Integer DELETE = 3; // 删除
    }

    public interface PersonOperPushResult {
        Integer NOT_START = -1;
        Integer SUCCESS = 0;
        Integer FAIL = 1;
    }

    /**
     * 预案动作  是否开启
     */
    interface FirefightingActionOpen {
        int OPEN = 1;//开启
        int CLOSE = 0;//关闭
    }

    /**
     * 预案动作类型
     */
    interface FirefightingActionType {
        int VOICE = 1;//语音
        int SMS = 2;//短信
        int RADIO = 3;//广播
        int GUARD = 4;//门禁
    }

    /**
     * 消防预案/消防预案动作  删除状态
     */
    interface FirefightingDel {
        int NOT_DEL = 0;//未删除
        int DEL = 1;//删除
    }

    /**
     * 预案通知模板类型
     */
    interface FirefightingActionTemplateType {
        int SMS = 1;//短信
        int VOICE = 2;//语音
    }

    /**
     * 预案设备类型     1、烟感 2、消防压力  3、门磁   4、燃气报警
     */
    interface FirefightingDeviceType {
        int SMOKE = 1;//烟感
        int FIREHYDRANT = 2;//消防压力
        int DOORSENSOR = 3;//门磁
        int GAS_ALARM = 4;//燃气报警器
    }

    /**
     * 距离常量
     */
    interface Distance {
        double FIFTY_METERS = 50.0;//50米
    }

    /**
     * 预案日志下发结果
     */
    interface FirefightingLogResult {
        int SUCCESS = 1;//成功
        int FAIL = 0;//失败
    }

    /**
     * 语音模板
     */
    interface VoiceTemplateId {
        String FIREFIGHTING_VOICE_TEMPLATE_FIRST = "3693"; //消防预案
        String FIREFIGHTING_VOICE_TEMPLATE_SECOND = "3692";//消防预案
    }

    /**
     * 媒体任务名称
     */
    interface SpeakMediaTaskName {
        String FIRE_TASK_NAME = "火灾通知";//火灾通知
    }

    /**
     * 媒体任务级别
     */
    interface SpeakMediaTaskLevel {
        String FIRST = "1";//一级
        String SECOND = "2";//二级
        String THIRD = "3";//第三
        String FOURTH = "4";//第四
    }

    /**
     * 媒体任务音量
     */
    interface SpeakMediaTaskVolume {
        String ONE = "1";//1
        String TWO = "2";//2
        String THREE = "3";//3
        String FOUR = "4";//4
        String FIVE = "5";//5
        String TEN = "10";//10
    }

    /**
     * 预案状态
     */
    interface FirefightingStatus {
        int OPEN = 1;//开启
        int DISABLE = 0;//禁用
    }

    /**
     * 人力管理
     */
    interface HrmJobWage {
        double WAGEMIN = 1000.0;//最低
        double WAGEMAX = 5000.0;//员工
    }

    interface HrmStaffStatus {
        int LEAVEJOB = 2;//离职
        int ONJOB = 1;//在职
        int EMPLOYED = 0; //待入职
    }

    interface HrmJobNoKey {
        String ONE = "1";
        String TWO = "2";
    }

    interface HrmUnitName {
        String ONE = "机关服务中心";
        String TWO = "国勤物业";
    }

    interface HrmContractStatus {
        int INI = 0;//初始化 全部
        int TAKE_EFFECT = 1;//生效
        int INVALIDATION = 2;//失效
        int TO_BE_EFFECTIVE = 3;//待生效
    }

    interface HrmContractStatusString {
        String INI = "全部";//初始化 全部
        String TAKE_EFFECT = "生效";//生效
        String INVALIDATION = "失效";//失效
        String TO_BE_EFFECTIVE = "待生效";//待生效
    }

    interface HrmJobType {
        int TECHNOLOGY = 1;//技术岗
        int MANAGEMENT = 2;//管理岗
        int SERVICE = 3; //服务岗
    }

    interface HrmJobTypeString {
        String TECHNOLOGY = "技术岗";//技术岗
        String MANAGEMENT = "管理岗";//管理岗
        String SERVICE = "服务岗"; //服务岗
    }

    interface HrmEvidenceStatus {
        int PENDING_APPROVAL = 0;
        int AGREE = 1;
        int REJECTED = 2;
    }

    /**
     * 年考勤统计提醒
     */
    interface HrmAttendanceNoticeLevel {
        int INT = -1;//初始值
        int STATUS_INI = 0;//未提醒
        int TWO_THOUSAND = 2000;//2000工时
        int TWO_THOUSAND_LEVEL = 1;//2000工时等级
        int ONE_THOUSAND_EIGHT = 1800;//1800工时
        int ONE_THOUSAND_EIGHT_LEVEL = 0;//1800工时
    }


    /**
     * 是否是 消防设备类型
     */
    interface IsFirefightingDeviceType {
        int YES = 1;//是
        int NO = 0;//不是
    }

    interface EvidenceStatus {
        int PENDING_APPROVAL = 0; // 待审批
        int AGREE = 1; // 同意
        int REFUSE = 2; // 拒绝
    }

    interface ZydxDefaultTenantId {
        Long TENANTID = 31098L;
    }

    interface ZydxDefaultRoleId {
        Long RoleId = 1289400037773164546L;
    }


    /**
     * 考勤工时区间
     */
    interface AttendanceWorkingHoursInterval {
        int LESS_THAN_1800 = 1; //工时小于1800
        int BETWEEN_1800_AND_1999 = 2;//1800-1999
        int MORE_THAN_2000 = 3;//大于2000
    }

    /**
     * 考勤工时区间
     */
    interface AttendanceWorkingHoursValue {
        int ZERO = 0;
        int ONE_THOUSAND_SEVEN_HUNDRED_AND_NINETY_NINE = 1799;
        int ONE_THOUSAND_AND_EIGHT_HUNDRED = 1800;
        int ONE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE = 1999;
        int TWO_THOUSAND = 2000;
    }

    /**
     * 是否需要整年显示
     */
    interface AttendanceIsAllYear {
        int YES = 1;//需要
        int NOT = 0;//不需要
    }

    /**
     * 单位类型
     */
    interface HRMUnitType {
        int ORGAN = 1;//机关服务中心
        int GUOQIN_PROPERTY = 2;//国勤物业
    }


    /**
     * 工资提前量
     */
    Double Hrm_Base_Salary = 2320.00;

    interface HrmWageLead {
        Double HRM_SENIORITY_SALARY = 2320.00; //基础工资
        int MAX_SENIORITY_SALARY = 300;        //工龄封顶工资
        int EVERY_YEAR_SENIORITY_SALARY = 20;  //工龄每年递增
        int WAGE_PERCENT = 25;             //绩效占岗位工资比例
    }

    /**
     * 意见反馈（包含派单操作）
     */
    interface RealtyOpinion {
        int IS_DISPATCH_0 = 0;//不派单
        int IS_DISPATCH_1 = 1;//派单

        int PROGRESS_WAIT_TO_DEAL = 0;//待处理
        int PROGRESS_WAIT_TO_EVALUATE = 1;//待评价
        int PROGRESS_SUCCESS = 2;//已完成
        int PROGRESS_DEALING = 3;//处理中

        int ROLE_OWNER = 1;//业主
        int ROLE_PROPERTY = 2;//物业
    }

    /**
     * 门禁开关状态
     */
    interface GuardOpenState {
        int OPEN = 1;//开
        int CLOSE = 0;//关
    }

    /**
     * 年龄段
     */
    interface  Generation{
        int SMALL_THIRTY_FIVE = 1;//小于以及35
        int BETWEEN_THIRTY_SIX_AND_FORTY=2;//36-40
        int BETWEEN_FORTY_ONE_AND_FORTY_FIVE=3;//41-45
        int BETWEEN_FORTY_SIX_AND_FIFTY=4;//46-50
        int BETWEEN_FIFTY_ONE_AND_FIFTY_FIVE=5;//51-55
        int BIG_FIFTY_FIVE=6;//55以上
    }

    /**
     * 年龄中位值类型
     */
    interface  MedianAgeType{
        int MAX=1;//最大
        int MIN=2;//最小
        int AVG=3;//平均
        int MEDIAN = 4;//中位值
    }

    /**
     * 访客申请审核状态
     */
    interface VisitorVerifyStatus {
        int PENDING_APPROVAL = 0;//待审批
        int APPROVAL = 1;//已同意
        int REFUSE = -1;//已拒绝
        int INVALID = -2;//已失效（无法审核）
    }

    /**
     * 访客申请审批类型
     */
    interface VisitorVerifyType {
        int APPROVAL = 1;//已同意
        int REFUSE = -1;//已拒绝
    }

    /**
     * 访客来访状态
     */
    interface VisitorVisitingStatus {
        int VISITED = 1;//已到访
        int NOT_VISITED = -1;//未到访
        int INVALID = -2;//已失效
    }

    /**
     * 社区是否拥有访客机设备
     */
    interface CommunityHasVisitorMachine {
        int YES = 1;//拥有
        int NO = 0;//未拥有
    }

    /**
     * 响应数据编码
     */
    interface ResultCode {
        int SUCCESS = 0;//成功
    }

    /**
     * 访客预约/邀请方式
     */
    interface VisitorInviteType {
        int APPOINTMENT = 1;//主动预约
        int INVITATION = 2;//被动邀请
    }

    /**
     * 访客是否需要签出
     */
    interface VisitorIsSign {
        int YES = 1;//需要签出
        int NO = 0;//不需要签出
    }

    /**
     * 访客签出状态
     */
    interface VisitorSignStatus {
        int PENDING_SIGN = 0;//待签出
        int SIGN = 1;//签出
        int REFUSE_SIGN = -1;//拒绝签出
    }

    /**
     * 访客登记顺序
     */
    interface VisitorSort {
        int FIRST_VISITOR = 1;//主访人（第一位访客）
    }

    /**
     * 访客证件类型
     */
    interface VisitorCertificateType {
        int IDCARD = 111;//身份证
        int PASSPORT = 414;//护照
        int RESIDENCE_BOOKLET = 113;//户口簿
        int DRIVER_LICENSE = 335;//驾驶证
        int EMPLOYEE_CARD = 131;//工作证
        int STUDENT_IDCARD = 133;//学生证
        int OTHER = 990;//其它
    }

    /**
     * 访客数据统计时间范围
     */
    interface VisitorTimeRange {
        int TODAY = 1;//今日
        int WEEK = 2;//本周
        int MONTH = 3;//本月
    }

    /**
     * 访客权限配置
     */
    interface VisitorAuthority {
        int GRANT = 1;//授予权限
        int REMOVE = 2;//移除权限
        int DISTRIBUTION = 3;//分配权限
    }

    /**
     * 访客预约有效通行时间
     */
    interface VisitorPassTime {
        int ONE_DAY = 1;//一天
        int THREE_DAYS = 3;//三天
        int SEVEN_DAYS = 7;//七天
    }

    /**
     * 访客进出类型
     */
    interface VisitorInOrOutType {
        int FACE = 1;//刷脸
        int IDCARD = 2;//刷身份证
        int DEVICE_SCAN = 3;//设备扫码
        int PHONE_SCAN = 4;//保安手机扫码
    }

    /**
     * 访客预约审核人类型
     */
    interface VisitorVerifyReviewer {
        int PROPRIETOR = 1; //业主
        int PROPERTY_COMPANY = 2; //物业公司
        int MANAGER = 3; //管理员
    }

    /**
     * 时间单位类型
     */
    interface TimeUint {
        int HOUR = 1;  //小时
        int MINUTE = 2;  //分钟
    }

    /**
     * 海康：访客机静态量
     */
    interface HikVisitor {
        int PAGENO = 1; // 页码
        int PAGESIZE = 10; // 条数
        int OPERATOR_EQUAL = 0; // =
        String DEFAULT_UNUSE = "2"; // 不使用
        String DEFAULT_USE = "1"; // 使用
    }

    /**
     * 超级管理员
     */
    interface SUPER_ADMIN {
        // 否
        int NO = 0;
        // 是
        int YES = 1;
    }

    /**
     * 顶级租户
     */
    interface SUPER_TENANT {
        // 否
        int NO = 0;
        // 是
        int YES = 1;
    }

    /**
     * 默认密码
     */
    String DEFAULT_PASSWORD = "admin345#$%";

}
