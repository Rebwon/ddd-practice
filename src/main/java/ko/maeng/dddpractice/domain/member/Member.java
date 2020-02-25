package ko.maeng.dddpractice.domain.member;

import ko.maeng.dddpractice.domain.article.Posts;
import ko.maeng.dddpractice.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Email email;

    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posts> posts = new ArrayList<>();

    @Builder
    public Member(Long id, String name, Email email, String password, List<Posts> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
        if(this.posts != null && this.posts.size() > 0) {
            for(Posts each : posts) {
                each.setMember(this);
            }
        }
    }
}
