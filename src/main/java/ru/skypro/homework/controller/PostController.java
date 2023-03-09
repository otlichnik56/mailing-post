package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
                    description = "Все почтовые отделения",
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

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Поиск почтового отделения по индексу",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Post.class)
                    )
            )
    })
    @GetMapping("/{index}")
    public Post getPost(@Parameter(name = "Индекс почтового отделения", example = "461505") @PathVariable Integer index) {
        return postService.getPost(index);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PatchMapping("/{index}")
    public Post updatePost(@PathVariable Integer index,
                           @RequestParam Post post) {
        return postService.updatePost(index, post);
    }

}
