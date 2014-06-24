package com.t7p.alumn.impl.membership;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.t7p.alumn.model.membership.Follower;
import com.t7p.alumn.model.membership.Member;
import com.t7p.alumn.services.membership.MemberServices;


@Service("memberServices")
public class MemberServicesImpl implements MemberServices {
	
	private static final Logger logger = 
			Logger.getLogger(MemberServicesImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	Update update;
	
	public MemberServicesImpl(){
		update = new Update();
	}
	
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

	public void updateMember(String key, Member member) throws Exception {
		logger.debug("MemberServicesImpl.updateMember()");
		try {
			Query q = new Query(Criteria.where("_id").is(key));
			mongoTemplate.updateFirst(q, update, Member.class);
		} catch (Exception e) {
			logger.error("error happens : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void deleteMember(String key) throws Exception {
		logger.debug("MemberServicesImpl.deleteMember()");
		try {
			Query q = new Query(Criteria.where("_id").is(key));
			mongoTemplate.remove(q, Member.class);
		} catch (Exception e) {
			logger.error("error happens : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void addFollower(String key, Follower f) throws Exception {
		logger.debug("MemberServicesImpl.addFollower()");
		try {
			update.push("followers", f);
			Query q = new Query(Criteria.where("_id").is(key));
			mongoTemplate.updateFirst(q, update, Member.class);
		} catch (Exception e) {
			logger.error("error happens : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void removeFollower(String key, Follower f) throws Exception {
		logger.debug("MemberServicesImpl.removeFollower()");
		try {
			update.pull("followers", f);
			Query q = new Query(Criteria.where("_id").is(key));
			mongoTemplate.updateFirst(q, update, Member.class);
		} catch (Exception e) {
			logger.error("error happens : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	public List<Member> selectAllMembersByFollower(Follower f) throws Exception {
		logger.debug("MemberServicesImpl.selectAllMembersByFollower()");
//		Query query = new Query(Criteria.where("followers.profileUrl").is(f.getProfileUrl()));
		Query query = new Query(Criteria.where("followers").is(f));
		query.with(new Sort(Sort.Direction.ASC, "_id"));
		
		return mongoTemplate.find(query, Member.class);
	}
	
	public List<Member> selectAllMembersByTags(String tag) throws Exception {
		logger.debug("MemberServicesImpl.selectAllMembersByTags()");
		
		Query query = new Query(Criteria.where("tags").is(tag));
		query.with(new Sort(Sort.Direction.ASC, "_id"));
		return mongoTemplate.find(query, Member.class);
	}

}
