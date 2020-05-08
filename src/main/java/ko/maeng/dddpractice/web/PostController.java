package ko.maeng.dddpractice.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ko.maeng.dddpractice.domain.article.Posts;
import ko.maeng.dddpractice.domain.board.BoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {
	private final BoardService boardService;

	@GetMapping("/{boardId}/posts")
	public ResponseEntity add(@PathVariable Long boardId) {
		Posts posts = new Posts();
		posts.addBoard(boardService.getById(boardId));
		return ResponseEntity.ok(posts);
	}
}
