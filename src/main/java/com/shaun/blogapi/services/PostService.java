package com.shaun.blogapi.services;

import com.shaun.blogapi.dto.CommentRequest;
import com.shaun.blogapi.dto.CreatePostRequest;
import com.shaun.blogapi.entity.Comment;
import com.shaun.blogapi.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<List<Post>> getAllPosts();

    Optional<Post> getPostById(Integer postId);

    Post createPost(CreatePostRequest createPostRequestm);

    List<Comment> getComments(Integer postIdInt);

    Comment createComment(Integer postId, CommentRequest commentRequest);

    void deletePostbyPostId(Integer postId);

    Post updatePost(Integer postId, CreatePostRequest postDTO);
}
