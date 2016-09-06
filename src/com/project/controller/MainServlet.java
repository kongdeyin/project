package com.project.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.project.entity.Department;
import com.project.entity.Menu;
import com.project.entity.User;
import com.project.model.UserModel;
import com.project.model.impl.UserModelMySQLImpl;
import com.project.util.Page;
import com.project.vo.UserVo;


public class MainServlet extends HttpServlet{
	
	//控制层持有一个模型层对象
	private UserModel userModel = new UserModelMySQLImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接收所有请求
		req.setCharacterEncoding("utf-8");
		String methodName = req.getParameter("methodName");
		Class c = MainServlet.class;
		try {
			Method m = c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this, req,resp);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		User user = userModel.loadUserByuserName(userName);
		int result = 1;
		if (user != null){
			// 用户名存在
			if (userPassword.equals(user.getUserPassWord())) {
				List<Menu> menulist = userModel.loadMenuByUid(user.getUid());
				req.getSession().setAttribute("menulist", menulist);
				// 密码正确
				req.getSession().setAttribute("loginUser", user);
				result = 1;
			} else {
				result = 2;
			}
		} else {
			result = 3;
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(result+"");
		resp.getWriter().flush();
	}
	
	/**
	 * 用户退出
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.sendRedirect("index.jsp");
	}
	
	/**
	 * 展示所有用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showUsers(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		int pageSize = Integer.valueOf(req.getParameter("pageSize"));
		String sql = req.getParameter("sql");
		
		Page<UserVo> page = userModel.loadAllUsers(pageNo,pageSize,sql);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",page.getDataList());
		map.put("total",page.getTotal());
		String json = JSONObject.fromObject(map).toString();
		
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
	}
	
	/**
	 * 添加用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addUser(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String uNo = req.getParameter("uNo");
		String userName = req.getParameter("userName");
		String userPassWord = req.getParameter("userPassWord");
		String realName = req.getParameter("realName");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String QQ = req.getParameter("QQ");
		String weChatNo = req.getParameter("weChatNo");
		String emergencyContactPerson = req.getParameter("emergencyContactPerson");
		String emergencyContactPhone = req.getParameter("emergencyContactPhone");
		int did = Integer.parseInt(req.getParameter("did"));
		String EntryTime = req.getParameter("EntryTime");
		int iseffective = Integer.parseInt(req.getParameter("iseffective"));
		userModel.addUser(uNo, userName, userPassWord, realName, phone, email, QQ, weChatNo, emergencyContactPerson, emergencyContactPhone, did, EntryTime, iseffective);
		
	}
	/**
	 * 展示所有部门
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showdepartment(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		List<Department> department = userModel.loadAllDepartmet();
		String json = JSONArray.fromObject(department).toString();
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
	}
	/**
	 * 通过uid删除用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteUserByUid(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String uid = req.getParameter("uid");
		userModel.deleteUserByUid(uid);
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	
	
}
