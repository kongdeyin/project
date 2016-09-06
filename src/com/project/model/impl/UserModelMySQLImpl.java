package com.project.model.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDaoImpl;
import com.project.entity.Department;
import com.project.entity.Menu;
import com.project.entity.User;
import com.project.model.UserModel;
import com.project.util.Page;
import com.project.vo.UserVo;

public class UserModelMySQLImpl implements UserModel{
	
	//模型层有一个DAO层对象
	private UserDao userDao = new UserDaoImpl();
	/**
	 * 通过用户加载一个用户对象(登录)
	 * @param userName 用户输入的帐号
	 * @return	表示用户的帐号不存在
	 */
	public User loadUserByuserName(String userName){
		User user = userDao.loadUserByuserName(userName);
		return user;
	}
	
	/**
	 * 通过uid展示所拥有的菜单
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenuByUid(int uid){
		return userDao.loadMenuByUid(uid);
	}
	
	/**
	 * 加载所有用户信息
	 * @return
	 */
	public Page<UserVo> loadAllUsers(int pageNo,int pageSize,String sql){
		return userDao.loadAllUsers(pageNo, pageSize,sql);
	}
	
	/**
	 * 加载所有部门
	 */
	public List<Department> loadAllDepartmet(){
		return userDao.loadAllDepartmet();
	}
	
	/**
	 * 添加用户信息
	 */
	public void addUser(String uNo,String userName,String userPassWord,String realName,String phone,String email,String QQ,String weChatNo,String emergencyContactPerson,String emergencyContactPhone,int did,String EntryTime,int iseffective){
		userDao.addUser(uNo, userName, userPassWord, realName, phone, email, QQ, weChatNo, emergencyContactPerson, emergencyContactPhone, did, EntryTime, iseffective);
	}
	
	/**
	 * 通过uid删除用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteUserByUid(String uid){
		userDao.deleteUserByUid(uid);
	}
}
