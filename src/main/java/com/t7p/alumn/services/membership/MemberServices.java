package com.t7p.alumn.services.membership;

import java.util.List;

import com.t7p.alumn.model.membership.Member;

public interface MemberServices {
	
	public void insertMember(Member member) throws Exception;
	public Member selectMemberByUsername(String username) throws Exception;
	public List<Member> selectAllMembers() throws Exception;
	
}
