package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Mailing;
//
@Repository
public interface MailingRepository extends JpaRepository<Mailing, Integer> {

}
