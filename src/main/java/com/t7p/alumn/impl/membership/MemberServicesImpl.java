package com.t7p.alumn.impl.membership;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.t7p.alumn.model.membership.Member;
import com.t7p.alumn.services.membership.MemberServices;


@Service("memberServices")
public class MemberServicesImpl implements MemberServices {
	
	private static final Logger logger = 
			Logger.getLogger(MemberServicesImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void insertMember(Member member) throws Exception {
		logger.debug("MemberServicesImpl.insertMember()");
		try {
			mongoTemplate.insert(member);
		} catch (Exception e) {
			logger.error("error happens : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Member selectMemberByUsername(String username) throws Exception {
			logger.debug("selectMemberByUsername()");
			Query query = new Query();
			query.addCriteria(Criteria.where("username").is(username));
		return (mongoTemplate.findOne(query, Member.class));
	}

	public List<Member> selectAllMembers() throws Exception {
			logger.debug("selectAllMembers()");
//			Query query = new Query();
//			query.addCriteria(null);
//			mongoTemplate.find(query, Member.class);
		return mongoTemplate.findAll(Member.class);
	}

}
