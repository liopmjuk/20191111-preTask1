package com.pretask.JJJ.Dto.Output;

import com.pretask.JJJ.exception.CustomException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FailureOutput {
	private String errorCode;
	private String errorMsg;
	
	public FailureOutput(CustomException ex) {
		this.errorCode = ex.getErrorCode();
		this.errorMsg = ex.getErrorMsg();
	}
}


