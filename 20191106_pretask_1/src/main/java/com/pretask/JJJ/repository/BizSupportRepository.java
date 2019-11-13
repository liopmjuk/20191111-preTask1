package com.pretask.JJJ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pretask.JJJ.Entity.BizSupportEntity;

@Repository
public interface BizSupportRepository extends JpaRepository<BizSupportEntity, String>{
	public static final String FIND_LARGEST_SUPPORT = ""
			+ "with news as (\r\n" + 
			"	select region, boundNo\r\n" + 
			"		from (select r.region as region, convert(regexp_replace(b.bound, '백만원 이내|백만원이내', '000000'), decimal(20)) as boundNo\r\n" + 
			"			from region r, biz_support b\r\n" + 
			"			where r.region_code = b.region_code\r\n" + 
			"			and b.bound like '%백%'"
			+ "		)\r\n" + 
			"	UNION ALL\r\n" + 
			"	select region, boundNo\r\n" + 
			"		from (select r.region as region, convert(regexp_replace(b.bound, '천만원 이내|천만원이내|추천금액 이내', '0000000'), decimal(20)) as boundNo\r\n" + 
			"			from region r, biz_support b\r\n" + 
			"			where r.region_code = b.region_code\r\n" + 
			"			and b.bound like '%천%'"
			+ "		)\r\n" + 
			"	UNION ALL\r\n" + 
			"	select region, boundNo\r\n" + 
			"		from (select r.region as region, convert(regexp_replace(b.bound, '억원 이내|억원이내', '00000000'), decimal(20)) as boundNo\r\n" + 
			"			from region r, biz_support b\r\n" + 
			"			where r.region_code = b.region_code\r\n" + 
			"			and b.bound like '%억%'"
			+ "		)\r\n" + 
			"	order by boundNo desc\r\n" + 
			")\r\n" + 
			"select region from news limit :count" + 
			"";
	
	@Query(value=FIND_LARGEST_SUPPORT, nativeQuery=true)
	List<String> findLargestSupports(@Param("count") int count);
}
