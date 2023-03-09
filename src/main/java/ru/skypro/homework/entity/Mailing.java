package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.model.Type;
import javax.persistence.*;
//
@Data
@Entity
@Table(name = "mailing")
public class Mailing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Type type;
    Integer recipientIndex;
    String recipientAddress;
    String recipientName;

}
