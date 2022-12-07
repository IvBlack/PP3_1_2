package ru.javamentor.PP_3_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.PP_3_1_2.service.UserService;
import ru.javamentor.PP_3_1_2.model.User;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //всех юзеров из БД переберем в цикле во view 'all-users'
    @RequestMapping("/")
    public String showAllUsers(Model mod) {
        List<User> allUsers = userService.getAllUsers();
        mod.addAttribute("allUsers", allUsers);
        return "all-users";
    }


    @PostMapping(value = "/", params = {"addUser"})
    public String addNewUser(ModelMap mod) {
        userService.saveUser(new User());
        mod.addAttribute("allUsers", userService.getAllUsers());
        return "all-users";
    }

    @PatchMapping("/edit/{id}")
    //передаем непустого работника из БД
    public String UpdateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") int id) {
        userService.updateUser(user.getId(), user);
        return "redirect:/";
    }

    @GetMapping(value = "/{id}/edit")
    //в параметре - работник с уже заполненными данными
    public String saveUser(@PathVariable("id") int id, Model mod) {
        mod.addAttribute("user", userService.getUser(id));
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}

