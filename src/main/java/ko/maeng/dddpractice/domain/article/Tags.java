package ko.maeng.dddpractice.domain.article;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn/*(name = "posts_id")*/
    private Posts posts;

    @Builder
    public Tags(Long id, String name, Posts posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    public void setPosts(Posts posts) {
        if(this.posts != null) {
            this.posts.getTags().remove(this);
        }
        this.posts = posts;
    }
}
