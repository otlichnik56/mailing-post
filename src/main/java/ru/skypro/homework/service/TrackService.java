package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Status;
import ru.skypro.homework.dto.TrackDto;
import ru.skypro.homework.entity.Mailing;
import ru.skypro.homework.entity.Post;
import ru.skypro.homework.entity.Track;
import ru.skypro.homework.repository.MailingRepository;
import ru.skypro.homework.repository.PostRepository;
import ru.skypro.homework.repository.TrackRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;
    private final PostRepository postRepository;
    private final MailingRepository mailingRepository;

    public String registeredMailing(TrackDto trackDto) {
        Status status = Status.REGISTERED;
        return generateTextAndSaveEntity(status, trackDto);
    }

    public String arriveMailing(TrackDto trackDto) {
        Status status = Status.ARRIVE;
        return generateTextAndSaveEntity(status, trackDto);
    }

    public String departedMailing(TrackDto trackDto) {
        Status status = Status.DEPARTED;
        return generateTextAndSaveEntity(status, trackDto);
    }

    public String receivedMailing(TrackDto trackDto) {
        Status status = Status.RECEIVED;
        return generateTextAndSaveEntity(status, trackDto);
    }

    public List<String> trackHistoryMailing(Integer id) {
        List<Track> trackHistory = trackRepository.findByMailingId(id);
        return trackHistory.stream().map(this::getString).collect(Collectors.toList());
    }

    /**
     * Генерирует строку ответа для контроллера
     * @param status
     * @param trackDto
     * @return
     */
    private String generateTextAndSaveEntity(Status status, TrackDto trackDto) {
        if (checkForAvailability(trackDto)) {
            if (checkLogic(status, trackDto)) {
                Track track = insert(trackDto);
                track.setStatus(status);
                trackRepository.save(track);
                return getString(track);
            } else {
                return "violation of the logic of movement";
            }
        } else {
            return "data entry error";
        }
    }

    /**
     * Генерирует строку в зависимости от пришедшей сущности Track
     * @param track
     * @return
     */
    private String getString(Track track) {
        switch (track.getStatus()) {
            case REGISTERED:
                return "Mailing " + track.getMailingId() + " registered with the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            case ARRIVE:
                return "Mailing " + track.getMailingId() + " arrive at the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            case DEPARTED:
                return "Mailing " + track.getMailingId() + " left the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            case RECEIVED:
                return "Mailing " + track.getMailingId() + " issued to the recipient at the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            default:
                return null;
        }
    }

    /**
     * Вводит значения TrackDto в сущность Track
     * а также тукущую дату и время и статус переданный публичными методами текущего сервиса
     * @param trackDto
     * @return
     */
    private Track insert(TrackDto trackDto) {
        Track track = new Track();
        track.setMailingId(trackDto.getMailing());
        track.setPostIndex(trackDto.getPost());
        track.setDateTime(LocalDateTime.now());
        return track;
    }

    /**
     * Проверяет на наличие почтового отправления с таким ID
     * и почтового отделения с таким индексом
     * @param trackDto
     * @return
     */
    private boolean checkForAvailability(TrackDto trackDto) {
        Post post = postRepository.findByIndex(trackDto.getPost());
        Mailing mailing = mailingRepository.findById(trackDto.getMailing()).orElse(null);
        return post != null && mailing != null;
    }

    /**
     * Проверяет на логику движения почтового отправления,
     * например отправление было зарегистрировано в отделении 101010 значит следующим шагом оно должно покинуть отделение 101010
     * @param status
     * @param trackDto
     * @return
     */
    private boolean checkLogic(Status status, TrackDto trackDto) {
        List<Track> history = trackRepository.findByMailingId(trackDto.getMailing());
        if (history.isEmpty()) {
            if (status.equals(Status.REGISTERED)) {
                return true;
            }
        } else {
            Track track = history.get(history.size() - 1);
            Mailing mailing = mailingRepository.findById(track.getMailingId()).orElse(null);
            /**Если отправление зарегистрировано и новая запись убыло с одинаковыми индексами
             * вернет true
             */
            if (track.getStatus().equals(Status.REGISTERED) && status.equals(Status.DEPARTED) && track.getPostIndex().equals(trackDto.getPost())) {
                return true;
            }
            /**Если отправление убыло и новая запись прибыло с разными индексами
             * вернет true
             */
            if (track.getStatus().equals(Status.DEPARTED) && status.equals(Status.ARRIVE) && !track.getPostIndex().equals(trackDto.getPost())) {
                return true;
            }
            /**Если отправление прибыло и новая запись убыло с одинаковыми индексами
             * вернет true
             */
            if (track.getStatus().equals(Status.ARRIVE) && status.equals(Status.DEPARTED) && track.getPostIndex().equals(trackDto.getPost())) {
                return true;
            }
            /**Если отправление прибыло и новая запись выдано с одинаковыми индексами
             * вернет true
             */
            if (track.getStatus().equals(Status.ARRIVE) && status.equals(Status.RECEIVED) && track.getPostIndex().equals(trackDto.getPost()) && trackDto.getPost().equals(mailing.getRecipientIndex())) {
                return true;
            }
        }
        return false;
    }


}
