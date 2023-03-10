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

    public String subsidedMailing(TrackDto trackDto) {
        Status status = Status.SUBSIDED;
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

    private String generateTextAndSaveEntity(Status status, TrackDto trackDto) {
        if (check(trackDto)) {
            Track track = insert(trackDto);
            track.setStatus(status);
            trackRepository.save(track);
            return getString(track);
        } else {
            return "data entry error";
        }
    }

    private String getString(Track track) {
        switch (track.getStatus()) {
            case REGISTERED:
                return "Mailing " + track.getMailingId() + " registered with the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            case ARRIVE:
                return "Mailing " + track.getMailingId() + " arrive at the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            case SUBSIDED:
                return "Mailing " + track.getMailingId() + " left the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            case RECEIVED:
                return "Mailing " + track.getMailingId() + " issued to the recipient at the post office " + track.getPostIndex() + " . Data and time: " + track.getDateTime();
            default:
                return null;
        }
    }

    private Track insert(TrackDto trackDto) {
        Track track = new Track();
        track.setMailingId(trackDto.getMailing());
        track.setPostIndex(trackDto.getPost());
        track.setDateTime(LocalDateTime.now());
        return track;
    }

    private boolean check(TrackDto trackDto) {
        Post post = postRepository.findByIndex(trackDto.getPost());
        Mailing mailing = mailingRepository.findById(trackDto.getMailing()).orElse(null);
        return post != null && mailing != null;
    }

}
