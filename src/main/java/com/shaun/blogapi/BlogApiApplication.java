package com.shaun.blogapi;

import com.shaun.blogapi.entity.Author;
import com.shaun.blogapi.entity.Comment;
import com.shaun.blogapi.entity.Post;
import com.shaun.blogapi.repo.AuthorRepository;
import com.shaun.blogapi.repo.CommentRepository;
import com.shaun.blogapi.repo.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApiApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext con = SpringApplication.run(BlogApiApplication.class, args);
		Object ds = con.getBean("dataSource");
		System.out.println(ds);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepository, AuthorRepository authorRepository, CommentRepository commentRepository) {
		return args -> {
			// Create dummy authors
			Author author1 = Author.builder()
					.firstname("John")
					.lastname("Cena")
					.email("John.Cena@example.com")
					.build();
			authorRepository.save(author1);

			Author author2 = Author.builder()
					.firstname("Jane")
					.lastname("Street")
					.email("jane.street@example.com")
					.build();
			authorRepository.save(author2);

			// Create dummy posts
			Post post1 = Post.builder()
					.title("First Post")
					.content("This is the content of the first post.")
					.author(author1)
					.build();
			postRepository.save(post1);

			Post post2 = Post.builder()
					.title("Second Post")
					.content("This is the content of the second post.")
					.author(author2)
					.build();
			postRepository.save(post2);

			// Create dummy comments
			Comment comment1 = Comment.builder()
					.content("This is a comment on the first post.")
					.author(author2)
					.post(post1)
					.build();
			commentRepository.save(comment1);

			Comment comment2 = Comment.builder()
					.content("This is a comment on the second post.")
					.author(author1)
					.post(post2)
					.build();
			commentRepository.save(comment2);
		};
	}

}
