package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.TrackRepository;
//
@Service
@AllArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;


}
