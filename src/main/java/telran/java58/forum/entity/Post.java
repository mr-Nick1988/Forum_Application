package telran.java58.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    private String id;
    private String content;
    private String title;
    private String author;
    private LocalDateTime dateCreated;
    private List<String> tags;
    private Integer likes = 0;

    private List<Comment> comments = new ArrayList<>();
}
