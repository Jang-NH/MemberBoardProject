package com.jnh.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jnh.board.dto.PageDTO;
import com.jnh.board.dto.CommentDTO;
import com.jnh.board.service.CommentService;
import com.jnh.board.dto.BoardDTO;
import com.jnh.board.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Autowired
	private BoardService bs;
	
	@Autowired
	private CommentService cs;	
	
	// 글쓰기 페이지 출력
	@RequestMapping(value="boardForm", method=RequestMethod.GET)
	public String saveForm(@RequestParam("b_writer") String b_writer, Model model) {
		BoardDTO board = bs.findByWriter(b_writer);
		model.addAttribute("board", board);
		return "board/boardForm";
	}
	
	// 글쓰기 처리
	@RequestMapping(value="boardForm", method=RequestMethod.POST)
	public String save(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		bs.save(board);
		return "redirect:/board/paging";
	}
	
	// 글쓰기목록 출력
	@RequestMapping(value="boardFindAll", method=RequestMethod.GET)
	public String findAll(Model model) {
		List<BoardDTO> boardList = bs.findAll();
		model.addAttribute("boardList", boardList);
		return "board/boardFindAll";
	}
	
	// 페이징
	@RequestMapping(value="paging", method=RequestMethod.GET)
	// value : 파라미터 이름, required : 필수여부, defaultValue : 기본값(page 요청값이 없으면 1로 세팅)
	public String paging(@RequestParam(value="page", required=false, defaultValue="1")int page, Model model) {
		List<BoardDTO> boardList = bs.pagingList(page);
		PageDTO paging = bs.paging(page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		return "board/boardFindAll";
	}
	
	// 검색
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@RequestParam("searchtype") String searchtype, @RequestParam("keyword") String keyword, Model model) {
		List<BoardDTO> boardList = bs.search(searchtype, keyword);
		model.addAttribute("boardList", boardList);
		return "board/boardFindAll";
	}
	
	// 수정화면 출력
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateFrom(@RequestParam("b_number") long b_number, Model model, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		BoardDTO board = bs.findById(b_number);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "board/boardUpdate";
		}
	
	// 수정처리
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardDTO board, Model model, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		bs.update(board);
		model.addAttribute("page", page);
		return "redirect:/board/detail?b_number=" + board.getB_number() + "&page=" + page;
	}
	
	// 삭제처리
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(@RequestParam("b_number") long b_number, Model model) {
		bs.delete(b_number);
		return "redirect:/board/boardFindAll";
	}
	
	// 상세조회 출력
	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String findById(@RequestParam("b_number") long b_number, Model model, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		BoardDTO board = bs.findById(b_number);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		List<CommentDTO> commentList = cs.findAll(b_number);
		model.addAttribute("commentList", commentList);
		return "board/boardDetail";
	}
	
	
}
