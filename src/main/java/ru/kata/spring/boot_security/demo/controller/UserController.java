package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.user.Role;
import ru.kata.spring.boot_security.demo.user.User;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired()
    public UserController(UserService us) {
        this.userService = us;
    }

    @GetMapping(value = "/admin")
    public String getUsers(@RequestParam(value = "count", defaultValue = "100") int count, ModelMap model){
        List<User> userList;
        userList = userService.getAllUsers();
        model.addAttribute("userList",
                userList.stream().limit(count).toList());
        // нужно не забыть указать передаваемый объект "userList" в .html
        return "admin";
    }
    @PostMapping(value = "/admin/save")
    public String saveUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "age")  int age,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "role") String role) {
        User user = new User(name, lastName, age, username, password);
        user.addUserRole(new Role(role.toUpperCase()));
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/update/{id}")
    public String update(@PathVariable(value = "id") int id){
        return "update";
    }
    @PostMapping(value ="admin/update/{id}")
    public String updateUserById(@PathVariable(value = "id") int id,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "lastName") String lastName,
                                 @RequestParam(value = "age")  int age,
                                 @RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "role") String role){
        User user = userService.getUserById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        user.setUsername(username);
        user.setPassword(password);
        user.addUserRole(new Role(role.toUpperCase()));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String getUsers(ModelMap model){
        List<User> userList;
        userList = userService.getAllUsers();
        model.addAttribute("userList",
                userList.stream().toList());
        // нужно не забыть указать передаваемый объект "userList" в .html
        return "user";
    }

    @GetMapping(value = "/index")
    public String welcomePage(ModelMap model){
        return "index";
    }
}
