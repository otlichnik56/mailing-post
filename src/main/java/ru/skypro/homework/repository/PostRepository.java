package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Post;
//
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findByIndex(Integer index);

}
