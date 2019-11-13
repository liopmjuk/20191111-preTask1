package com.pretask.JJJ.Dto.Output;

import com.pretask.JJJ.Entity.BizSupportEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionSupportOutput {
	private String region;
	private String target;
	private String usage;
	private String bound;
	private String rate;
	private String institute;
	private String mgmt;
	private String reception;
	
	public static RegionSupportOutput dataTransfer(BizSupportEntity entity, String region) {
		return RegionSupportOutput.builder()
			.region(region)
			.target(entity.getTarget())
			.usage(entity.getUsage())
			.bound(entity.getBound())
			.rate(entity.getRate())
			.institute(entity.getInstitute())
			.mgmt(entity.getMgmt())
			.reception(entity.getReception())
			.build();
	}
}
