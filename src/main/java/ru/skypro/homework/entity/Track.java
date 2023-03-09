package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
//
@Data
@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer mailingId;
    Integer PostIndex;

    LocalDateTime dateTime;
    String status;

}
