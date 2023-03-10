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
        Post postCreate = postRepository.findById(post.getIndex()).orElse(null);
        if (postCreate == null) {
            return postRepository.save(post);
        } else {
            return null;
        }
    }

    public Post updatePost(Post post) {
        Post postUpdate = postRepository.findByIndex(post.getIndex());
        if (postUpdate != null) {
            return postRepository.save(post);
        } else {
            return null;
        }
    }

    public boolean deletePost(Integer index) {
        Post post = postRepository.findById(index).orElse(null);
        if (post == null) {
            return false;
        } else {
            postRepository.deleteById(index);
            return true;
        }
    }

}
