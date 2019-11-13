package com.pretask.JJJ.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pretask.JJJ.Dto.Input.RegionInput;
import com.pretask.JJJ.Dto.Input.UpdateBizSupportInput;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreTaskTests1 {
	@Autowired
	private TestRestTemplate rest;
	
	@Test
	public void test1_저장테스트() {
		ResponseEntity<String> result = rest.getForEntity("/biz/saveAll", String.class);
		
		System.out.println(result);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
	}
	
	@Test
	public void test2_조회테스트() {
		
		ResponseEntity<String> result = rest.getForEntity("/biz/getDataAll", String.class);
		
		System.out.println(result);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull();
	}
	
	@Test
	public void test3_지역조회테스트() {
		
		String region = "강릉시";
		
		RegionInput input = RegionInput.builder()
				.region(region)
				.build();
		
		ResponseEntity<String> result = rest.postForEntity("/biz/getData", input, String.class);
		
		System.out.println(result);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
	}
	
	@Test
	public void test4_수정테스트() {
		UpdateBizSupportInput input = UpdateBizSupportInput.builder()
//				.region("테스트시")
				.region("강릉시")
				.target("강릉시 소재 중소기업으로서 강릉시장이 추천한 자")
				.usage("테스트")
				.bound("100억원 이내")
				.rate("1%")
				.institute("강릉시 지자체")
				.mgmt("강릉 테스트지점")
				.reception("강릉시 소재 테스트 영업점")
				.build();
		
		ResponseEntity<String> result = rest.postForEntity("/biz/update", input, String.class);
		
		System.out.println(result);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
	}
	
	@Test
	public void test5_상위갯수데이터조회테스트() {
		
		ResponseEntity<String> result = rest.getForEntity("/biz/boundList/5", String.class);
		
		System.out.println(result);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull();
	}
	
	@Test
	public void test6_최저지원지자체조회테스트() {
		
		ResponseEntity<String> result = rest.getForEntity("/biz/lowestLimit", String.class);
		
		System.out.println(result);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull();
	}
}
