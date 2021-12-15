package com.jnh.board.service;

import java.util.List;

import com.jnh.board.dto.CommentDTO;

public interface CommentService {

	List<CommentDTO> findAll(long b_number);

	void save(CommentDTO comment);

}
