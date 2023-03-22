package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * extends JpaRepository<Student, Long> 表示数据的类型为 Student，
 * Student 的 ID 是 Long 类型
 *
 * @author zhou
 * @date 2023/3/22
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
