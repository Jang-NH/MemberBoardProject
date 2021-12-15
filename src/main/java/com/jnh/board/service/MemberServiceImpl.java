package com.jnh.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jnh.board.dto.MemberDTO;
import com.jnh.board.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository mr;
	
	@Autowired
	private HttpSession session;

	@Override
	public int save(MemberDTO member) throws IllegalStateException, IOException {
		
		MultipartFile m_profile = member.getM_profile();
		String m_profilename = m_profile.getOriginalFilename();
		m_profilename = System.currentTimeMillis() + "-" + m_profilename;
		System.out.println("m_profilename: " + m_profilename);
		String savePath = "C:\\development_jnh\\source\\spring\\MemberBoardProject\\src\\main\\webapp\\resources"+m_profilename;

		if(!m_profile.isEmpty()) {
			m_profile.transferTo(new File(savePath));
		}

		member.setM_profilename(m_profilename);

		return mr.save(member);
	}

	@Override
	public String idDuplicate(String m_id) {
		String result = mr.idDuplicate(m_id);
		if(result == null)
			return "ok";
		else
			return "no";
	}

	@Override
	public String login(MemberDTO member) {
		MemberDTO loginMember = mr.login(member);
		if(loginMember != null) {
			session.setAttribute("loginID", member.getM_id());
			return "board/boardFindAll";
		} else {
			return "member/login";
		}
	}

	@Override
	public void adminDelete(String m_id) {
		mr.adminDelete(m_id);
	}

	@Override
	public MemberDTO findById(String m_id) {
		MemberDTO member = mr.findById(m_id);
		return member;
	}

	@Override
	public void update(MemberDTO member) {
		mr.update(member);
	}

	@Override
	public List<MemberDTO> findAll() {
		List<MemberDTO> memberList = mr.findAll();
		return memberList;
	}





}
