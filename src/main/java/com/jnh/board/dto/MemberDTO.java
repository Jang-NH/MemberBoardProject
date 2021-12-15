package com.jnh.board.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_email;
	private String m_phone;
	private MultipartFile m_profile;
	private String m_profilename;
}
