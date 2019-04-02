package com.arya.spring.vue.aryaspringvuebe;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String email;

    @Column(name="CreateTime", updatable = false)
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name="UpdateTime")
    @UpdateTimestamp
    private Timestamp updateTime;
}
