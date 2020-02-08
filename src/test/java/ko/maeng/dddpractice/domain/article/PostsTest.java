package ko.maeng.dddpractice.domain.article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostsTest {
    @Autowired
    PostsRepository postsRepository;

    @Test
    void insert(){
        // given
        postsRepository.save(Posts.builder()
                .category(new Category("Domain Driven Design"))
                .title("도메인 주도 설계를 공부하자.")
                .description("도메인은~~~~~")
                .build());

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        Posts posts = all.get(0);
        assertThat(posts.getCategory().getName()).isEqualTo("Domain Driven Design");
        assertThat(posts.getTitle()).isEqualTo("도메인 주도 설계를 공부하자.");
    }
}