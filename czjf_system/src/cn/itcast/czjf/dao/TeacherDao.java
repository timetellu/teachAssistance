package cn.itcast.czjf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.czjf.domain.Admin;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.utils.JDBCUtils;

public class TeacherDao {

	public Teacher teacherLogin(String um, String up) throws SQLException {
		String sql="SELECT * FROM t_tea WHERE loginName=? AND loginPwd=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Teacher>(Teacher.class), um,up);
	}
	
	public int findTotalRecords() throws SQLException {
		//统计老师的数量
		String sql="select count(*) from t_tea";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	//获取当前页中的老师信息
	public List<Teacher> findTeachersWithPage(int startIndex, int pageSize) throws SQLException {
		String sql="select * from t_tea limit ? , ? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Teacher>(Teacher.class),startIndex,pageSize);
		
	}

	public void addTeacher(Teacher t) throws SQLException {
		String sql="INSERT INTO t_tea VALUES( NULL , ? , ? , ? , ? , ? , ? , ?)";
		Object[] params= {t.getTeaNum(),t.getTeaRealName(),t.getTeaSex(),t.getTeaAge(),t.getLoginName(),t.getLoginPwd(),"no"};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
		
	}

	public void delTeacherById(String id) throws SQLException {
		String sql="DELETE FROM t_tea WHERE teaId=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,id);
		
	}

	public void updateTeacher(Teacher t) throws SQLException {
		String sql="UPDATE t_tea SET teaNum = ?  ,teaRealName = ? , teaSex = ? , teaAge= ? , loginName = ? , loginPwd =  ? WHERE teaId=?";
		Object[] params= {t.getTeaNum(),t.getTeaRealName(),t.getTeaSex(),t.getTeaAge(),t.getLoginName(),t.getLoginPwd(),t.getTeaId()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

	public Teacher validateTeaExist(String tn) throws SQLException {
		String sql="SELECT * FROM t_tea WHERE teaNum=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Teacher>(Teacher.class), tn);
	}

}
