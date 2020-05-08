package ko.maeng.dddpractice.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ko.maeng.dddpractice.domain.board.Board;
import ko.maeng.dddpractice.domain.common.BaseTimeEntity;
import ko.maeng.dddpractice.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tags> tags = new ArrayList<>();

    @Builder
    public Posts(Long id, Member member, Category category, String title, String description, List<Tags> tags) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.title = title;
        this.description = description;
        this.tags = setTags(tags);
    }

    public List<Tags> setTags(List<Tags> tags) {
        this.tags = tags;
        if(this.tags != null && this.tags.size() > 0) {
            for(Tags tag : tags) {
                tag.setPosts(this);
            }
        }
        return tags;
    }

    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getPosts().remove(this);
        }
        this.member = member;
    }

    public void addBoard(Board board) {

    }
}
