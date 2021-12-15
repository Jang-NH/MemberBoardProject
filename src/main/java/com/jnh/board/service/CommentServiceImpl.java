package com.jnh.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jnh.board.dto.CommentDTO;
import com.jnh.board.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository cr;

	@Override
	public List<CommentDTO> findAll(long b_number) {
		return cr.findAll(b_number);
	}

	@Override
	public void save(CommentDTO comment) {
		cr.save(comment);
		
	}

}
