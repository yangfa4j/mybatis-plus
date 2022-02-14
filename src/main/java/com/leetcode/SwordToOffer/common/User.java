package com.leetcode.SwordToOffer.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date 2022/2/10
 * @Author yangfa
 * @Description
 */
public class User {

    private String name;

    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User("noPermissionButApply"));
        users.add(new User("noPermissionButApply"));
        users.add(new User("direct"));
        users.add(new User("noPermission"));
        users.add(new User("noPermission"));
        users.add(new User("noPermission"));

        User direct = new User("direct");
        User direct1 = new User("direct");

        direct.setUsers(users);
        direct1.setUsers(users);

        User noPermission = new User("noPermission");
        User noPermission1 = new User("noPermission");
        noPermission.setUsers(users);
        noPermission1.setUsers(users);

        User noPermissionButApply = new User("noPermissionButApply");
        User noPermissionButApply1 = new User("noPermissionButApply");
        noPermissionButApply.setUsers(users);
        noPermissionButApply1.setUsers(users);

        ArrayList<User> result = new ArrayList<>();
        result.add(direct);
        result.add(direct1);
        result.add(noPermission);
        result.add(noPermission1);
        result.add(noPermissionButApply);
        result.add(noPermissionButApply1);

        List<User> users1 = User.groupBy(result);
        for (User user : users1) {
            List<User> users2 = User.groupBy(user.getUsers());
            user.setUsers(users2);
        }
        System.out.println("users1 = " + users1);

    }

    public static List<User> groupBy(List<User> users){
        ArrayList<User> result = new ArrayList<>();
        Map<String, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getName));

        if (map.containsKey("direct")){
            result.addAll(map.get("direct"));
        }
        if (map.containsKey("noPermissionButApply")){
            result.addAll(map.get("noPermissionButApply"));
        }
        if (map.containsKey("noPermission")){
            result.addAll(map.get("noPermission"));
        }

        return result;

    }
}
