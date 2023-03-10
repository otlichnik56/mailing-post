package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.entity.Post;
import ru.skypro.homework.service.PostService;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Вывод всех почтовых отделений",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Post[].class)
                    )
            )
    })
    @GetMapping
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }


    @GetMapping("/{index}")
    public ResponseEntity<?> getPost(@PathVariable(value = "index") Integer index) {
        logger.info("PostController. method getPost. Post = " + index);
        Post post = postService.getPost(index);
        if (post == null) {
            return ResponseEntity.status(404).body("Post office not found");
        } else {
            return ResponseEntity.status(200).body(post);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        logger.info("PostController. method createPost. Post = " + post);
        if (postService.createPost(post) == null) {
            return ResponseEntity.status(409).body("Perhaps a post office with this postal code already exists");
        } else {
            return ResponseEntity.status(200).body(post);
        }
    }

    @PatchMapping
    public ResponseEntity<?> updatePost(@RequestBody Post post) {
        logger.info("PostController. method updatePost. Post = " + post);
        if (postService.updatePost(post) == null) {
            return ResponseEntity.status(404).body("Post office not found");
        } else {
            return ResponseEntity.status(200).body(post);
        }
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<?> deletePost(@PathVariable Integer index) {
        logger.info("PostController. method deletePost. Index = " + index);
        if (postService.deletePost(index)) {
            return ResponseEntity.status(200).body("Post office successfully deleted");
        } else {
            return ResponseEntity.status(404).body("Post office not found");
        }
    }

}
