package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 在启动类上写了一些简单的 demo
 *
 * @author zhou
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student student = new Student("first name", "last name", "email", 21);
            Student savedStudent = studentRepository.save(student);
            System.out.println("返回的时候，会更新 ID：" + savedStudent);

            List<Student> list = new ArrayList<>();
            int number = 10;
            System.out.println("创建 " + number + " 个新数据...");
            for (int i = 0; i < number; i++) {
                Student stu = new Student("first name " + i, "last name " + i, "email " + i, 20 + i);
                list.add(stu);
            }
            // 可以存储多个
            studentRepository.saveAll(list);

            long count = studentRepository.count();
            System.out.println("可以计算现在有多少行数据：" + count);

            long id = 2L;
            studentRepository.findById(id)
                    .ifPresentOrElse(
                            stu -> System.out.println("可以查找 ID 为 " + id + " 的数据：" + stu),
                            () -> System.out.println("因为返回的是 Optional，所以可以实现查找不到时，完成其他任务的方法"));
        };
    }

}
