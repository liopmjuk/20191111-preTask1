package com.pretask.JJJ.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.pretask.JJJ.Entity.BizSupportEntity;
import com.pretask.JJJ.repository.RegionRepository;

public class BizSupportProcessor implements ItemProcessor<BizSupportEntity, BizSupportEntity> {
	
	@Autowired
	private RegionRepository regionRepository;
	private Logger logger = LoggerFactory.getLogger(BizSupportProcessor.class);
	
	@Override
	public BizSupportEntity process(BizSupportEntity item) throws Exception {
		// TODO Auto-generated method stub
		
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		String regionCode = regionRepository.findByRegion(item.getRegion());
		
		item = item.toBuilder()
		.region(regionCode)
		.createDate(date)
		.build();
		
		return item;
	}
}
