package ko.maeng.dddpractice.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void insert(){
        // given
        memberRepository.save(Member.builder()
                .name("김철수")
                .email("chulsu@naver.com")
                .password("1234")
                .build());

        // when
        List<Member> all = memberRepository.findAll();
        Member member = all.get(0);

        // then
        assertThat(member.getName()).isEqualTo("김철수");
        assertThat(member.getEmail()).isEqualTo("chulsu@naver.com");
    }
}