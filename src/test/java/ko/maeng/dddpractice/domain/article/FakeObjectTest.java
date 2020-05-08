package ko.maeng.dddpractice.domain.article;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ko.maeng.dddpractice.domain.board.Board;
import ko.maeng.dddpractice.domain.board.BoardRepository;

@SpringBootTest
public class FakeObjectTest {
	@Autowired
	PostsRepository postsRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	EntityManager em;

	@BeforeEach
	void setUp() {
		Board board = Board.builder()
			.name("Fake Object")
			.build();
		boardRepository.save(board);
	}

	@Test
	void saveFakeObject() {
		Posts posts = Posts.builder()
			.category(new Category("Hibernate"))
			.title("Fake Object란?")
			.description("ID 필드값만 저장된 Board 객체를 new로 직접 생성해서 setBoard하는 것이란다.")
			.tags(Arrays.asList(Tags.builder()
				.name("Hibernate")
				.build()))
			.build();

		Board board = new Board();
		board.setId(1l);
		posts.setBoard(board);

		postsRepository.save(posts);

		em.clear();

		Posts dbPosts = postsRepository.findById(1l).get();

		assertThat(dbPosts.getBoard().getName()).isEqualTo("Fake Object");
	}
}
