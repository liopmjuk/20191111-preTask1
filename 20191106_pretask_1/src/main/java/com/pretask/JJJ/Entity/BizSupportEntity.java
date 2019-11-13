package com.pretask.JJJ.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="BIZ_SUPPORT")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class BizSupportEntity {
	
	@Column(name="ID")
	private String id;
	
	@Id
	@Column(name="REGION_CODE")
	private String region;
	
	@Column(name="TARGET")
	private String target;
	
	@Column(name="USAGE")
	private String usage;

	@Column(name="BOUND")
	private String bound;
	
	@Column(name="RATE")
	private String rate;
	
	@Column(name="INSTITUTE")
	private String institute;
	
	@Column(name="MGMT")
	private String mgmt;
	
	@Column(name="RECEPTION")
	private String reception;

	@Column(name="CREATE_DATE")
	private String createDate;
	
	@Column(name="UPDATE_DATE")
	private String updateDate;
}
