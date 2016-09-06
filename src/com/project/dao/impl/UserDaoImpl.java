package com.project.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.project.dao.UserDao;
import com.project.entity.Department;
import com.project.entity.Menu;
import com.project.entity.User;
import com.project.util.DBUtil;
import com.project.util.Page;
import com.project.vo.UserVo;

public class UserDaoImpl implements UserDao{
	/**
	 * 通过用户加载一个用户对象(登录)
	 * @param userName 用户输入的帐号
	 * @return	表示用户的帐号不存在
	 */
	public User loadUserByuserName(String userName){
		String sql = "select * from user where userName = ?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{userName});
		User user = null;
		if(list != null && list.size() > 0){
			Object[] os =list.get(0);
			user = new User((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), String.valueOf(os[3]), String.valueOf(os[4]), String.valueOf(os[5]), String.valueOf(os[6]), String.valueOf(os[7]), String.valueOf(os[8]), String.valueOf(os[9]), String.valueOf(os[10]), (Integer)os[11], String.valueOf(os[12]), (Integer)os[13]);
		}
		return user;
	}
	
	/**
	 * 通过uid展示所拥有的菜单
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenuByUid(int uid) {
		String sql = "select m.* from userrole ur,role r,rolemenu rm,menu m where m.mid = rm.mid and rm.rid = r.rid and r.rid = ur.rid and ur.uid=?";
		List<Object[]> list = DBUtil.executeQuery(sql,new Object[]{uid});
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m = null;
		if(list != null && list.size() > 0){
			for(Object[] os : list){
				m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
				menuList.add(m);
			}
		}
		return menuList;
	}
	
	
	/**
	 * 加载所有用户信息
	 * @return
	 */
	public Page<UserVo> loadAllUsers(int pageNo,int pageSize,String sql){
		
		String sql1 = "select u.uid,u.uNo,u.userName,u.userPassWord,u.realName,u.phone,u.email,u.QQ,u.weChatNo,u.emergencyContactPerson,u.emergencyContactPhone,(select d.dname from department d where d.did=u.did),u.EntryTime,u.iseffective from user u where u.iseffective=1";
		String sql2 = " limit ?,?";
		String sql3 = null;
		if(null != sql && sql !=""){
			sql3 = sql1 + sql + sql2;
		}else{
			sql3 = sql1 + sql2;
		}
		List<Object[]> list = DBUtil.executeQuery(sql3, new Object[]{(pageNo-1)*pageSize,pageSize});
		List<UserVo> userList = new ArrayList<UserVo>();
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				UserVo userVo = new UserVo((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), String.valueOf(os[3]), String.valueOf(os[4]), String.valueOf(os[5]), String.valueOf(os[6]), String.valueOf(os[7]), String.valueOf(os[8]), String.valueOf(os[9]), String.valueOf(os[10]), String.valueOf(os[11]), String.valueOf(os[12]), (Integer)os[13]);
				userList.add(userVo);
			}
		}
		sql = "select count(*) from user";
		list = DBUtil.executeQuery(sql, null);
		long total = (Long)list.get(0)[0];
		return new Page<UserVo>(pageNo, pageSize, userList, total);
		
	}
	
	/**
	 * 加载所有部门
	 */
	public List<Department> loadAllDepartmet(){
		String sql = "select * from department";
		List<Object[]> list = DBUtil.executeQuery(sql,null);
		//Department department = null;
		List<Department> departmentList = new ArrayList<Department>();
		if(list != null && list.size() > 0){
			for(Object[] os : list){
				Department department = new Department((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), String.valueOf(os[3]), (Integer)os[4]);
				departmentList.add(department);
			}
		}
		return departmentList;
	}
	
	/**
	 * 添加用户信息
	 */
	public void addUser(String uNo,String userName,String userPassWord,String realName,String phone,String email,String QQ,String weChatNo,String emergencyContactPerson,String emergencyContactPhone,int did,String EntryTime,int iseffective){
		Object[] o = {uNo,userName,userPassWord,realName,phone,email,QQ,weChatNo,emergencyContactPerson,emergencyContactPhone,did,EntryTime,iseffective};
		String sql = "insert into user(uNo,userName,userPassWord,realName,phone,email,QQ,weChatNo,emergencyContactPerson,emergencyContactPhone,did,EntryTime,iseffective) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DBUtil.executeDML(sql, o);
	}
	
	/**
	 * 通过uid删除用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteUserByUid(String uid){
		String sql = "update user set iseffective = 0 where uid in ("+uid+")";
		DBUtil.executeDML(sql, null);
	}
}
