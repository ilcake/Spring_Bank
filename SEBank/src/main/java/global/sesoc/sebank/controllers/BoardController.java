package global.sesoc.sebank.controllers;

import java.util.List;

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
		model.addAttribute("board", b);

		return "board/board";
	}

}
