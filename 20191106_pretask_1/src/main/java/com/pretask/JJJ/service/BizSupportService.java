package com.pretask.JJJ.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pretask.JJJ.Dto.Input.UpdateBizSupportInput;
import com.pretask.JJJ.Dto.Output.RegionSupportOutput;
import com.pretask.JJJ.Dto.Input.RegionInput;
import com.pretask.JJJ.Entity.BizSupportEntity;
import com.pretask.JJJ.exception.CustomException;
import com.pretask.JJJ.provider.BizQueryProvider;
import com.pretask.JJJ.provider.BizSupportProvider;
import com.pretask.JJJ.repository.RegionRepository;

@Service
public class BizSupportService {
	
	private Logger logger = LoggerFactory.getLogger(BizSupportService.class);
	
	private BizQueryProvider bizQueryProvider;
	private BizSupportProvider bizSupportProvider;
	private RegionRepository regionRepository;
	private EntityManager em;

	@Autowired
    public BizSupportService(BizQueryProvider bizQueryProvider, BizSupportProvider bizSupportProvider,
    		RegionRepository regionRepository, EntityManager em) {
    	this.bizQueryProvider = bizQueryProvider;
    	this.bizSupportProvider = bizSupportProvider;
    	this.regionRepository = regionRepository;
        this.em = em;
    }
    
    public List<RegionSupportOutput> getAllBizInfo() {
    	return bizQueryProvider.findAllData(em);
    }
    
    public RegionSupportOutput getBizInfo(RegionInput input) {
    	String regionCode = regionRepository.findByRegion(input.getRegion());
    	if(regionCode == null || "".equals(regionCode)) {
    		throw new CustomException("","존재하지 않는 정보입니다.");
    	}
    	
    	BizSupportEntity entity = bizSupportProvider.getInfoByRegionCode(regionCode);
    	
    	return RegionSupportOutput.dataTransfer(entity, input.getRegion());
    }
    
    public void updateBizInfo(UpdateBizSupportInput input) {
    	
    	String regionCode = regionRepository.findByRegion(input.getRegion());
    	if(regionCode == null || "".equals(regionCode)) {
    		throw new CustomException("","존재하지 않는 지역 정보입니다.");
    	}
    	
    	BizSupportEntity entity = bizSupportProvider.getInfoByRegionCode(regionCode);
    	if(entity == null) {
    		throw new CustomException("","존재하지 않는 지자체 협력 정보입니다.");
    	}
    	
    	String updateDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	entity = entity.toBuilder()
    			.target(input.getTarget())
    			.usage(input.getUsage())
    			.bound(input.getBound())
    			.rate(input.getRate())
    			.institute(input.getInstitute())
    			.mgmt(input.getMgmt())
    			.reception(input.getReception())
    			.updateDate(updateDate)
    			.build();
    	
    	logger.debug("update data >>> {}", entity.toString());
    	try {
    		bizSupportProvider.updateInfo(entity);
    	} catch (Exception ex) {
			throw new CustomException("", "데이터 수정 중 오류가 발생했습니다.");
		}
    }
    
    public List<String> getLargestBoundInfos(int count) {
    	List<String> regionList = bizSupportProvider.getLargestSupports(count);
    	
    	return regionList;
    }
    
    public Map<String, String> getLowestLimit() {
    	Map<String, String> resultMap = new HashMap<>();
    	
    	String institute = bizQueryProvider.findLowestLimit(em);
    	
    	resultMap.put("institute", institute);
    	return resultMap;
    }
}
