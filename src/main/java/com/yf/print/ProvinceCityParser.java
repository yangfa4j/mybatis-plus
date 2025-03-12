package com.yf.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;

public class ProvinceCityParser {

    public static void main(String[] args) {
        String filePath = "src/main/resources/省市区.txt"; // 文件路径
        ObjectMapper objectMapper = new ObjectMapper();

        String province = "河北省-石家庄市-晋州市"; // 用户输入的省市区路径

        try {
            // 解析 JSON 文件为 List<Region>
            List<Region> regions = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Region.class));
            // 根据路径查找省、市、区的 ID
            Map<String, String> result = findIdsByPath(regions, province);
            System.out.println("匹配结果: " + result);
        } catch (IOException e) {
            System.err.println("解析文件失败！");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("匹配失败: " + e.getMessage());
        }
    }

    private static Map<String, String> findIdsByPath(List<Region> regions, String path) {
        String[] levels = path.split("-"); // 按 "-" 分割路径
        if (levels.length != 3) {
            throw new IllegalArgumentException("路径格式错误，应为 '省-市-区' 格式");
        }
        Map<String, String> result = new HashMap<>();
        String provinceName = levels[0];
        String cityName = levels[1];
        String districtName = levels[2];

        // 查找省
        Region province = findRegionByName(regions, provinceName);
        if (province == null) {
            throw new IllegalArgumentException("未找到省: " + provinceName);
        }
        result.put("省", province.getId());

        // 查找市
        Region city = findRegionByName(province.getChildren(), cityName);
        if (city == null) {
            throw new IllegalArgumentException("未找到市: " + cityName);
        }
        result.put("市", city.getId());

        // 查找区
        Region district = findRegionByName(city.getChildren(), districtName);
        if (district == null) {
            throw new IllegalArgumentException("未找到区: " + districtName);
        }
        result.put("区", district.getId());
        return result;
    }


    /**
     * 根据名称查找 Region 对象
     *
     * @param regions 当前层级的 Region 列表
     * @param name 要查找的名称
     * @return 匹配的 Region 对象，如果未找到则返回 null
     */
    private static Region findRegionByName(List<Region> regions, String name) {
        if (regions == null || regions.isEmpty()) {
            return null;
        }
        for (Region region : regions) {
            if (region.getTitle().equals(name)) {
                return region;
            }
        }
        return null;
    }
}

/**
 * 定义 Region 类，包含 id、title 和 children 字段
 */
class Region {
    private String id;
    private String title;
    private List<Region> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Region> getChildren() {
        return children;
    }

    public void setChildren(List<Region> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Region{id='" + id + "', title='" + title + "', children=" + (children == null ? "[]" : children.size()) + "}";
    }
}
