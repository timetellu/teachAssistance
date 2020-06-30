package cn.itcast.czjf.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.czjf.domain.Admin;
import cn.itcast.czjf.utils.JDBCUtils;

public class AdminDao {

	public Admin adminLogin(String um, String up) throws SQLException {
		String sql="SELECT * FROM t_admin WHERE userName= ? AND userPw=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),um,up);
		
	}

	public void updateAdmin(Admin a) throws SQLException {
		String sql="UPDATE t_admin SET userPw = ? WHERE userId = ?";
		Object[] params= {a.getUserPw(),a.getUserId()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

}
