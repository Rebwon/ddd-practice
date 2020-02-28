package ko.maeng.dddpractice.domain.article;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>, PostsRepositoryCustom {

    @EntityGraph(attributePaths = {"tags"})
    @Query("SELECT DISTINCT p FROM Posts p")
    List<Posts> findAllWithFetch();
}
