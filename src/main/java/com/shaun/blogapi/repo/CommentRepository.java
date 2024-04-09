package com.shaun.blogapi.repo;

import com.shaun.blogapi.entity.Author;
import com.shaun.blogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
