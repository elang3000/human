package com.wondersgroup.human.dto.ofcflow;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.human.bo.organization.OrgInfo;
public class UnitBasicInfoDTO {
	
	
	public static List<UnitBasicInfoDTO> UnitBasicInfo2DTO(List<OrgInfo> list){
		List<UnitBasicInfoDTO> dTOList=new ArrayList<>();
		for (OrgInfo unitBasicInfo : list) {
			dTOList.add(new UnitBasicInfoDTO(unitBasicInfo.getId(),unitBasicInfo.getUnitBasicName()));
		}
		return dTOList;
	}
	
	//id
	private String id;
	//鍚嶅瓧
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UnitBasicInfoDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public UnitBasicInfoDTO() {
	}
	/**
	 * 
	 * @Title:UnitBasicInfoDTO
	 * @Description:鎺ユ敹unitBasicInfo鐨勬瀯閫犳柟娉�
	 * @param unitBasicInfo
	 */
	public UnitBasicInfoDTO(OrgInfo unitBasicInfo) {
		this.id=unitBasicInfo.getId();
		this.name=unitBasicInfo.getUnitBasicName();
	}
}
