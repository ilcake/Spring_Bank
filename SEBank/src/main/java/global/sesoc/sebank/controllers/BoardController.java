package global.sesoc.sebank.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import global.sesoc.sebank.dao.BoardRepository;
import global.sesoc.sebank.util.PageNavigator;
import global.sesoc.sebank.vo.Board;
import global.sesoc.sebank.vo.Reply;

@Controller
public class BoardController {
	/*
	 * 
	*/
	@Autowired
	BoardRepository repo;

	final int countPerPage = 10;
	final int pagePerGroup = 5;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//
	@RequestMapping(value = "/listBoard", method = RequestMethod.GET)
	public String listBoard(@RequestParam(value = "searchTitle", defaultValue = "") String searchTitle,
			@RequestParam(value = "searchText", defaultValue = "") String searchText,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

		// 전체 글 갯수를 얻어오는 작업
		int total = repo.getTotal(searchTitle, searchText);
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		int start = navi.getStartRecord() + 1;
		int end = start + countPerPage - 1;

		logger.info("Total : " + total + "Start/End : " + start + "/" + end + "  BOARD ----" + searchTitle + "="
				+ searchText);

		List<Board> boardList = repo.listBoard(searchTitle, searchText, start, end);
		model.addAttribute("listBoard", boardList); // loop돌려서 출력
		model.addAttribute("total", total); // 글 갯수 출력
		model.addAttribute("searchTitle", searchTitle); // 마지막으로 검색한 데이터 알아보기
		model.addAttribute("searchText", searchText); // 마지막으로 검색한 데이터 알아보기
		model.addAttribute("navi", navi); // 페이징을 위해서 가져간다.

		logger.info("BOARD ----" + boardList.toString());
		return "board/list";
	}

	/*
	 * 글 한개에 대한 요청처리
	 * 
	 * @param boardnum-게시물의 시퀀스 번호 호출
	 * 
	 * @return 처리 결과에 대한 출력을 위한 뷰 요청
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(int boardnum, Model model) {
		logger.info("요청한 글 번호 :" + boardnum);
		// mapper 연동.
		Board b = repo.getBoard(boardnum);

		// 리플 가져오기.
		List<Reply> replyList = repo.listReply(boardnum);

		model.addAttribute("board", b);
		model.addAttribute("replyList", replyList);

		return "board/boardDetail";
	}

	/*
	 * 게시글 작성 화면에 대한 요청
	 * 
	 * @return 게시글 입력을 위한 뷰.
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String boardWrite() {
		// mapper 연동.

		return "board/boardWrite";
	}

	/*
	 * 작성된 게시글을 DB에 저장하기 위함
	 * 
	 * @return 리스트.
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String Write(Board board, HttpSession session) {
		// mapper 연동.
		String loginId = (String) session.getAttribute("loginId");
		board.setCustid(loginId);
		logger.info("write ==>  " + board.toString());
		repo.insertBoard(board);

		return "redirect:listBoard";
	}

	/*
	 * 삭제
	 * 
	 * @param boardnum 삭제하려고 하는 게시물의 시퀀스번호
	 * 
	 * @return 리스트.
	 */
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String Write(int boardnum) {
		repo.deleteBoard(boardnum);
		return "redirect:listBoard";
	}

	// 댓글을 DBMS에 저장하는 동작.
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(Reply reply, HttpSession session) {
		String user = (String) session.getAttribute("loginId");
		reply.setCustid(user);
		logger.info(reply.toString());
		repo.replyWrite(reply);
		return "redirect:board?boardnum=" + reply.getBoardnum();
	}

	// 댓글을 DBMS에 저장하는 동작.
	@RequestMapping(value = "/replyDelete", method = RequestMethod.GET)
	public String replyWrite(int replynum, int boardnum) {
		repo.replyDelete(replynum);
		return "redirect:board?boardnum=" + boardnum;
	}

}
