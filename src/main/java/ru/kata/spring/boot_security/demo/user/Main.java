package ru.kata.spring.boot_security.demo.user;

public class Main {
    public static void main(String[] args) {
        User user = new User("Oleg", "komi", 29, "alegakom", "q1w2e3r4" );
        Role role = new Role("ADMIN");
        Role role1 = new Role("USER");

        user.addUserRole(role);
        user.addUserRole(role1);
        System.out.println(user.getAuthorities());
    }
}
