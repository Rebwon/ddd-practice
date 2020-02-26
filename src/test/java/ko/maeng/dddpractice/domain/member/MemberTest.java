package ko.maeng.dddpractice.domain.member;

import ko.maeng.dddpractice.domain.article.Category;
import ko.maeng.dddpractice.domain.article.Posts;
import ko.maeng.dddpractice.domain.article.PostsRepository;
import ko.maeng.dddpractice.domain.article.Tags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void setup() {
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

        Member member = Member.builder()
                .name("김철수")
                .email(new Email("chulsu@naver.com"))
                .password("1234")
                .posts(posts)
                .build();

        memberRepository.save(member);

        em.clear();
    }

    @Disabled
    @Test
    void insert() throws Exception {
        // given
        memberRepository.save(Member.builder()
                .name("김철수")
                .email(new Email("chulsu@naver.com"))
                .password("1234")
                .build());

        // when
        List<Member> all = memberRepository.findAll();
        Member member = all.get(0);

        // then
        assertThat(member.getName()).isEqualTo("김철수");
        assertThat(member.getEmail().getValue()).isEqualTo("chulsu@naver.com");
    }

    @Test
    void memberInsertArticle() throws Exception {
        // when
        List<Member> all = memberRepository.findAll();
        Member dbMember = all.get(0);

        dbMember.getPosts().clear();

        memberRepository.delete(dbMember);
        List<Posts> postsList = postsRepository.findAll();

        // then
        assertThat(dbMember.getPosts()).isEmpty();
        assertThat(postsList.size()).isEqualTo(0);
    }
}