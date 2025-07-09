package telran.java58.forum.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreatePostRequestDto {
    private String title;
    private String content;
    private List<String> tags;
}
