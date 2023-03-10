package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

//
@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    Integer index;

    String name;
    String address;

}
