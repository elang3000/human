package com.wondersgroup.human.enums;

/**
 * 
 * @ClassName: ServantTypeEnum 
 * @Description: 录用人员类型 1代表公务员 2代表参公
 * @author: youyd
 * @date: 2018年8月24日 上午9:55:29
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public enum ServantTypeEnum {

	SERVANT_GONGWUYUAN(1,"公务员"),
	SERVANT_CANGONG(2,"参公人员");
	
	private Integer code;
	
	private String message;
	
	ServantTypeEnum(Integer code,String message) {
		this.code=code;
		this.message=message;
	}

	public static String getServantTypeEnumString(Integer code){
		if(code==1){
			return ServantTypeEnum.SERVANT_GONGWUYUAN.getMessage();
		}else if(code==2){
			return ServantTypeEnum.SERVANT_CANGONG.getMessage();
		}else{
			return null;
		}
	}
	
	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

}
