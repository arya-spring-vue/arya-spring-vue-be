package com.arya.spring.vue.aryaspringvuebe.controller;

import com.arya.spring.vue.aryaspringvuebe.Student;
import com.arya.spring.vue.aryaspringvuebe.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "/redis")
public class RedisController {
    Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(path = "/createStudent")
    @ResponseBody
    public String createStudent(){
        Student student = new Student();
        student.setId("2019001");
        student.setName("FrankKai的儿子");
        student.setGender(Student.Gender.MALE);
        student.setGrade(1);
        studentRepository.save(student);

        logger.info("新建一个学生: {}", student);

        return "创建学生成功";
    }

    @GetMapping(path = "/readStudents")
    @ResponseBody
    public Iterable<Student> readStudents() {
        return studentRepository.findAll();
    }

    // 通过id查找到student实例
    public Student getStudentById(String id) {
        // 根据id查询出对应的持久化对象
        Optional<Student> student = studentRepository.findById(id);
        return student.get();
    }

    @PutMapping(path = "/updateStudent/{id}")
    public @ResponseBody
    String updateStudent(@PathVariable(value = "id") String id , @Valid @RequestBody Student studentDetails){
        // 通过@PathVariable传入关键id，通过@RequestBody传入具体更新数据
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setGender(studentDetails.getGender());
        student.setGrade(studentDetails.getGrade());
        studentRepository.save(student);
        return "更新学生成功！";
    }

    // 删除一个好友
    @DeleteMapping(path = "/deleteStudent/{id}")
    public @ResponseBody String deleteStudent(@PathVariable(value = "id") String id){
        studentRepository.deleteById(id);
        return "删除用户成功！";
    }
}
