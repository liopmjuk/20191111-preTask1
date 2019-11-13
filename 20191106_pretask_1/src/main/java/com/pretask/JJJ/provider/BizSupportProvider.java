package com.pretask.JJJ.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pretask.JJJ.Entity.BizSupportEntity;
import com.pretask.JJJ.repository.BizSupportRepository;

@Service
public class BizSupportProvider {
	private BizSupportRepository bizSupportRepository;
	
	@Autowired
	public BizSupportProvider(BizSupportRepository bizSupportRepository) {
		this.bizSupportRepository = bizSupportRepository;
	}
	
	public boolean checkInfoExist(String regionCode) {
		return bizSupportRepository.findById(regionCode).isPresent();
	}
	
	public BizSupportEntity getInfoByRegionCode(String regionCode) {
		return bizSupportRepository.findById(regionCode).get();
	}
	
	public List<String> getLargestSupports(int count) {
		return bizSupportRepository.findLargestSupports(count);
	}
	
	public void updateInfo(BizSupportEntity entity) {
		bizSupportRepository.save(entity);
	}
}
