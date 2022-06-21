package cn.amigosoft.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ChenXiong
 * @Description: excel错误信息导出工具类
 * @CreateTime: 2020年8月27日18:07:20
 */
@Slf4j
public class ImportUtils {

    /***
     *  Excel 解析失败
     * @param response 请求
     */
    public static void errorParseExcel(HttpServletResponse response) {
        Result<String> result = new Result<>();
        String errorMsg;
        try {
            errorMsg = URLEncoder.encode("Excel解析失败！", "UTF-8");
        } catch (Exception e) {
            log.error("汉字转码是发生错误：", e);
            errorMsg = "Excel parsing failed";
        }
        result.error(errorMsg);
        response.addHeader("Other-Json", JSON.toJSONString(result));
        response.setHeader("Access-Control-Expose-Headers", response.getHeader("Access-Control-Expose-Headers") + ",Other-Json");
    }

    /***
     *  Excel中未包含数据
     * @param response 请求
     */
    public static void noDataImport(HttpServletResponse response) {
        Result<String> result = new Result<>();
        String errorMsg;
        try {
            errorMsg = URLEncoder.encode("Excel中未包含数据！", "UTF-8");
        } catch (Exception e) {
            log.error("汉字转码是发生错误：", e);
            errorMsg = "No data in Excel";
        }
        result.error(errorMsg);
        response.addHeader("Other-Json", JSON.toJSONString(result));
        response.setHeader("Access-Control-Expose-Headers", response.getHeader("Access-Control-Expose-Headers") + ",Other-Json");
    }

    /***
     * 导入存在错误信息
     * @param response 请求
     */
    public static void errorImport(int allCount, HttpServletResponse response, Collection<?> errorList,
                                   Class<?> errorListClass, String fileName) {
        int errorCount = errorList.size();
        int successCount = allCount - errorList.size();

        Map<String, Object> map = new HashMap<>(8);
        map.put("code", 0);
        map.put("sTotal", successCount);
        map.put("eTotal", errorCount);
        // -1 时触发下载请求
        map.put("isDownload", -1);
        response.addHeader("Other-Json", JSON.toJSONString(map));
        response.setHeader("Access-Control-Expose-Headers", response.getHeader("Access-Control-Expose-Headers") + ",Other-Json");

        try {
            ExcelUtils.exportExcelToTarget(response, fileName, errorList, errorListClass);
        } catch (Exception e) {
            log.error("Excel错误数据导出时发生错误", e);
        }
    }

    /***
     * 导入成功
     * @param response 请求
     */
    public static void successImport(int allCount, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", 0);
        map.put("sTotal", allCount);
        map.put("eTotal", 0);

        // 1 为不下载
        map.put("isDownload", 1);
        response.addHeader("Other-Json", JSON.toJSONString(map));
        response.setHeader("Access-Control-Expose-Headers", response.getHeader("Access-Control-Expose-Headers") + ",Other-Json");
    }

    public static List<Map<String, String>> addressResolution(String address) {
        //1级 省 自治区  2级 市 自治州 地区 3级：区县市旗(镇？)
        String province = null, city = null, provinceAndCity = null, town = null, detailAddress = null;
        Map<String, String> row = new LinkedHashMap<>();
        List<Map<String, String>> table = new ArrayList<>();
        Map<String, String> resultMap = new HashMap<>(4);

        if (address.startsWith("香港特别行政区")) {
            resultMap.put("province", "香港");
            return Collections.singletonList(resultMap);
        } else if (address.contains("澳门特别行政区")) {
            resultMap.put("province", "澳门");
            return Collections.singletonList(resultMap);
        } else if (address.contains("台湾")) {
            resultMap.put("province", "台湾");
            return Collections.singletonList(resultMap);
        } else {
            //普通地址
            String regex = "((?<provinceAndCity>[^市]+市|.*?自治州|.*?区|.*县)(?<town>[^区]+区|.*?市|.*?县|.*?路|.*?街|.*?道|.*?镇|.*?旗)(?<detailAddress>.*))";
            Matcher m = Pattern.compile(regex).matcher(address);
            while (m.find()) {
                provinceAndCity = m.group("provinceAndCity");
                String regex2 = "((?<province>[^省]+省|.+自治区|上海市|北京市|天津市|重庆市|上海|北京|天津|重庆)(?<city>.*))";
                Matcher m2 = Pattern.compile(regex2).matcher(provinceAndCity);
                while (m2.find()) {
                    province = m2.group("province");
                    row.put("province", province == null ? "" : province.trim());
                    city = m2.group("city");
                    row.put("city", city == null ? "" : city.trim());
                }
                town = m.group("town");
                row.put("town", town == null ? "" : town.trim());
                table.add(row);

                detailAddress = m.group("detailAddress");
                row.put("detailAddress", detailAddress == null ? "" : detailAddress.trim());
                table.add(row);
            }
        }
        if (table.size() > 0) {
            if (StringUtils.isNotBlank(table.get(0).get("province"))) {
                province = table.get(0).get("province");
                // 截取有问题，西藏自治区，内蒙古自治区，广西壮族自治区，宁夏回族自治区，新疆维吾尔自治区
                //对自治区进行处理
                // if (province.contains("自治区")) {
                //     if (province.contains("内蒙古")) {
                //         province = province.substring(0,4);
                //     }  else {
                //         province = province.substring(0,3);
                //     }
                // }
            }
            // 对省份进行处理
            if (StringUtils.isNotBlank(province)) {
                if (StringUtils.isNotBlank(table.get(0).get("city"))) {
                    city = table.get(0).get("city");
                    if ("上海市".equals(city) || "重庆市".equals(city) || "北京市".equals(city) || "天津市".equals(city)) {
                        province = table.get(0).get("city");
                    }
                } else if ("上海市".equals(province) || "重庆市".equals(province) || "北京市".equals(province) || "天津市".equals(province)) {
                    city = province;
                }
                if (StringUtils.isNotBlank(table.get(0).get("town"))) {
                    town = table.get(0).get("town");
                }
                if (StringUtils.isNotBlank(table.get(0).get("detailAddress"))) {
                    detailAddress = table.get(0).get("detailAddress");
                }
                // 去除省份中的最后一个字
                // province = province.substring(0,province.length() - 1);
            }
            resultMap.put("province", province);
            resultMap.put("city", city);
            resultMap.put("county", town);
            resultMap.put("town", detailAddress);
            resultMap.put("village", "");
            return Collections.singletonList(resultMap);
        } else {
            resultMap.put("province", "");
            resultMap.put("city", "");
            resultMap.put("county", "");
            resultMap.put("town", address);
            resultMap.put("village", "");
            return Collections.singletonList(resultMap);
        }
    }

    public static void main(String[] args) {
        String address;

        // address="广东省深圳市南山区东滨路205号";
        // address="上海市虹口区飞虹路518号";
        // address="河北省廊坊市三河市燕顺路1140号";
        // address="香港特别行政区油尖旺区广华街58号";
        // address="黑龙江省大兴安岭地区呼玛县合兴街";
        // address="江苏省南京市江宁区202县道";
        // address="海南省陵水黎族自治县陵水黎族自治县提蒙大道215号";
        //
        // address="山东省烟台市龙口市062县道";
        // address="新疆维吾尔自治区乌鲁木齐市沙依巴克区阿里山街";
        // address="内蒙古自治区呼伦贝尔市鄂温克族自治旗";
        // address="日本滋贺县甲賀市県道507号線";
        //
        address = "广东省东莞市仍然市振华路290号";
        // address="海南省省直辖县陵水黎族自治县提蒙大道215号";


        List<Map<String, String>> maps = addressResolution(address);
        maps.forEach(System.out::println);
    }
}
