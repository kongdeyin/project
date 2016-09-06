package com.project.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.project.entity.Department;
import com.project.entity.Menu;
import com.project.entity.User;
import com.project.util.Page;
import com.project.vo.UserVo;

public interface UserModel {
	/**
	 * 通过用户加载一个用户对象(登录)
	 * @param userName 用户输入的帐号
	 * @return	表示用户的帐号不存在
	 */
	public User loadUserByuserName(String userName);
	
	/**
	 * 通过uid展示所拥有的菜单
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenuByUid(int uid);
	
	/**
	 * 加载所有用户信息
	 * @return
	 */
	public Page<UserVo> loadAllUsers(int pageNo,int pageSize,String sql);
	
	/**
	 * 加载所有部门
	 */
	public List<Department> loadAllDepartmet();
	
	/**
	 * 添加用户信息
	 */
	public void addUser(String uNo,String userName,String userPassWord,String realName,String phone,String email,String QQ,String weChatNo,String emergencyContactPerson,String emergencyContactPhone,int did,String EntryTime,int iseffective);
	/**
	 * 通过uid删除用户
	 * @param uid 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteUserByUid(String uid);
	
	
}
