package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Mailing;
import ru.skypro.homework.entity.Post;
import ru.skypro.homework.repository.MailingRepository;
import ru.skypro.homework.repository.PostRepository;

import java.util.List;
//
@Service
@AllArgsConstructor
public class MailingService {
    private final MailingRepository mailingRepository;
    private final PostRepository postRepository;

    public List<Mailing> getAllMailing() {
        return mailingRepository.findAll();
    }

    public Mailing getMailing(Integer id) {
        return mailingRepository.findById(id).orElse(null);
    }

    public Mailing createMailing(Mailing mailing) {
        Post post = postRepository.findByIndex(mailing.getRecipientIndex());
        Mailing mailingCreate = mailingRepository.findById(mailing.getId()).orElse(null);
        if (mailingCreate == null && post != null) {
            return mailingRepository.save(mailing);
        } else {
            return null;
        }
    }

    public Mailing updateMailing(Mailing mailing) {
        Post post = postRepository.findByIndex(mailing.getRecipientIndex());
        Mailing mailingUpdate = mailingRepository.findById(mailing.getId()).orElse(null);
        if (mailingUpdate != null && post != null) {
            return mailingRepository.save(mailing);
        } else {
            return null;
        }
    }

    public boolean deleteMailing(Integer id) {
        Mailing mailing = mailingRepository.findById(id).orElse(null);
        if (mailing == null) {
            return false;
        } else {
            mailingRepository.deleteById(id);
            return true;
        }
    }

}
