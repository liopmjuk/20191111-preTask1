package com.pretask.JJJ.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class RegionPrgmInfo {
	private String region;
	private String count;
}
