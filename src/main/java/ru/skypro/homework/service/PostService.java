package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Post;
import ru.skypro.homework.repository.PostRepository;
import java.util.List;
//
@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post getPost(Integer index) {
        return postRepository.findById(index).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Integer index, Post post) {
        Post post1 = postRepository.findById(index).orElse(null);
        return postRepository.save(post);
    }

    public boolean deletePost(Integer index) {
        Post post = postRepository.findById(index).orElse(null);
        return post != null;
    }

}
