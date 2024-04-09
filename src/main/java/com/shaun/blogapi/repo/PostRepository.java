package com.shaun.blogapi.repo;

import com.shaun.blogapi.entity.Comment;
import com.shaun.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findByTitle(String title);

    @Query("""
        select p from Post p where p.id = :postId and p.isDeleted = false
        """)
    Optional<Post> findPostByPostId(Integer postId);



}
