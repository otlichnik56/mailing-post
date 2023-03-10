package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    Integer index;

    String name;
    String address;

}
