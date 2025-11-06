package com.manalese.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facebook/posts")
public class FacebookController {

    private final FacebookRepository postRepository;

    // Dependency Injection: Spring automatically provides an instance of FacebookRepository
    @Autowired
    public FacebookController(FacebookRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 1. CREATE Post (POST http://localhost:8080/api/facebook/posts)
    @PostMapping
    public ResponseEntity<FacebookPost> createPost(@RequestBody FacebookPost post) {
        // The @PrePersist method in the Entity will set the created/modified dates.
        FacebookPost savedPost = postRepository.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    // 2. READ All Posts (GET http://localhost:8080/api/facebook/posts)
    @GetMapping
    public List<FacebookPost> getAllPosts() {
        return postRepository.findAll();
    }

    // 3. READ Post by ID (GET http://localhost:8080/api/facebook/posts/{id})
    @GetMapping("/{id}")
    public ResponseEntity<FacebookPost> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Return 404 if not found
    }

    // 4. UPDATE Post (PUT http://localhost:8080/api/facebook/posts/{id})
    @PutMapping("/{id}")
    public ResponseEntity<FacebookPost> updatePost(@PathVariable Long id, @RequestBody FacebookPost postDetails) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    // Update the mutable fields
                    existingPost.setAuthor(postDetails.getAuthor());
                    existingPost.setContent(postDetails.getContent());
                    existingPost.setImageUrl(postDetails.getImageUrl());

                    // The @PreUpdate method in the Entity will automatically set modifiedDateTime.
                    FacebookPost updatedPost = postRepository.save(existingPost);
                    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 5. DELETE Post (DELETE http://localhost:8080/api/facebook/posts/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content on successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if post doesn't exist
        }
    }
}