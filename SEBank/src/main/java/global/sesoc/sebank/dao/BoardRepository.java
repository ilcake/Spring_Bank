package global.sesoc.sebank.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.sebank.vo.Board;
import global.sesoc.sebank.vo.Reply;

@Repository
public class BoardRepository {
	@Autowired
	SqlSession sqlSession;

	// 전체 글 목록 얻어오기 ※

	/*
	 * 전체 글 요청
	 * 
	 * @param searchTitle 요청할때 검색 옵션 (글제목, 글 작성자, 글 내용)
	 * 
	 * @param searchText 요청할때 검색하는 검색 값
	 * 
	 * @return 전체 글
	 */
	public List<Board> listBoard(String searchTitle, String searchText, int start, int end) {
		List<Board> bList = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		Map<String, String> search = new HashMap<>();
		search.put("searchTitle", searchTitle);
		search.put("searchText", searchText);
		search.put("start", start + "");
		search.put("end", end + "");
		try {
			bList = mapper.listBoard(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bList;
	}

	public Board getBoard(int boardnum) {
		Board b = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			b = mapper.getBoard(boardnum);
			mapper.addHits(boardnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/*
	 * 전체 글 개수 요청
	 * 
	 * @param searchTitle 글 개수를 요청할때 검색하는 옵션(글제목, 글쓴이, 글내용)
	 * 
	 * @param searchText 글 개수를 요청할때 검색하는 검색 값
	 * 
	 * @return 전체 글 개수
	 */
	public int getTotal(String searchTitle, String searchText) {
		int total = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		Map<String, String> search = new HashMap<>();
		search.put("searchTitle", searchTitle);
		search.put("searchText", searchText);
		try {
			total = mapper.getTotal(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	/*
	 * DZBMS에 접근하여 전달받은 데이터를 저장
	 * 
	 * @param board 저장할 데이터가 담긴 객체
	 * 
	 * @return 저장에 대한 성공여부 1:성공 0:실패
	 */
	public int insertBoard(Board board) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			result = mapper.insertBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBoard(int boardnum) {
		Board b = new Board();
		b.setBoardnum(boardnum);
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.deleteBoard(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int replyWrite(Reply reply) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.replyWrite(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Reply> listReply(int boardnum) {
		List<Reply> listReply = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			listReply = mapper.listReply(boardnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listReply;
	}

	public int replyDelete(int replynum) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			result = mapper.deleteReply(replynum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
