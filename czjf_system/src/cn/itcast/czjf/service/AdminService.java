package cn.itcast.czjf.service;

import java.sql.SQLException;

import cn.itcast.czjf.dao.AdminDao;
import cn.itcast.czjf.domain.Admin;

public class AdminService {

	public Admin adminLogin(String um, String up)  throws SQLException {
		//调用DAO层功能
		AdminDao AdminDao=new AdminDao();
		return AdminDao.adminLogin(um,up);
		
	}

	public void updateAdmin(Admin a) throws SQLException {
		AdminDao AdminDao=new AdminDao();
		AdminDao.updateAdmin(a);
	}

}
