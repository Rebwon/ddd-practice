package ko.maeng.dddpractice.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ko.maeng.dddpractice.domain.common.BaseTimeEntity;
import ko.maeng.dddpractice.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    private String title;
    private String description;

    @Builder
    public Posts(Long id, Member member, Category category, String title, String description) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.title = title;
        this.description = description;
    }

    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getPosts().remove(this);
        }
        this.member = member;
        //member.getPosts().add(this);
    }
}
