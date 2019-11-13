package com.pretask.JJJ.Dto.Input;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBizSupportInput {
	@NotEmpty
	private String region;
	@NotEmpty
	private String target;
	@NotEmpty
	private String usage;
	@NotEmpty
	private String bound;
	@NotEmpty
	private String rate;
	@NotEmpty
	private String institute;
	@NotEmpty
	private String mgmt;
	@NotEmpty
	private String reception;
}
