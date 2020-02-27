package ko.maeng.dddpractice.querydsl;

import ko.maeng.dddpractice.domain.member.Email;
import ko.maeng.dddpractice.domain.member.Member;
import ko.maeng.dddpractice.domain.member.MemberRepository;
import ko.maeng.dddpractice.domain.member.MemberRepositorySupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRepositorySupport memberRepositorySupport;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @Test
    void querydsl_findByNameTest() {
        // given
        Member member = Member.builder()
                .name("rebwon")
                .email(new Email("rebwon@gmail.com"))
                .password("1234")
                .build();

        memberRepository.save(member);

        // when
        List<Member> result = memberRepositorySupport.findByName("rebwon");

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getPassword()).isEqualTo("1234");
    }
}
