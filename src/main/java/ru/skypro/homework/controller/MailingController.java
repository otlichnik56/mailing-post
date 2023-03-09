package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.MailingService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mailing")
public class MailingController {

    private final Logger logger = LoggerFactory.getLogger(MailingController.class);
    private final MailingService mailingService;


}
