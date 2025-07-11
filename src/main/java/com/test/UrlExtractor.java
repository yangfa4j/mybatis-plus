package com.test;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtractor {
    public static void main(String[] args) {
        // 输入的HTML文本
        String html = "<p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/fd90b4a111ee470eae021fb506bbe24f.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/fd90b4a111ee470eae021fb506bbe24f.jpg\\\" style=\\\"\\\" title=\\\"1.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/9f944cf22098437b86454ca869afd3f5.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/9f944cf22098437b86454ca869afd3f5.jpg\\\" style=\\\"\\\" title=\\\"2.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/72e1132899b04b8d8f68a9879664eefd.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/72e1132899b04b8d8f68a9879664eefd.jpg\\\" style=\\\"\\\" title=\\\"3.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/00718c6a1fee440f87c15fc18585808e.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/00718c6a1fee440f87c15fc18585808e.jpg\\\" style=\\\"\\\" title=\\\"4.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/22d6388882fc436592cb93dfff54ffb4.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/22d6388882fc436592cb93dfff54ffb4.jpg\\\" style=\\\"\\\" title=\\\"5.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/272c8db637834eefa6a9fe2c1a2df07f.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/272c8db637834eefa6a9fe2c1a2df07f.jpg\\\" style=\\\"\\\" title=\\\"11.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/f037656583644c4b8e725759d1f83a85.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/f037656583644c4b8e725759d1f83a85.jpg\\\" style=\\\"\\\" title=\\\"x_01.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/76a9005f9ed3498292ad98527419180c.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/76a9005f9ed3498292ad98527419180c.jpg\\\" style=\\\"\\\" title=\\\"x_02.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/c5c73151a47d43529d00bdaa2f4e0897.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/c5c73151a47d43529d00bdaa2f4e0897.jpg\\\" style=\\\"\\\" title=\\\"x_03.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/7e969c3e60fa4c3282b012cecdee086a.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/7e969c3e60fa4c3282b012cecdee086a.jpg\\\" style=\\\"\\\" title=\\\"x_04.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/6b797274404c4c568439d5cf0f1a8fcd.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/6b797274404c4c568439d5cf0f1a8fcd.jpg\\\" style=\\\"\\\" title=\\\"x_05.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/d2052d3ceeae420b8238be4d9050db55.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/d2052d3ceeae420b8238be4d9050db55.jpg\\\" style=\\\"\\\" title=\\\"x_06.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/fc7ca70c705f44d38638a162154e3982.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/fc7ca70c705f44d38638a162154e3982.jpg\\\" style=\\\"\\\" title=\\\"x_07.jpg\\\"/></p><p><img src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/b0bf227a9a69453386713b61cd8dde4f.jpg\\\" _src=\\\"https://himall-storage-1259069382.cos.ap-nanjing.myqcloud.com/web/Storage/Shop/1363/Products/46092/remark/b0bf227a9a69453386713b61cd8dde4f.jpg\\\" style=\\\"\\\" title=\\\"x_08.jpg\\\"/></p><p><br/></p>";

        // 定义正则表达式
        String regex = "https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        // 提取所有匹配的URL
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group());
        }

        // 输出结果
        System.out.println("提取到的URL数量：" + urls.size());
        for (String url : urls) {
            System.out.println(url);
        }
    }

    private String camelToSnakeCase(String camelCase) {
        if (StringUtils.isEmpty(camelCase)) {
            return camelCase;
        }

        return camelCase
                .replaceAll("([a-z])([A-Z])", "$1_$2")  // 在小写字母和大写字母之间插入下划线
                .toLowerCase();  // 转换为小写
    }
}