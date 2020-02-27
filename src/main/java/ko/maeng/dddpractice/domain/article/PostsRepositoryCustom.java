package ko.maeng.dddpractice.domain.article;

import ko.maeng.dddpractice.web.dto.PostsCategory;

import java.util.List;

public interface PostsRepositoryCustom {
    List<PostsCategory> findAllPostsCategory();
}
