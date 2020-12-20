package com.lighthouse.team0.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ImageMapper {
	
	@Insert("insert into image values (#{img1})")
	public void saveImage(@Param("img1") byte[] img);
	
	@Select("select * from image")
	public List<byte[]> getAllImages();
	

}
