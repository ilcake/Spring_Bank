package global.sesoc.sebank.dao;

import java.util.List;
import java.util.Map;

import global.sesoc.sebank.vo.Board;

public interface BoardMapper {

	// 1) 게시글 저장
	public int insertBoard(Board board) throws Exception;

	// 2) 글 1개 자세히 보기 (boardnum을 전달받음)
	public Board getBoard(int boardnum) throws Exception;

	// 3) 글에 대한 조회수 (boardnum)
	public int addHits(int boardnum) throws Exception;

	// 4) 현재 등록된 글의 전체 개수 (검색글에 따라 total갯수가 달라짐)
	// select count(*) from board title like '%날씨%';
	// select count(*) from board title like '%'||#{searchText}||'%';
	public int getTotal(Map<String, String> search) throws Exception;

	// 5) 전체 글 조회
	// select * from board (검색글에 따라 결과가 달라진다)
	// select * from board where title like '%'||#{searchText}||'%';
	public List<Board> listBoard(Map<String, String> search) throws Exception;

	// 6) 글 수정
	public int updateBoard(Board board) throws Exception;

	// 7) 글 삭제
	public int deleteBoard(Board board) throws Exception;

}
