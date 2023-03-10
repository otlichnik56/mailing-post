package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.TrackDto;
import ru.skypro.homework.service.TrackService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/track")
public class TrackController {

    private final Logger logger = LoggerFactory.getLogger(TrackController.class);
    private final TrackService trackService;

    @PostMapping("/registered")
    public String registeredMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method registeredMailing. TrackDto = " + trackDto);
        return trackService.registeredMailing(trackDto);
    }

    @PostMapping("/arrive")
    public String arriveMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method arriveMailing. TrackDto = " + trackDto);
        return trackService.arriveMailing(trackDto);
    }

    @PostMapping("/departed")
    public String departedMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method departedMailing. TrackDto = " + trackDto);
        return trackService.departedMailing(trackDto);
    }

    @PostMapping("/received")
    public String receivedMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method receivedMailing. TrackDto = " + trackDto);
        return trackService.receivedMailing(trackDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> trackHistoryMailing(@PathVariable Integer id) {
        logger.info("TrackController. method trackHistoryMailing. Mailing ID = " + id);
        List<String> history = trackService.trackHistoryMailing(id);
        if (history.isEmpty()) {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        } else {
            return ResponseEntity.status(200).body(history);
        }
    }

}
