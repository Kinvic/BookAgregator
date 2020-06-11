package com.bookagregator.controller;

import com.bookagregator.entity.Role;
import com.bookagregator.entity.User;
import com.bookagregator.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/userList")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("/add")
    public String addUserForm(){
        return "userAdd";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam Map<String, String> form){
        User user = new User();
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        Set<Role> userRoles = new TreeSet<>();
        user.setLogin(form.get("login"));
        user.setPassword((form.get("password")));

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                userRoles.add(Role.valueOf(key));
            }
        }
        user.setRoles(userRoles);
        userRepository.save(user);

        return "redirect:/userList";
    }

    @GetMapping("/edit/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/edit")
    public String userSave(
            @RequestParam String login,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setLogin(login);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/userList";
    }
}
