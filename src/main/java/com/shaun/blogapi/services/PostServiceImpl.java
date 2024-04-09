package com.shaun.blogapi.services;

import com.shaun.blogapi.dto.AuthorRequest;
import com.shaun.blogapi.dto.CommentRequest;
import com.shaun.blogapi.dto.CreatePostRequest;
import com.shaun.blogapi.entity.Author;
import com.shaun.blogapi.entity.Comment;
import com.shaun.blogapi.entity.Post;
import com.shaun.blogapi.repo.AuthorRepository;
import com.shaun.blogapi.repo.CommentRepository;
import com.shaun.blogapi.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Optional<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return Optional.ofNullable(posts);
    }

    @Override
    public Optional<Post> getPostById(Integer postId) {
        return postRepository.findPostByPostId(postId);
    }

    @Override
    public Post createPost(CreatePostRequest createPostRequest) {

        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        Author author = authorRepository.findByEmail(createPostRequest.getAuthor().getEmail());
        post.setAuthor(author);
        post.setComments(new ArrayList<>());

        postRepository.save(post);
        Post latest = postRepository.findByTitle(createPostRequest.getTitle());
        return latest;
    }

    @Override
    public Comment createComment(Integer postId, CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        Author author = authorRepository.findByEmail(commentRequest.getAuthor().getEmail());
        comment.setAuthor(author);

        Optional<Post> optionalPost = postRepository.findPostByPostId(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            comment.setPost(post);

            comment.setAuthor(author);

            return commentRepository.save(comment);
        }

        return comment;
    }
    @Override
    public List<Comment> getComments(Integer postId) {
        Post post = postRepository.findPostByPostId(postId).orElse(null);
        List<Comment> comments = new ArrayList<>();
        if (post != null) {
            System.out.println(post.comments);
            return post.comments;
        }
        System.out.println(comments.toString());
        return  comments;
    }




    @Override
    public void deletePostbyPostId(Integer postId) {
        Optional<Post> postOptional =postRepository.findPostByPostId(postId);
        if(postOptional.isPresent())
        {
            Post p =postOptional.get();
            p.setDeleted(true);
            List<Comment> comments = p.getComments();
            if (comments != null) {
                for (Comment comment : comments) {
                    comment.setDeleted(true);
                }
            }
            postRepository.save(p);
        }
    }
    @Override
    public Post updatePost(Integer postId, CreatePostRequest postDTO) {
        Optional<Post> existingPostOptional = postRepository.findPostByPostId(postId);
        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            existingPost.setTitle(postDTO.getTitle());
            existingPost.setContent(postDTO.getContent());

            // Update the author information
            Author author = existingPost.getAuthor();
            AuthorRequest authorDTO = postDTO.getAuthor();
            author.setFirstname(authorDTO.getFirstname());
            author.setLastname(authorDTO.getLastname());
            author.setEmail(authorDTO.getEmail());

            return postRepository.save(existingPost);
        } else {
            return null;
        }
    }
}
