package com.castle.config;
/*package com.airtel.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cointribe.icici.mongo.model.Counters;

@Repository
public class MongoDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	
	public Integer getNextSequence(String name){
		if(null!=name){
			Map<String,Object> params=new HashMap<>();
			params.put("name", name);
			Counters counters=findOne(params, Counters.class);
			if(null!=counters){
				Integer seqId=counters.getSeqId()+1;
				counters.setSeqId(seqId);
				save(counters);
				return seqId;
			}else{
				counters=new Counters();
				counters.setName(name);
				save(counters);
				return counters.getSeqId();
			}
		}
		return null;
	}
	
	public <T> T findOne(Map<String,Object> params,Class<T> entityClass) {
		Query query = new Query();
		if(params == null || params.isEmpty()){
			return null;
		} else {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
			}
		}
		return mongoTemplate.findOne(query, entityClass);
	}
	
}
*/