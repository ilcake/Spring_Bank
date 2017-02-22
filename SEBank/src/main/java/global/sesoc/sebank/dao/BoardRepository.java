package global.sesoc.sebank.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.sebank.vo.Board;

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
			int result = mapper.addHits(boardnum);
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
}
