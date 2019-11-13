package com.pretask.JJJ.controller;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pretask.JJJ.Dto.Input.RegionInput;
import com.pretask.JJJ.Dto.Input.UpdateBizSupportInput;
import com.pretask.JJJ.Dto.Output.RegionSupportOutput;
import com.pretask.JJJ.Dto.Output.SuccessOutput;
import com.pretask.JJJ.exception.CustomException;
import com.pretask.JJJ.service.BizSupportService;

@RestController
@RequestMapping(value="/biz")
public class BizSupportController {
	
	@Autowired	
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	private BizSupportService bizSupportService;
	
	@Autowired
	public BizSupportController (BizSupportService bizSupportService) {
		this.bizSupportService = bizSupportService;
	}
	
	@RequestMapping(value="/saveAll", method=RequestMethod.GET)
	public SuccessOutput saveAllBizInfo() throws Exception {
		try {
			JobParameters params = new JobParametersBuilder()
					.toJobParameters();
			jobLauncher.run(job, params);
		} catch(Exception e) {
			throw new CustomException("", "데이터 처리 중 오류가 발생했습니다.");
		}
		return new SuccessOutput("Success", "정상처리 되었습니다.");
	}
	
	@RequestMapping(value="/getDataAll", method=RequestMethod.GET)
	public List<RegionSupportOutput> getAllBizInfo() throws Exception {
		return bizSupportService.getAllBizInfo();
	}
	
	@RequestMapping(value="/getData", method=RequestMethod.POST)
	public RegionSupportOutput getBizInfo(@RequestBody RegionInput input) throws Exception {
		return bizSupportService.getBizInfo(input);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public SuccessOutput updateBizInfo(@RequestBody UpdateBizSupportInput input) throws Exception {
		bizSupportService.updateBizInfo(input);
		
		return new SuccessOutput("Success", "정상처리 되었습니다.");
	}
	
	@RequestMapping(value="/boundList/{count}", method=RequestMethod.GET)
	public List<String> getLargestBoundInfos(@PathVariable("count") int count) throws Exception {
		if(count == 0) {
			throw new CustomException("", "조회할 숫자를 입력해주세요.");
		}
		
		return bizSupportService.getLargestBoundInfos(count);
	}
	
	@RequestMapping(value="/lowestLimit", method=RequestMethod.GET)
	public Map<String, String> getLowestLimit() throws Exception {
		return bizSupportService.getLowestLimit();
	}
}
