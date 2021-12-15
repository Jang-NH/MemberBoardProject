package com.jnh.board.service;

import java.io.IOException;
import java.util.List;

import com.jnh.board.dto.MemberDTO;

public interface MemberService {

	int save(MemberDTO member) throws IllegalStateException, IOException;

	String idDuplicate(String m_id);

	String login(MemberDTO member);

	void adminDelete(String m_id);

	MemberDTO findById(String m_id);

	void update(MemberDTO member);

	List<MemberDTO> findAll();



}
