package telran.java58.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.java58.forum.dto.request.CreatePostRequestDto;
import telran.java58.forum.dto.response.CommentResponseDto;
import telran.java58.forum.dto.response.PostResponseDto;
import telran.java58.forum.service.ForumService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final ForumService forumService;

    @PostMapping("/post/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto createPost(@PathVariable String user, @RequestBody CreatePostRequestDto requestDto) {
        return forumService.createPost(user, requestDto);
    }

    @GetMapping("/post/{postId}")
    public PostResponseDto findPostById(@PathVariable String postId) {
        return forumService.findPostById(postId);
    }

    @PatchMapping("/post/{postId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable String postId) {
        forumService.addLike(postId);
    }

    @PatchMapping("/post/{postId}/comment/{commenter}")
    public PostResponseDto addComment(@PathVariable String postId, @PathVariable String commenter, @RequestBody CommentResponseDto commentDto) {
        return forumService.createPost(postId, commenter, commentDto.getMessage());
    }

    @GetMapping("/posts/author/{user}")
    public List<PostResponseDto> findPostsByAuthor(@PathVariable String user) {
        return forumService.findPostsByAuthor(user);
    }

    @GetMapping("/posts/period")
    public List<PostResponseDto> findPostsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        return forumService.findPostsByPeriod(dateFrom.atStartOfDay(), dateTo.atStartOfDay());
    }

    @PatchMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable String postId, @RequestBody CreatePostRequestDto requestDto) {
        return forumService.updatePost(postId, requestDto);
    }

    @DeleteMapping("/post/{postId}")
    public PostResponseDto deletePost(@PathVariable String postId) {
        return forumService.deletePost(postId);
    }

    @GetMapping("/posts/tags")
    public List<PostResponseDto> findPostsByTags(@RequestParam String values){
        String[] tags = values.split(",");
        return forumService.findPostsByTags(tags);
    }
}
