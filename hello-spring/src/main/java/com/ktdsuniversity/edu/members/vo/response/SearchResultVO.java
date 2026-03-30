package com.ktdsuniversity.edu.members.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.members.vo.MembersVO;

public class SearchResultVO {

	private int count;
	private List<MembersVO> membersVO;

	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<MembersVO> getMembersVO() {
		return this.membersVO;
	}
	public void setMembersVO(List<MembersVO> membersVO) {
		this.membersVO = membersVO;
	}
	
}
