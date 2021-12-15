package com.jnh.board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jnh.board.repository.BoardRepository;
import com.jnh.board.dto.BoardDTO;
import com.jnh.board.dto.PageDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository br;
	
	@Override
	public void save(BoardDTO board) throws IllegalStateException, IOException {
		MultipartFile b_file = board.getB_file();
		// 파일 이름을 가져옴(파일이름을 DB에 저장하기 위해)
		String b_filename = b_file.getOriginalFilename();
		// 파일명 중복을 피하기 위해 파일이름앞에 현재 시간값을 붙임. 
		b_filename = System.currentTimeMillis() + "-" + b_filename;
		System.out.println("b_filename: " + b_filename);
		// 파일 저장 경로 세팅
		String savePath = "C:\\development_jnh\\source\\spring\\MemberBoardProject\\src\\main\\webapp\\resources"+b_filename;
		// bfile이 비어있지 않다면(즉 파일이 있으면) savePath에 저장을 하겠다.
		if(!b_file.isEmpty()) {
			b_file.transferTo(new File(savePath));
		}
		// 여기까지의 내용은 파일을 저장하는 과정 
		
		// DB에 저장하기 위해 dto에 파일이름을 담는다.
		board.setB_filename(b_filename);
		br.save(board);
	}

	@Override
	public List<BoardDTO> findAll() {
		return br.findAll();
	}
	
	private static final int PAGE_LIMIT = 5; // 한페이지에 보여질 글 개수 
	private static final int BLOCK_LIMIT = 3; // 한화면에 보여질 페이지 개수

	@Override
	public List<BoardDTO> pagingList(int page) {
		int pagingStart = (page-1) * PAGE_LIMIT;
		Map<String, Integer> pagingParam = new HashMap<String, Integer>();
		pagingParam.put("start", pagingStart);
		pagingParam.put("limit", PAGE_LIMIT);
		List<BoardDTO> pagingList = br.pagingList1(pagingParam);
		for(BoardDTO b: pagingList) {
				System.out.println(b.toString());
		}
		return pagingList;
	}

	@Override
	public PageDTO paging(int page) {
		int boardCount = br.boardCount(); // 전체 글 갯수 조회
		int maxPage = (int)(Math.ceil((double)boardCount / PAGE_LIMIT)); // 전체 페이지 계산
		// 시작페이지 : 2페이지를 요청했으면 1페이지(1,2,3), 4페이지를 요청했으면 4페이지(4,5,6), 8페이지를 요청했으면 7페이지(7,8,9)의 값을 갖도록 계산
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) -1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT -1;
		if(endPage > maxPage)
			endPage = maxPage;
		PageDTO paging = new PageDTO();
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		return paging;
	}

	@Override
	public BoardDTO findById(long b_number) {
		br.hits(b_number);
		BoardDTO board = br.findById(b_number);
		return board;
	}

	@Override
	public void update(BoardDTO board) {
		br.update(board);
		
	}

	@Override
	public void delete(long b_number) {
		br.delete(b_number);
		
	}

	@Override
	public List<BoardDTO> search(String searchtype, String keyword) {
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("type", searchtype);
		searchParam.put("keyword", keyword);
		List<BoardDTO> boardList = br.search(searchParam);
		return boardList;
	}

	@Override
	public BoardDTO findByWriter(String b_writer) {
		BoardDTO board = br.findByWriter(b_writer);
		return board;
	}

}
