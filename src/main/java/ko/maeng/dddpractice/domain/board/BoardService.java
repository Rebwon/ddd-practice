package ko.maeng.dddpractice.domain.board;

import org.springframework.stereotype.Service;

@Service
public class BoardService {

	public Board getById(Long boardId) {
		Board board = new Board();
		board.setId(boardId);
		return board;
	}
}

