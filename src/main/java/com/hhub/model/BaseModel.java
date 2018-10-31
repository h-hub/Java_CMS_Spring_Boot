package com.hhub.model;

import java.util.Date;

public interface BaseModel {
	
	public void setCreateBy(String userEmail);
	
	public String getCreateBy();
	
	public void setCreateTime(Date createDate);
	
	public Date getCreateTime();

	public void setModifyBy(String userEmail);
	
	public String getModifyBy();
	
	public void setModifyTime(Date modifyDate);
	
	public Date getModifyTime();
	
}
