package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import ru.skypro.homework.model.Type;
import javax.persistence.*;
import java.util.Collection;

//
@Data
@Entity
@Table(name = "mailings")
public class Mailing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Type type;
    Integer recipientIndex;
    String recipientAddress;
    String recipientName;

}
