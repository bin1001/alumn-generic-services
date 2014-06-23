package com.t7p.alumn.junit.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
//	@Test
	public final void testSaveMember() {
		//need to put validation
		try {
			logger.debug("MemberServiceJunit.testSaveMember()");
			Member mbr1 = new Member();
			mbr1.setUsername("rbn02");
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
	public final void testGetMemberByUsername() {
		try {
			logger.debug("MemberServiceJunit.testGetMemberByUsername()");
			Member m = memberServices.selectMemberByUsername("rbn01");
			logger.debug(m==null?"NULLS":m.getNickName());
			logger.debug("After Running Test...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
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
	
	
	
}
