package ko.maeng.dddpractice.querydsl;

import ko.maeng.dddpractice.domain.article.Category;
import ko.maeng.dddpractice.domain.article.Posts;
import ko.maeng.dddpractice.domain.article.PostsRepository;
import ko.maeng.dddpractice.domain.article.Tags;
import ko.maeng.dddpractice.domain.member.Email;
import ko.maeng.dddpractice.domain.member.Member;
import ko.maeng.dddpractice.domain.member.MemberRepository;
import ko.maeng.dddpractice.web.dto.PostsCount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DynamicQueryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
        postsRepository.deleteAll();
    }

    @Test
    void DYNAMIC_QUERY_ADVANCE() {
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

        // when
        List<Member> result = memberRepository.findDynamicQueryAdvance("김철수", null, "");

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("김철수");
    }
}
