package com.test.mapstruct.order;

/**
 * @author yangfa
 * @date 2025/6/30 11:36
 * IntelliJ IDEA
 * @description
 */
public class APP {

    public static void main(String[] args) {


        Address address = new Address();
        address.setStreet("吉泰六路");
        address.setCity("成都");


        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setAddress(address);

        Order order = new Order();
        order.setId(1L);
        order.setOrderNo("123456");
        order.setCustomer(customer);


        OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(order);
        System.out.println(orderDto);


    }
}
