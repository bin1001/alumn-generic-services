package com.t7p.alumn.junit.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.t7p.alumn.model.membership.Follower;
import com.t7p.alumn.model.membership.Member;
import com.t7p.alumn.services.membership.MemberServices;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationConfig.xml" })
public class MemberServiceJunit {
	
	private static final Logger logger = 
			Logger.getLogger(MemberServiceJunit.class);
	
	@Autowired
	MemberServices memberServices;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	public final void testSaveMember() {
		//need to put validation
		try {
			logger.debug("MemberServiceJunit.testSaveMember()");
			Member mbr1 = new Member();
			mbr1.setUsername("sss");
			mbr1.setNickName("Binz Ladenz");
			mbr1.setCompleteName("Bung Binz Ladenz");
			mbr1.setUinID("10203040");
			mbr1.setAddress("Bukit Pamulang Indah");
			mbr1.setBiography("Anak Uin, angkatan 2005");
			memberServices.insertMember(mbr1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void saveMemberWithFollower() {
		//need to put validation
		List<Follower> followers = new ArrayList<Follower>();
		Follower f = new Follower();
		f.setProfileUrl("www.alumn.com/abc");
		f.setUsername("abc");
		followers.add(f);
		
		f = new Follower();
		f.setProfileUrl("www.alumn.com/ddd");
		f.setUsername("ddd");
		followers.add(f);
		
		f = new Follower();
		f.setProfileUrl("www.alumn.com/hhh");
		f.setUsername("hhh");
		followers.add(f);
		
		List<String> tags = new ArrayList<String>();
		tags.add("programer");
		tags.add("penyanyi");
		
		try {
			logger.debug("MemberServiceJunit.testSaveMemberWithFollower()");
			Member mbr1 = new Member();
			mbr1.setUsername("sherina19");
			mbr1.setNickName("sherina");
			mbr1.setCompleteName("sherina munaf");
			mbr1.setUinID("9999999");
			mbr1.setAddress("jakartaa city");
			mbr1.setBiography("Anak Uin, angkatan 2005");
			mbr1.setFollowers(followers);
			mbr1.setTags(tags);
			memberServices.insertMember(mbr1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void updateMember() {
		//need to put validation
		Member m = new Member();
		try {
			m = memberServices.selectMemberByUsername("sherina19");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Follower> followers = new ArrayList<Follower>();
		Follower f = new Follower();
		f.setProfileUrl("www.alumn.com/def");
		f.setUsername("def");
		followers.add(f);
		
		f = new Follower();
		f.setProfileUrl("www.alumn.com/hhh");
		f.setUsername("hhh");
		followers.add(f);
		m.setFollowers(followers);
		
		m.setBiography("Singer");
		
		
		try {
			logger.debug("MemberServiceJunit.updateMemberFollower()");
			memberServices.updateMember("sherina19", m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void deleteMember() {
		//need to put validation
		
		try {
			logger.debug("MemberServiceJunit.deleteMember()");
			memberServices.deleteMember("sherina19");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void addFollowerToMember() {
		//need to put validation
		Follower f = new Follower();
		f.setProfileUrl("www.alumn.com/agnesmonica");
		f.setUsername("agnes monica");
		try {
			logger.debug("MemberServiceJunit.addFollowerToMember()");
			memberServices.addFollower("sherina19", f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void removeFollowerFromMember() {
		//need to put validation
		Follower f = new Follower();
		f.setProfileUrl("www.alumn.com/agnesmonica");
		f.setUsername("agnes monica");
		try {
			logger.debug("MemberServiceJunit.removeFollowerFromMember()");
			memberServices.removeFollower("sherina19", f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void testGetMemberByUsername() {
		try {
			logger.debug("MemberServiceJunit.testGetMemberByUsername()");
			Member m = memberServices.selectMemberByUsername("sherina19");
			System.out.println(m.getFollowers());
			logger.debug(m==null?"NULLS":m.getNickName());
			logger.debug("After Running Test...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void testGetAllMember(){
		logger.debug("MemberServiceJunit.testGetAllMember()");
		try {
			List<Member> liMembers = memberServices.selectAllMembers();
			for (Member member : liMembers) {
				logger.debug("Member : " + member.getUsername()+" - "+member.getNickName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void testGetAllMemberByFollower(){
		logger.debug("MemberServiceJunit.testGetAllMemberByFollower()");
		Follower f = new Follower();
		f.setUsername("ddd");
		f.setProfileUrl("www.alumn.com/ddd");
		try {
			List<Member> liMembers = memberServices.selectAllMembersByFollower(f);
			System.out.println("liMembers.size() >>> " + liMembers.size());
			for (Member member : liMembers) {
				logger.debug("Member : " + member.getUsername()+" - "+member.getNickName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public final void testGetAllMemberByTags(){
		logger.debug("MemberServiceJunit.testGetAllMemberByTags()");
		try {
			List<Member> liMembers = memberServices.selectAllMembersByTags("penyanyi");
			System.out.println("liMembers.size() >>> " + liMembers.size());
			for (Member member : liMembers) {
				logger.debug("Member : " + member.getUsername()+" - "+member.getNickName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
