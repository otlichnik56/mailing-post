package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ru.skypro.homework.dto.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JoinColumn(name = "mailing_id")
    Integer mailingId;

    @JoinColumn(name = "post_index")
    Integer postIndex;

    LocalDateTime dateTime;
    Status status;

}
