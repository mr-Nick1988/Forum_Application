package telran.java58.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String id;
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private Integer likes = 0;
}
