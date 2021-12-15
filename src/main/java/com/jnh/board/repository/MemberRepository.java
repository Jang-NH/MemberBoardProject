package com.jnh.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jnh.board.dto.MemberDTO;

@Repository
public class MemberRepository {
	
	@Autowired
	private SqlSessionTemplate sql;

	public int save(MemberDTO member) {
		return sql.insert("Member.save", member);
	}

	public String idDuplicate(String m_id) {
		return sql.selectOne("Member.idDuplicate", m_id);
	}

	public MemberDTO login(MemberDTO member) {
		return sql.selectOne("Member.login", member);
	}

	public void adminDelete(String m_id) {
		sql.delete("Member.adminDelete", m_id);
		
	}

	public MemberDTO findById(String m_id) {
		return sql.selectOne("Member.findById", m_id);
	}

	public void update(MemberDTO member) {
		sql.update("Member.update", member);
	}

	public List<MemberDTO> findAll() {
		return sql.selectList("Member.findAll");
	}



}
