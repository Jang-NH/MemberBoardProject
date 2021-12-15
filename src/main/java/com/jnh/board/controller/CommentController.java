package com.jnh.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jnh.board.dto.CommentDTO;
import com.jnh.board.service.CommentService;

@Controller
@RequestMapping(value="/comment/*")
public class CommentController {
	
	@Autowired
	private CommentService cs;
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO comment) {
	System.out.println("CommentController.save() : " + comment);

	cs.save(comment);
	List<CommentDTO> commentList = cs.findAll(comment.getB_number());
	
	return commentList;
	}

}
