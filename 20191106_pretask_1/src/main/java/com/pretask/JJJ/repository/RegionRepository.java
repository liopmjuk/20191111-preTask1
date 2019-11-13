package com.pretask.JJJ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pretask.JJJ.Entity.RegionEntity;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, String>{
	
	public static final String FIND_REGION_CODE = "select r.region_code from REGION r where r.region = :region";
	
	@Query(value = FIND_REGION_CODE, nativeQuery = true)
	public String findByRegion(@Param("region") String region);
}
