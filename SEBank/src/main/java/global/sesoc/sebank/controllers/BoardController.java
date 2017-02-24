package global.sesoc.sebank.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.sebank.dao.BoardRepository;
import global.sesoc.sebank.util.FileService;
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
	final String uploadPath = "/boardfile"; // 파일이 업로드 되는 경로.

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
	public String Write(MultipartFile upload, Board board, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		board.setCustid(loginId);

		System.out.println(upload);
		String savedFile = "";
		// 첨부된 파일을 처리.
		if (!upload.isEmpty()) {
			FileService fs = new FileService();
			logger.info(upload.toString());
			savedFile = fs.saveFile(upload, uploadPath);
			System.out.println("savedFile==" + savedFile);
			System.out.println("OriginalFile==" + upload.getOriginalFilename());
			board.setOriginalfile(upload.getOriginalFilename());
			board.setSavedfile(savedFile);
		}

		logger.info("write2 ==>  " + board.toString());
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

	// 파일다운로드
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(int boardnum, HttpServletResponse response) {
		// 수동으로 다운을 시켜줘야하기때문에 리스폰스를 받아와야한다.

		// boardnum에 해당하는 한개의 글 가져오기.
		Board b = repo.getBoard(boardnum);
		String originalFile = b.getOriginalfile();

		// 사용자 측에서 다운로드 받도록 하기 위해서 response객체의 헤더를 조작한다.
		try {
			// Content-Disposition의 헤더를 조작해준다.
			// 파일명이 한글일 경우를 대비하여 UTF-8로 인코딩하여 준다.
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(originalFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fullpath = uploadPath + "/" + b.getSavedfile();

		FileInputStream filein = null; // 서버쪽 하드에 있는 자료를 메모리에 올리기위한 inputStream
										// (file로 주고받는다)
		ServletOutputStream fileout = null; // 서버쪽 메모리에서 리스폰스에 올리기 위한
											// outputStream
		// (원격지 밖으로 나가기때문에 ServletOutputStream)

		try {
			filein = new FileInputStream(fullpath);
			fileout = response.getOutputStream();

			// Spring 에서 제공하는 유틸리티
			FileCopyUtils.copy(filein, fileout);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (filein != null)
					filein.close();
				if (fileout != null)
					fileout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public String deleteFile(String savedfile) {
		String fullpath = "C:"+""+"boardfile"+""+ savedfile;
		FileService.deleteFile(fullpath);
		return null;
	}
}
