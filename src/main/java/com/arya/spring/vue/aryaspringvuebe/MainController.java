package com.arya.spring.vue.aryaspringvuebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "/spring/vue")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    // request body: form-data
    @PostMapping(path = "/createUser")
    public @ResponseBody String createUser(@RequestParam String name, @RequestParam String sex, @RequestParam Integer age, @RequestParam String email){
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        user.setEmail(email);
        userRepository.save(user);
        return "创建用户成功！";
    }

    @GetMapping(path = "/readUsers")
    public @ResponseBody Iterable<User> readUsers() {
        return userRepository.findAll();
    }

    // 通过id查找到user实例
    public User getUserById(Integer id) {
        // 根据id查询出对应的持久化对象
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    // request body: raw JSON(application/json)
    // 按照这样Restful形式的PUT写接口，可以清晰地区分出PK和其它字段
    @PutMapping(path = "/updateUser/{id}")
    public @ResponseBody String updateUser(@PathVariable(value = "id") Integer id ,@Valid @RequestBody User userDetails){
        // 通过@PathVariable传入关键id，通过@RequestBody传入具体更新数据
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setSex(userDetails.getSex());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        userRepository.save(user);
        return "更新用户成功！";
    }

    // 删除一个好友
    @DeleteMapping(path = "/deleteUser/{id}")
    public @ResponseBody String deleteUser(@PathVariable(value = "id") Integer id){
        userRepository.deleteById(id);
        return "删除用户成功！";
    }
}