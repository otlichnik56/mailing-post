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
    public ResponseEntity<?> getMailing(@PathVariable Integer id) {
        logger.info("MailingController. method getMailing. Mailing id = " + id);
        Mailing mailing = mailingService.getMailing(id);
        if (mailing == null) {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMailing(@RequestBody Mailing mailing) {
        logger.info("MailingController. method createMailing. Mailing = " + mailing);
        if (mailingService.createMailing(mailing) == null) {
            return ResponseEntity.status(409).body("Perhaps a mailing with this identifier already exists");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateMailing(@RequestBody Mailing mailing) {
        logger.info("MailingController. method updateMailing. Mailing = " + mailing);
        if (mailingService.updateMailing(mailing) == null) {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMailing(@PathVariable Integer id) {
        logger.info("MailingController. method deleteMailing. Id = " + id);
        if (mailingService.deleteMailing(id)) {
            return ResponseEntity.status(200).body("Mail item deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        }
    }


}
