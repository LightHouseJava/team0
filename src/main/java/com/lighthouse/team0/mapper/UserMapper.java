package com.lighthouse.team0.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lighthouse.team0.entity.User;

@Mapper
public interface UserMapper {
	
	@Insert("insert into users values (#{userName}, #{password})")
	public void insertNewUser(@Param("userName") String userName, @Param("password") String password);
	
	@Select("SELECT EXISTS(SELECT 1 FROM users WHERE username=#{userName})")
	public boolean checkUserExists(@Param("userName") String userName);
	
	@Select("SELECT * FROM users WHERE username = #{userName}")
    public User findByUserName(@Param("userName") String userName);
}
