package com.shaun.blogapi.controller;

import com.shaun.blogapi.dto.AuthorRequest;
import com.shaun.blogapi.dto.CommentRequest;
import com.shaun.blogapi.dto.CreatePostRequest;
import com.shaun.blogapi.entity.Author;
import com.shaun.blogapi.entity.Comment;
import com.shaun.blogapi.entity.Post;
import com.shaun.blogapi.repo.AuthorRepository;
import com.shaun.blogapi.repo.CommentRepository;
import com.shaun.blogapi.repo.PostRepository;
import com.shaun.blogapi.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping(value="api/v1")
public class BlogController {

    @Autowired
    PostService postService;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/posts")
    public ResponseEntity<?> getAllPosts()
    {
        try{
            var postList= postService.getAllPosts();
            if (postList.isPresent()) {
                return ResponseEntity.ok(postList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post List not found");
            }
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Server is Down , try again later");

        }

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getTweetByItsId(@PathVariable("postId") String postID)
    {

        try {
            Integer postIdInt = Integer.valueOf(postID);
            var post = postService.getPostById(postIdInt);

            if (post.isPresent()) {
                return ResponseEntity.ok(post);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid postId format");
        }

    }

    @PostMapping("/post")
    public ResponseEntity<?> CreateTweet(@RequestBody CreatePostRequest createPostRequest)
    {

        AuthorRequest authorRequest = createPostRequest.getAuthor();

        Author existingAuthor = authorRepository.findByEmail(authorRequest.getEmail());

        //System.out.println(authorRequest.getLastname()==existingAuthor.getLastname());
        if (existingAuthor != null ) {

            Post createdPost = postService.createPost(createPostRequest);

            // Return the created post with HTTP status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        }
        else {
            // Author doesn't exist, return an error response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author not found");
        }

    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable("postId") String postID)
    {
        try {
            Integer postIdInt = Integer.valueOf(postID);
            Optional<Post> postOptional = postService.getPostById(postIdInt);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                List<Comment> comments = post.getComments();
                return ResponseEntity.ok(comments);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid postId format");
        }

    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable Integer postId, @RequestBody CommentRequest commentRequest) {

        var post = postService.getPostById(postId);

        if(post==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
        Comment createdComment = postService.createComment(postId, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletedAPost(@PathVariable("postId") Integer postId)
    {
        var post = postService.getPostById(postId);

        if(post==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
        postService.deletePostbyPostId(postId);
        return new ResponseEntity<>("post deleted succesfully",HttpStatus.OK);

    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody CreatePostRequest postDTO) {
        var post = postService.getPostById(id);

        if(post==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
        Post updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }


}
