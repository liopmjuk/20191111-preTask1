package com.pretask.JJJ.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="REGION")
@NoArgsConstructor
@AllArgsConstructor
public class RegionEntity {
	@Id
	@Column(name="REGION_CODE")
	private String regionCode;
	
	@Column(name="REGION")
	private String region;
}
