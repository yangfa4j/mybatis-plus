package com.yf;

import com.google.common.collect.Lists;
import com.test.lambda.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {




    @Test
    public void test1() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = integers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test2() {
        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        List<Product> prodList = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5);

        Map<String, List<Product>> prodMap = prodList.stream().collect(Collectors.groupingBy(Product::getCategory));
        System.out.println("prodMap = " + prodMap);

        Map<String, List<Product>> prodMap1 = prodList.stream().collect(Collectors.groupingBy(item -> item.getCategory() + "_" + item.getName()));
        System.out.println("prodMap1 = " + prodMap1);

        Optional<Product> max = prodList.stream().max(Comparator.comparingInt(Product::getNum));
        max.ifPresent(System.out::println);

        int sum = prodList.stream().mapToInt(Product::getNum).sum();
        System.out.println("sum = " + sum);

        Double collect = prodList.stream().collect(Collectors.averagingInt(Product::getNum));
        System.out.println("collect = " + collect);

        String collect1 = prodList.stream().map(Product::getName).collect(Collectors.joining("-"));
        System.out.println("collect1 = " + collect1);

        Map<String, List<Product>> collect2 = prodList.stream().collect(Collectors.groupingBy((product -> {
            if (product.getNum() == 1) return "低级";
            else if (product.getNum() > 1 && product.getNum() < 5) return "中级";
            else return "高级";
        })));
        System.out.println("collect2 = " + collect2);
    }


    @Test
    public void test3() {


    }


}
