package cn.amigosoft.modules.assets.utils;

import cn.amigosoft.common.utils.StringUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNoUtils {
    /**
     * 生成编号：前缀 + 时间 + 序号（最多4位数）
     */
    private final static String FORMAT_CODE = "0000";

    public static String getNumber(String prefix,String format, Date date, Integer count){
        if(count == null || StringUtil.isBlank(prefix) || StringUtil.isBlank(format) || date == null){
            return null;
        }
        if(count >= 10000){
            count = 1;
        }
        DecimalFormat dft = new DecimalFormat(FORMAT_CODE);
        String code = dft.format(count); // 格式化为四位流水号 code: 0001
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date); // 格式化日期 date: 20200724
        String codeEnd = prefix + dateStr + code;
        return codeEnd;
    }

    /**
     * 生成编号：前缀 + 时间 + maxNum (maxNum为当前最大的编号)
     */
    public static String getNumberByMaxNum(String prefix,String format, Date date, String maxNum){
        if(StringUtil.isBlank(maxNum) || StringUtil.isBlank(prefix) || StringUtil.isBlank(format) || date == null){
            return null;
        }
        String substring = maxNum.substring(maxNum.length() - 4, maxNum.length());
        int count = Integer.parseInt(substring) + 1;
        if(count >= 10000){
            count = 1;
        }
        DecimalFormat dft = new DecimalFormat(FORMAT_CODE);
        String code = dft.format(count); // 格式化为四位流水号 code: 0001
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date); // 格式化日期 date: 20200724
        String codeEnd = prefix + dateStr + code;
        return codeEnd;
    }

    public static void main(String[] args) {
        String wb = getNumber("WB","yyyyMMdd", new Date(), 0);
        System.out.println(wb);
        String c = "BW200011200000";
        String numberByMaxNum = getNumberByMaxNum("WB", "yyyyMMdd", new Date(), c);
        System.out.println(numberByMaxNum);
    }
}
