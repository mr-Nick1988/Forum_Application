package telran.java58.forum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.forum.entity.Post;

import java.time.LocalDateTime;
import java.util.List;


public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthor(String author);

    List<Post> findByTagsIn(String[] tags);

    List<Post> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
