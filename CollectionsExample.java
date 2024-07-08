package edu.sdccd.cisc191.template;


import java.util.ArrayList;
import java.util.HashMap;

public class CollectionsExample {

    public static void main(String[] args) {
        // Example using HashMap for quick access by name
        HashMap<String, User> userMap = new HashMap<>();
        userMap.put("Alice", new User("Alice", 25));
        userMap.put("Bob", new User("Bob", 30));

        User user = userMap.get("Alice");
        System.out.println(user);

        // Example using ArrayList for a list of users
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("Carol", 28));
        userList.add(new User("Dave", 22));

        for (User u : userList) {
            System.out.println(u);
        }
    }

    static class User {
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}