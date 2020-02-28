package ko.maeng.dddpractice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostsDto {
    private String title;
    private String description;
    private List<String> tagsName;
    private String categoryName;

    @Builder
    public PostsDto(String title, String description, List<String> tagsName, String categoryName) {
        this.title = title;
        this.description = description;
        this.tagsName = tagsName;
        this.categoryName = categoryName;
    }
}
