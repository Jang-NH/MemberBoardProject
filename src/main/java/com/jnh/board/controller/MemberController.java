package com.jnh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jnh.board.dto.MemberDTO;
import com.jnh.board.service.MemberService;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {

	@Autowired
	private MemberService ms;
	
	// 회원가입 페이지 출력
	@RequestMapping(value="save", method=RequestMethod.GET)
	public String saveForm() {
		return "member/save";
	}
	
	// 회원가입 처리 (프로필 첨부)
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		int result = ms.save(member);
		if(result > 0)
			return "index";
		else
			return "member/save";
	}
	
	// 아이디 중복 체크
	@RequestMapping(value="idDuplicate", method=RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam("m_id") String m_id) {
		String result = ms.idDuplicate(m_id);
		return result;
	}
	
	// 로그인
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	// 로그인 처리
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member) {
		String loginResultPage = ms.login(member);
		return loginResultPage;
	}
	
	// 로그아웃 처리
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	// 관리자 페이지 출력
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public String admin() {
		return "member/admin";
	}
	
	// 관리자 회원 목록 처리
	@RequestMapping(value="findAll", method=RequestMethod.GET)
	public String findAll(Model model) {
		List<MemberDTO> memberList = ms.findAll();
		model.addAttribute("memberList", memberList);
		
		return "member/findAll";
	}
	
	// 관리자 삭제 처리
	@RequestMapping(value="adminDelete", method=RequestMethod.GET)
	public String adminDelete(@RequestParam("m_id") String m_id) {
		ms.adminDelete(m_id);
		return "redirect:/member/findAll";
	}
	
	// 회원 마이페이지 상세조회
	@RequestMapping(value="myPage", method=RequestMethod.GET)
	public String findById(@RequestParam("m_id") String m_id, Model model) {
		MemberDTO member = ms.findById(m_id);
		model.addAttribute("member", member);
		return "member/myPage";
	}
	
	// 회원 마이페이지 수정화면 요청
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(@RequestParam("m_id") String m_id, Model model) {
		MemberDTO member = ms.findById(m_id);
		model.addAttribute("member", member);
		return "member/update";
	}
	
	// 회원 마이페이지 수정
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute MemberDTO member, Model model) {
		ms.update(member);
		return "redirect:/member/myPage?m_id=" + member.getM_id();
	}
}
