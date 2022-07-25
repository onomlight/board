package com.korea.dto;

public class MemberDTO {
	private String email;
	private String pwd;
	private String addr1;
	private String addr2;
	private String zipcode;
	private int grade; // 일반 1/ 관리자 2/ 익명0
	
	
	//Getter and setter
	//ToString
	
	
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public MemberDTO() {
		grade=1;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", pwd=" + pwd + ", addr1=" + addr1 + ", addr2=" + addr2 + ", grade="
				+ grade + "]";
	}
	



	

}
