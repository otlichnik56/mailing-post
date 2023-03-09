package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;
//
@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer index;

    String name;
    String address;

}
