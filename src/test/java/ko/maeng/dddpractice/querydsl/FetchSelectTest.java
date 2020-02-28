package ko.maeng.dddpractice.querydsl;

import ko.maeng.dddpractice.domain.article.Category;
import ko.maeng.dddpractice.domain.article.Posts;
import ko.maeng.dddpractice.domain.article.PostsRepository;
import ko.maeng.dddpractice.domain.article.Tags;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class FetchSelectTest {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private EntityManager em;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    void FETCH_SELECT() {
        // given
        List<Tags> tags = asList(
                Tags.builder().name("TDD").build(),
                Tags.builder().name("JAVA").build()
        );

        List<Posts> posts = asList(
                Posts.builder().title("TDD").description("TDD의 중요성")
                        .tags(tags)
                        .category(new Category("TDD")).build(),
                Posts.builder().title("JAVA").description("JAVA의 중요성")
                        .category(new Category("JAVA")).build(),
                Posts.builder().title("AWS").description("AWS의 중요성")
                        .category(new Category("AWS")).build());

        postsRepository.saveAll(posts);
        em.clear();

        // when
        List<Posts> result = postsRepository.findAllWithFetch();

        // then
        assertThat(result.size()).isEqualTo(3);
    }
}
