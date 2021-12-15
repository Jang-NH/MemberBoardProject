package com.jnh.board.service;

import java.io.IOException;
import java.util.List;

import com.jnh.board.dto.BoardDTO;
import com.jnh.board.dto.PageDTO;

public interface BoardService {

	void save(BoardDTO board) throws IllegalStateException, IOException;

	List<BoardDTO> findAll();

	List<BoardDTO> pagingList(int page);

	PageDTO paging(int page);

	BoardDTO findById(long b_number);

	void update(BoardDTO board);

	void delete(long b_number);

	List<BoardDTO> search(String searchtype, String keyword);

	BoardDTO findByWriter(String b_writer);


}
