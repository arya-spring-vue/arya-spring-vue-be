package com.arya.spring.vue.aryaspringvuebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/spring/vue")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/createUser")
    public @ResponseBody String createUser(@RequestParam String name, @RequestParam String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "创建用户成功！";
    }

    @GetMapping(path = "/readUsers")
    public @ResponseBody Iterable<User> readUsers() {
        return userRepository.findAll();
    }
}