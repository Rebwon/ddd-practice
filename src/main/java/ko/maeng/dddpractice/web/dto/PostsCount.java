package ko.maeng.dddpractice.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsCount {
    private String memberName;
    private long postsCount;
}
