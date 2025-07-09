package telran.java58.forum.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java58.forum.dto.request.CreatePostRequestDto;
import telran.java58.forum.dto.response.PostResponseDto;
import telran.java58.forum.entity.Comment;
import telran.java58.forum.entity.Post;

import telran.java58.forum.exeptions.PostNotFoundException;
import telran.java58.forum.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostResponseDto createPost(String author, CreatePostRequestDto requestDto) {
        Post post = modelMapper.map(requestDto, Post.class);
        post.setAuthor(author);
        post.setDateCreated(LocalDateTime.now());
        post.setLikes(0);
        post.setComments(new ArrayList<>());
        post = postRepository.save(post);
        return modelMapper.map(post, PostResponseDto.class);

    }

    public PostResponseDto findPostById(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post, PostResponseDto.class);
    }

    public void addLike(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    public PostResponseDto createPost(String postId, String commenter, String message) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        Comment comment = new Comment();
        comment.setUser(commenter);
        comment.setMessage(message);
        comment.setDateCreated(LocalDateTime.now());
        comment.setLikes(0);
        post.getComments().add(comment);
        postRepository.save(post);
        return modelMapper.map(post, PostResponseDto.class);

    }

    public List<PostResponseDto> findPostsByAuthor(String author) {
        return postRepository.findByAuthor(author).stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .toList();
    }

    public List<PostResponseDto> findPostsByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return postRepository.findByDateCreatedBetween(dateFrom, dateTo).stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .toList();
    }

    public List<PostResponseDto> findPostsByTags(List<String>tags){
        return postRepository.findByTagsIn(tags).stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .toList();
    }

    public PostResponseDto updatePost(String postId, CreatePostRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        post.setTags(requestDto.getTags());
        post = postRepository.save(post);
        return modelMapper.map(post, PostResponseDto.class);
    }

    public PostResponseDto deletePost(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(postId);
        return modelMapper.map(post, PostResponseDto.class);
    }

}
