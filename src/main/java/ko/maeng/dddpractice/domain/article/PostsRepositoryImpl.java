package ko.maeng.dddpractice.domain.article;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ko.maeng.dddpractice.web.dto.PostsCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ko.maeng.dddpractice.domain.article.QCategory.category;
import static ko.maeng.dddpractice.domain.article.QPosts.posts;

@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostsCategory> findAllPostsCategory() {
        return jpaQueryFactory
                .select(Projections.fields(PostsCategory.class,
                        posts.title.as("postsTitle"),
                        category.name.as("categoryName")))
                .from(posts)
                .join(posts).on(category.id.eq(posts.id))
                .fetch();
    }
}
