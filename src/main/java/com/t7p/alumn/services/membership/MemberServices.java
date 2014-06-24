package com.t7p.alumn.services.membership;

import java.util.List;

import com.t7p.alumn.model.membership.Follower;
import com.t7p.alumn.model.membership.Member;

public interface MemberServices {
	
	public void insertMember(Member member) throws Exception;

	public Member selectMemberByUsername(String username) throws Exception;

	public List<Member> selectAllMembers() throws Exception;

	public void updateMember(String key, Member member) throws Exception;

	public void deleteMember(String key) throws Exception;

	public void addFollower(String key, Follower f) throws Exception;

	public void removeFollower(String key, Follower f) throws Exception;
	
	public List<Member> selectAllMembersByFollower(Follower f) throws Exception;
	
	public List<Member> selectAllMembersByTags(String tag) throws Exception;
}
