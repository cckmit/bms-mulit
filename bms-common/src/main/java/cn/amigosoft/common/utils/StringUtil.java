package cn.amigosoft.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * <p>
 * Copyrigth:Copyright (c) 2015年7月13日 下午1:06:15
 * <p>
 * @version 1.0.0
 */

public class StringUtil {
	public static final String ALLCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 方法描述：字符串是否为空
	 * 
	 * @param str
	 * @return boolean
	 * @time 2015年7月13日 下午1:10:52
	 * 
	 */
	public static boolean isBlank(String str) {
		if (str == null || "".equals(str.trim())||"null".equals(str)||"NULL".equals(str))
			return true;
		return false;
	}
	/**
	 * 判断对象是否为空对象
	 * @param obj
	 * @return true 是空对象 false 不是空对象
	 */
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true ;
		}
		return false ;
	}

	/**
	 *  判断是否是空字符串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str)){
			return true ;
		}
		return false ;
	}

	/**
	 * 数据数据是否是空对象
	 * @param arr
	 * @return
	 */
	public static boolean isEmpty(Object[] arr){
		if(arr == null){
			return true ;
		}
		if(arr.length == 0){
			return true ;
		}
		return false ;
	}

	/**
	 * 方法描述：字段是否为空
	 * 
	 * @param str
	 * @return boolean
	 * @time 2015年7月13日 下午1:07:11
	 * 
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 方法描述：输入流转换为字符串
	 * 
	 * @param is
	 * @return String
	 * @time 2015年7月13日 下午1:10:01
	 * 
	 */
	public static String streamToString(InputStream is) {
		String str = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		try {
			while ((i = is.read()) != -1)
				baos.write(i);
			byte data[] = baos.toByteArray();
			str = new String(data, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 方法描述:获取随机4位数 date:2014-4-15 add by: maoliliang@xwtec.cn
	 */
	public static String getRandom4() {
		Random random = new Random();
		int code = random.nextInt(8999);
		code = code + 1000;
		return String.valueOf(code);
	}
	/**
	 * 方法描述:获取随机6位数 date:2014-4-15 add by: maoliliang@xwtec.cn
	 */
	public static String getRandom6() {
		Random random = new Random();
		int code = random.nextInt(889999);
		code = code + 100000;
		return String.valueOf(code);
	}
	/**
	 * 方法描述:获取随机8位数 date:2014-4-15 add by: maoliliang@xwtec.cn
	 */
	public static String getRandom8() {
		Random random = new Random();
		int code = random.nextInt(88999999);
		code = code + 10000000;
		return String.valueOf(code);
	}
	/**
	 * 
	 * 方法描述：url编码
	 * 
	 * @param source
	 * @return String
	 * @time 2015年7月20日 上午10:45:18
	 *
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getIntStringForObj(Object obj) {
		return (obj == null) ? "0" : obj.toString();
	}


	public static String getSubStr(String str, int num) {
		String result = "";
		int i = 0;
		while (i < num) {
			int lastFirst = str.lastIndexOf('/');
			result = str.substring(lastFirst) + result;
			str = str.substring(0, lastFirst);
			i++;
		}
		return result.substring(1);
	}
	/**
	 * 拼凑requestParamters的所有参数，返回字符串
	 * 
	 * @param request
	 * @return String
	 */
	public static String getParamsString(HttpServletRequest request) throws Exception {
		String paramsStr = "";
		@SuppressWarnings("unchecked")
		Map<String, String[]> paramMap = request.getParameterMap();
		Set<Map.Entry<String, String[]>> set = paramMap.entrySet();
		for (Map.Entry<String, String[]> entry : set) {
			String pname = entry.getKey();
			String[] values = entry.getValue();
			for (String value : values) {
				if(paramsStr.equals("")){
					paramsStr = paramsStr + pname + "=" + value;
				}else{
					paramsStr = paramsStr + "&" + pname + "=" + value;
				}
				
			}
		}
		return paramsStr;
	}
	/**
	 * @Title handleStr
	 * @Description 字符串拼接方法，替换${}
	 * @param pattern
	 * @param params
	 * @return
	 */
	public static String handleStr(String pattern, Object... params) {
		if (null == pattern || pattern.trim().length() == 0) {
			return pattern;
		}

		if (null == params || params.length == 0) {
			return pattern;
		}
		for (int i = 0; i < params.length; i++) {
			if (null == params[i]) {
				params[i] = "null";
			}
			pattern = StringUtils.replaceOnce(pattern, "${}",
					params[i].toString());
		}

		return pattern;
	}
	public static boolean strGtLen(String str,Integer len){
		boolean flag = false;
		if(!isEmpty(str)){
			if(str.length()>len){
				flag =true;
			}
		}
		return flag;
	}

	/**
	 * 短信标识码
	 * @return
	 */
	public static String getIdentificationCode(){
		int max=99;
		int min=1;
		Random random = new Random();
		int num = random.nextInt(max)%(max-min+1) + min;
		String smsNum=generateUpperString(1)+toFixdLengthString(num, 2);
		return smsNum;
	}
	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 *
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(int num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}
	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}
	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大写字母)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}
	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含小写字母)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}
	/**
	 * 生成一个定长的纯0字符串
	 *
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}
	/**
	 * 获取uuid
	 * @return 数字串
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
    /**
	 * 方法描述 判断一个对象是否是一个数组
     * @param obj
     * @return
	 *
	 * @author xuefh
     * @date 2020年2月16日 下午5:03:00
	 */
	public static boolean isArray(Object obj) {
		if(obj == null) {
			return false;
		}
		return obj.getClass().isArray();
	}
}
