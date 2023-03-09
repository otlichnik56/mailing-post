package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Mailing;
import ru.skypro.homework.repository.MailingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MailingService {
    private final MailingRepository mailingRepository;

    public List<Mailing> getAllMailing() {
        return mailingRepository.findAll();
    }

    public Mailing getMailing(Integer id) {
        return mailingRepository.findById(id).orElse(null);
    }

    public Mailing createMailing(Mailing mailing) {
        return mailingRepository.save(mailing);
    }

    public Mailing updateMailing(Mailing mailing) {
        return mailingRepository.save(mailing);
    }

    public boolean deleteMailing(Integer id) {
        Mailing mailing = mailingRepository.findById(id).orElse(null);
        return mailing != null;
    }

}
