package cn.itcast.czjf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.domain.Vedio;
import cn.itcast.czjf.utils.JDBCUtils;

public class CourseDao {

	public int findTotalRecords() throws SQLException {
		//统计课程的数量
		String sql="select count(*) from t_course";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	public List<Course> findCoursesWithPage(int startIndex, int pageSize) throws SQLException {
		String sql="SELECT t1.*,t2.`teaNum`,t2.`teaRealName` FROM t_course t1 LEFT JOIN t_tea t2 ON t1.`tId`=t2.`teaId` ORDER BY t1.cId LIMIT ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Course>(Course.class),startIndex,pageSize);
	}

	public void delCourseById(String id) throws SQLException {
		String sql="DELETE FROM t_course WHERE cId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,id);
		
	}

	public void addCourse(Course course) throws SQLException {
		JDBCUtils.startTransaction();
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		//1_根据teaNum查询到teaId
		String sql1="SELECT * FROM t_tea WHERE teaNum = ?";
		Teacher tea = qr.query(sql1, new BeanHandler<Teacher>(Teacher.class),course.getTeaNum());
		int teaId = tea.getTeaId();
		//2_添加课程
		String sql2="INSERT INTO t_course VALUES( NULL , ? , ? , ? )";
		Object[] params= {course.getcNum(),course.getcName(),teaId};
		qr.update(sql2,params);
		//3_ 提交事务
		JDBCUtils.commitAndClose();
	}

	public Course validateCourseExist(String cNum) throws SQLException {
		String sql="SELECT * FROM t_course WHERE cNum=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Course>(Course.class), cNum);
	}

	public Course validateCourseExistByteacher(String cNum, int teaId) throws SQLException {
		// SELECT * FROM t_course WHERE tId=2 AND cNum = 'bx001'
		String sql="SELECT * FROM t_course WHERE tId = ? AND cNum = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Course>(Course.class), teaId,cNum);
	}

	public int findTotalRecordsByStudent(int sId) throws SQLException {
		String sql = "SELECT COUNT(t3.cId) FROM (SELECT t1.*,t2.`teaNum`,t2.`teaRealName` FROM (SELECT * FROM t_course WHERE cId IN (SELECT cId FROM t_xuanke WHERE stuId = ?)) t1 JOIN t_tea t2 ON t1.tId = t2.`teaId`) t3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),sId);
		return num.intValue();
	}

	public List<Course> findCoursesWithPageByStudent(int sId,int startIndex, int pageSize) throws SQLException {
		String sql="SELECT t1.*,t2.`teaNum`,t2.`teaRealName` FROM (SELECT * FROM t_course WHERE cId IN (SELECT cId FROM t_xuanke WHERE stuId = ?)) t1 JOIN t_tea t2 ON t1.tId = t2.`teaId` ORDER BY t1.cId LIMIT ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Course>(Course.class),sId,startIndex,pageSize);
	}

	public Course findCourseById(int cId) throws SQLException {
		String sql="SELECT * FROM t_course WHERE cId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Course>(Course.class),cId);
	}

}
