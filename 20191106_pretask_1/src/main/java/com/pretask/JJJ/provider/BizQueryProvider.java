package com.pretask.JJJ.provider;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pretask.JJJ.Dto.Output.RegionSupportOutput;

@Service
public class BizQueryProvider {
	
	private Logger logger = LoggerFactory.getLogger(BizQueryProvider.class);
	
	public List<RegionSupportOutput> findAllData(EntityManager em) {
		String jpql = "select r.region, b.target, b.usage, b.bound, b.rate, b.institute, b.mgmt, b.reception\r\n"
				+ "from region r, biz_support b\r\n"
				+ "where r.region_code = b.region_code";

		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		Query query = em.createNativeQuery(jpql);
		List<RegionSupportOutput> results = jpaResultMapper.list(query, RegionSupportOutput.class);
		return results;
	}
	
	public String findLowestLimit(EntityManager em) {
		String jpql = "select institute\r\n" + 
				"from biz_support\r\n" + 
				"order by rate asc\r\n" + 
				"limit 1";

		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		Query query = em.createNativeQuery(jpql);
		String result = jpaResultMapper.list(query, String.class).get(0);
		return result;
	}
}
