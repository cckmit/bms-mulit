package cn.amigosoft.common.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 *资产管理-编号生成工具
 * @author 邱贤斌
 * @date 2021/06/01
 */
public class AssetsNoUtils {
    private static AtomicLong atomicLong = new AtomicLong(1L);
    private static AtomicLong timestamp = new AtomicLong(System.currentTimeMillis());
    private static final Long DEFAULT_MAX = 9999L;
    private static NumberFormat numberFormat;

    static {
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMinimumIntegerDigits(3);
        int seuquenceLen = String.valueOf(DEFAULT_MAX).length();
        numberFormat.setMinimumIntegerDigits(seuquenceLen);
        numberFormat.setGroupingUsed(false);
    }

    public static String getUniqueId() {
        String formatSequence = numberFormat.format(getSequence(DEFAULT_MAX));
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String todayDate = dateFormat.format(today);
        return  todayDate + randomNum(4) + formatSequence;
    }


    /**
     * get a sequence（number） in a millisecond,if sequenceNumber equels maxPerMillisecond,
     * it will return at next millisecond with a sequenceNumber which will increase from zero again
     *
     * @param maxPerMillisecond
     * @return
     */
    public static Long getSequence(Long maxPerMillisecond) {
        long nextSequence = atomicLong.get();
        while (nextSequence >= maxPerMillisecond || !atomicLong.compareAndSet(nextSequence, ++nextSequence)) {
            if (nextSequence >= maxPerMillisecond && timestamp.longValue() >= System.currentTimeMillis()) {
                nextSequence = atomicLong.get();
            } else if (nextSequence >= maxPerMillisecond) {
                if (atomicLong.compareAndSet(maxPerMillisecond, 0)) {
                    timestamp.set(System.currentTimeMillis());
                    nextSequence = 0L;
                }
            }
        }
        return nextSequence;
    }

    /**
     * 生成指定位数随机码
     * @Author qiuxianbin
     * @Date 2021/06/01
     **/
    private static int randomNum(int len){
        return  (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
    }

    /**
     * 资产领用编号生成  "LY" + 8位时间格式（yyyyMMMdd）+ 4位随机数 + 4位数字序列
     * @Author qiuxianbin
     * @Date 2021/06/01
     **/
    public static String getRequisitionNo() {
        return "LY" + getUniqueId() ;
    }

    /**
     * 资产类别编号生成  "LB" + 8位时间格式（yyyyMMMdd）+ 4位随机数 + 4位数字序列
     * @Author qiuxianbin
     * @Date 2021/06/01
     **/
    public static String getEquipmentTypeNo() {
        return "LB" + getUniqueId() ;
    }

    /**
     * 资产类别编号生成  "LB" + 8位时间格式（yyyyMMMdd）+ 4位随机数 + 4位数字序列
     * @Author qiuxianbin
     * @Date 2021/06/01
     **/
    public static String getEquipmentPlanNo() {
        return "BY" + getUniqueId() ;
    }


    public static void main(String[] args) {
        for(int i=0;i<4;i++){
            System.out.println(getEquipmentTypeNo());
        }
    }

}


    