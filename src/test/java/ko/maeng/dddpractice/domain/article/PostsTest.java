package ko.maeng.dddpractice.domain.article;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostsTest {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    EntityManager em;

    @Disabled
    void setup() {
        // given
        Posts post = Posts.builder()
                .category(new Category("Domain Driven Design"))
                .title("도메인 주도 설계를 공부하자.")
                .description("도메인은~~~~~")
                .build();

        Tags tdd = Tags.builder()
                .name("DDD")
                .posts(post)
                .build();

        Tags java = Tags.builder()
                .name("JAVA")
                .posts(post)
                .build();

        postsRepository.save(post);

        em.clear();
    }

    @Disabled
    @Test
    void posts만_저장해도_태그가_저장되는지_확인() {
        Posts posts = postsRepository.findById(1l).get();
        for(Tags tag : posts.getTags()) {
            System.out.println(tag.getName());
        }
    }

    @Test
    void posts컬렉션의_태그를_지웠을_때(){
        Posts post = Posts.builder()
                .category(new Category("Domain Driven Design"))
                .title("도메인 주도 설계를 공부하자.")
                .description("도메인은~~~~~")
                .build();

        Tags tdd = Tags.builder()
                .name("DDD")
                .posts(post)
                .build();

        Tags java = Tags.builder()
                .name("JAVA")
                .posts(post)
                .build();

        postsRepository.save(post);

        em.clear();

        Posts dbPosts = postsRepository.findById(1l).get();
        dbPosts.getTags().clear();
        postsRepository.save(dbPosts);

        em.clear();
        Posts find = postsRepository.findById(1l).get();
        assertThat(find.getTags().size()).isEqualTo(0); //error
    }
}