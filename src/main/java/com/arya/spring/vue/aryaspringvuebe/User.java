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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String email;

    @Column(name="CreateTime", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name="UpdateTime", insertable = false)
    @UpdateTimestamp
    private Timestamp updateTime;
}
