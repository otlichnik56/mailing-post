package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.TrackService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/track")
public class TrackController {

    private final Logger logger = LoggerFactory.getLogger(TrackController.class);
    private final TrackService trackService;


}
