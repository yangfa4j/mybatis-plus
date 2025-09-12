package com.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 地址掩码工具类
 * 用于对敏感地址信息进行脱敏处理
 *
 * @author system
 * @date 2025/01/20
 */
@Slf4j
public class MaskUtils {

    // 省市区匹配正则
    private static final Pattern PROVINCE_CITY_PATTERN = Pattern.compile("^(.{2,4}[省市])?(.{2,6}[市区县])?(.{2,6}[区县镇])?");

    /**
     * 标准地址掩码 - 保留省市区，其他部分进行掩码处理
     *
     * @param address 完整地址
     * @return 掩码后的地址
     */
    public static String maskAddress(String address) {
        if (StringUtils.isBlank(address)) {
            return address;
        }

        // 地址太短直接返回
        if (address.length() <= 3) {
            return address;
        }

        // 提取省市区部分
        String provinceCityDistrict = extractProvinceCityDistrict(address);

        // 如果没有识别到省市区，使用简单掩码
        if (StringUtils.isBlank(provinceCityDistrict)) {
            return maskSimple(address);
        }

        // 计算详细地址部分
        String detailAddress = address.substring(provinceCityDistrict.length());

        // 如果详细地址为空或太短，直接返回省市区
        if (StringUtils.isBlank(detailAddress) || detailAddress.length() <= 2) {
            return provinceCityDistrict + detailAddress;
        }

        // 对详细地址进行强掩码处理
        String maskedDetail = maskDetailPart(detailAddress);

        return provinceCityDistrict + maskedDetail;
    }

    /**
     * 手机号掩码
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return mobile;
        }
        if (mobile.length() != 11) {
            log.debug("手机号长度不为11位, mobile:{}", mobile);
            return mobile;
        }
        // 保留前3位和后4位，中间4位替换为****
        String prefix = mobile.substring(0, 3);
        String suffix = mobile.substring(7);
        return prefix + "****" + suffix;
    }

    /**
     * 提取省市区部分
     *
     * @param address 完整地址
     * @return 省市区部分
     */
    private static String extractProvinceCityDistrict(String address) {
        // 简单的省市区识别逻辑
        StringBuilder result = new StringBuilder();
        int index = 0;

        // 识别省份
        for (int i = 2; i <= Math.min(4, address.length()); i++) {
            String part = address.substring(0, i);
            if (part.endsWith("省") || part.endsWith("市")) {
                result.append(part);
                index = i;
                break;
            }
        }

        // 识别城市
        if (index < address.length()) {
            for (int i = index + 2; i <= Math.min(index + 6, address.length()); i++) {
                String part = address.substring(index, i);
                if (part.endsWith("市") || part.endsWith("区") || part.endsWith("县")) {
                    result.append(part);
                    index = i;
                    break;
                }
            }
        }

        // 识别区县
        if (index < address.length()) {
            for (int i = index + 2; i <= Math.min(index + 6, address.length()); i++) {
                String part = address.substring(index, i);
                if (part.endsWith("区") || part.endsWith("县") || part.endsWith("镇")) {
                    result.append(part);
                    break;
                }
            }
        }

        return result.toString();
    }

    /**
     * 对详细地址部分进行掩码处理
     *
     * @param detailAddress 详细地址
     * @return 掩码后的详细地址
     */
    private static String maskDetailPart(String detailAddress) {
        if (StringUtils.isBlank(detailAddress)) {
            return detailAddress;
        }

        int length = detailAddress.length();

        // 短地址保留首尾各1位
        if (length <= 4) {
            return detailAddress.charAt(0) + StringUtils.repeat("*", length - 2) + detailAddress.charAt(length - 1);
        }

        // 长地址保留前2位，其余大部分掩码
        int keepPrefix = 2;
        int keepSuffix = Math.max(1, length / 6); // 保留末尾少量字符
        int maskLength = length - keepPrefix - keepSuffix;

        String prefix = detailAddress.substring(0, keepPrefix);
        String suffix = detailAddress.substring(length - keepSuffix);
        String mask = StringUtils.repeat("*", Math.min(maskLength, 8)); // 最多8个*

        return prefix + mask + suffix;
    }

    /**
     * 简单掩码处理（当无法识别省市区时使用）
     *
     * @param address 地址
     * @return 掩码后的地址
     */
    private static String maskSimple(String address) {
        int length = address.length();

        if (length <= 6) {
            // 短地址保留前后各1位
            return address.charAt(0) + StringUtils.repeat("*", length - 2) + address.charAt(length - 1);
        } else {
            // 长地址保留前3位和后2位
            String prefix = address.substring(0, 3);
            String suffix = address.substring(length - 2);
            String mask = StringUtils.repeat("*", Math.min(length - 5, 8));
            return prefix + mask + suffix;
        }
    }

    /**
     * 检查地址是否已经被掩码
     *
     * @param address 地址
     * @return 是否已掩码
     */
    public static boolean isMasked(String address) {
        return StringUtils.isNotBlank(address) && address.contains("*");
    }

    public static void main(String[] args) {
        // 测试用例
        String[] testAddresses = {
                "北京市朝阳区望京东路4号院恒电大厦A座1001室",
                "上海市浦东新区陆家嘴环路1000号国金中心88楼",
                "广州市天河区珠江新城花城大道123号富力盈凯广场35层",
                "深圳市南山区科技园南区深南大道10000号腾讯大厦",
                "杭州市西湖区文三路259号昌地火炬大厦1号楼17楼",
                "成都市高新区天府大道中段666号希望大厦B座",
                "重庆市渝中区解放碑步行街88号时代广场3楼301号",
                "武汉市洪山区珞喻路129号华中科技大学",
                "南京市鼓楼区中山路1号紫峰大厦88楼",
                "天津市和平区南京路108号新世界百货",
                "西安市雁塔区高新四路15号",
                "苏州市工业园区星海街328号创意产业园A区2栋3楼",
                "青岛市市南区香港中路76号颐中皇冠假日酒店",
                "大连市中山区人民路15号国际金融大厦",
                "厦门市思明区湖滨南路76号百脑汇科技大厦",
                // 特殊情况测试
                "北京市",
                "上海",
                "广州天河区",
                "深圳南山",
                "香港特别行政区中环金融街1号",
                "澳门特别行政区新马路123号",
                "台湾省台北市信义区101大楼",
                "新疆维吾尔自治区乌鲁木齐市天山区",
                "内蒙古自治区呼和浩特市新城区",
                "广西壮族自治区南宁市青秀区",
                // 没有省市区的地址
                "望京东路4号院恒电大厦A座1001室",
                "陆家嘴环路1000号国金中心88楼",
                "科技园南区深南大道10000号腾讯大厦"
        };

        System.out.println("=== 地址掩码测试结果 ===\n");

        for (String address : testAddresses) {
            String masked = maskAddress(address);
            System.out.printf("原地址: %s\n", address);
            System.out.printf("掩码后: %s\n", masked);
            System.out.printf("已掩码: %s\n", isMasked(masked));
            System.out.println("---");
        }

        // 边界情况测试
        System.out.println("\n=== 边界情况测试 ===\n");

        String[] edgeCases = {
                null,
                "",
                "   ",
                "北",
                "北京",
                "北京市",
                "a",
                "ab",
                "abc",
                "abcd",
                "abcde"
        };

        for (String address : edgeCases) {
            String masked = maskAddress(address);
            System.out.printf("原地址: '%s' -> 掩码后: '%s'\n", address, masked);
        }

        System.out.println("\n=== 手机号掩码测试 ===\n");

        String[] mobileNumbers = {
                "13800138000",
                "1380013800",
                "138001380",
                "13800138",
                "1380013",
                "138001",
                "13800",

        };
        for (String mobile : mobileNumbers) {
            String masked = maskMobile(mobile);
            System.out.printf("原手机号: %s -> 掩码后: %s\n", mobile, masked);
        }

    }
}