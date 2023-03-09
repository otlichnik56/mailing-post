package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.entity.Mailing;
import ru.skypro.homework.service.MailingService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mailing")
public class MailingController {

    private final Logger logger = LoggerFactory.getLogger(MailingController.class);
    private final MailingService mailingService;

    @GetMapping
    public List<Mailing> getAllMailing() {
        return mailingService.getAllMailing();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMailing(@Parameter(name = "Индекс почтового отделения", example = "461505") @PathVariable Integer id) {
        logger.info("MailingController. method getMailing. Mailing id = " + id);
        Mailing mailing = mailingService.getMailing(id);
        if (mailing == null) {
            return ResponseEntity.status(404).body("Такого почтового отправления не существует");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMailing(@RequestBody Mailing mailing) {
        logger.info("MailingController. method createMailing. Mailing = " + mailing);
        if (mailingService.createMailing(mailing) == null) {
            return ResponseEntity.status(409).body("Возможно, почтовое отправление с таким идентификатором уже существует");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateMailing(@RequestParam Mailing mailing) {
        logger.info("MailingController. method updateMailing. Mailing = " + mailing);
        if (mailingService.updateMailing(mailing) == null) {
            return ResponseEntity.status(404).body("Почтового отправления с таким идентификатором не существует");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMailing(@PathVariable Integer id) {
        logger.info("MailingController. method deleteMailing. Id = " + id);
        if (mailingService.deleteMailing(id)) {
            return ResponseEntity.status(200).body("Почтовое отправление успешно удалено");
        } else {
            return ResponseEntity.status(404).body("Почтовое отправление с таким индексом не найдено");
        }
    }


}
