package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Student class
 * 注意，只能使用 javax.persistence 包下的注释，这样方便迁移
 *
 * @author zhou
 * @date 2023/3/21
 */
@Entity(
        // 定义实体的名称
        name = "Student"
)
@Table(
        // 定义表名
        name = "student",
        // 设定 unique 约束 / unique index / unique key
        uniqueConstraints = {
                // 设定 unique index 的索引名称，并设定作用的字段
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Student {

    /**
     * 设定为 ID 字段：@Id
     * 指定 Sequence Generator：@SequenceGenerator
     * 设定自增的参数：@GeneratedValue
     */
    @Id
    @SequenceGenerator(
            // 指定 Sequence Generator 的名称
            name = "student_sequence_generator",
            // 指定 Sequence 的名称
            sequenceName = "student_sequence",
            // 指定每次自增的时候，增长多少（默认就是 1）
            allocationSize = 1
    )
    @GeneratedValue(
            // 设定自增的策略，这里是根据 Sequence Generator 来自增
            strategy = GenerationType.SEQUENCE,
            // 设定使用哪个 Generator
            generator = "student_sequence_generator"
    )
    @Column(
            // 指定字段名称
            name = "id",
            // 该字段不能改名
            updatable = false
    )
    private Long id;

    @Column(
            // 字段名默认就是驼峰转下划线
            name = "first_name",
            // 不能为 null
            nullable = false,
            // 指定字段为 text。如果不指定，String 类型会被指定为 varchar
            columnDefinition = "TEXT"
    )
    private String firstName;

    private String lastName;

    @Column(
            nullable = false
            // 这里可以设定该字段为 unique key：
            // 但是因为在上面已经指定了 unique index，所以这里如果还指定的话，就会让上面的指定失效
            // ,unique = true
            )
    private String email;
    private Integer age;

    public Student() {
    }

    public Student(String firstName,
                   String lastName,
                   String email,
                   Integer age) {
        // 因为这里设定 ID 是自增的，所以构造器不需要 ID
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
