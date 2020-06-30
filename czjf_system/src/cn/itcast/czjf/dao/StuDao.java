package cn.itcast.czjf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.domain.Teacher;
import cn.itcast.czjf.utils.JDBCUtils;

public class StuDao {

	public Student validateUserExist(String um) throws SQLException {
		String sql="SELECT *  FROM t_stu WHERE stuNum=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Student>(Student.class),um);
		
	}

	public Student stuLogin(String um, String up) throws SQLException {
		String sql="SELECT *  FROM t_stu WHERE stuNum=? AND loginPw=? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Student>(Student.class),um,up);
	}



	public int findTotalRecords() throws SQLException {
		//统计学生的数量
		String sql="select count(*) from t_stu";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	public List<Student> findStudentsWithPage(int startIndex, int pageSize) throws SQLException {
		String sql="SELECT t1.*,t2.cNum,t2.cName FROM (SELECT t3.cId,t3.cNum,t3.cName,t4.`stuId` FROM t_course t3 LEFT JOIN t_xuanke t4 ON t4.`cId` = t3.`cId` WHERE t4.stuId != 0) t2 LEFT JOIN t_stu t1  ON t2.`stuId` = t1.`stuId` ORDER BY t1.stuId LIMIT ?,?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Student>(Student.class),startIndex,pageSize);
	}

	public void delStudentById(String id) throws SQLException {
		String sql="DELETE FROM t_stu WHERE stuId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,id);
	}

	public void addStudent(Student stu) throws SQLException {
		JDBCUtils.startTransaction();
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		
		//1_添加学生
		String sql1="INSERT INTO t_stu VALUES( NULL , ? , ? , ? , ? , ? , ? , ?)";
		Object[] params1= {stu.getStuNum(),stu.getStuRealname(),stu.getStuSex(),stu.getStuAge(),stu.getLoginPw(),100,"no"};
		qr.update(sql1,params1);
		//2_往t_xuanke表中添加学生和课程的绑定关系
		String sql2="INSERT INTO t_xuanke VALUES(NULL,(SELECT cId FROM t_course WHERE cNum=?), (SELECT stuId FROM t_stu WHERE stuNum = ?))";
		Object[] params2= {stu.getcNum(),stu.getStuNum()};
		qr.update(sql2, params2);
		//3_ 提交事务
		JDBCUtils.commitAndClose();
	}

	public List<Student> findStudentsWithPageByTeacher(int teaId, int startIndex, int pageSize) throws SQLException {
		String sql1="SELECT t1.*,t2.cNum,t2.cName,t2.tId  FROM (SELECT t3.*,t4.`cId` FROM t_stu t3 LEFT JOIN t_xuanke t4 ON t3.`stuId` = t4.`stuId`) t1 RIGHT JOIN (SELECT * FROM t_course WHERE tId = ?) t2 ON t1.`cId` = t2.cId WHERE stuId!= 0 ORDER BY t2.cId  LIMIT ?,?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Student> stu =  qr.query(sql1, new BeanListHandler<Student>(Student.class),teaId,startIndex,pageSize);
		return stu;
	}

	public int findTotalRecordsByXK() throws SQLException {
		//统计学生选课表记录的数量 ———— admin
		String sql="select count(id) from t_xuanke";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	public int findTotalRecordsByTeacher(int teaId) throws SQLException {
		//统计学生选课表记录的数量 ———— atea
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT COUNT(t5.tId) FROM (SELECT t1.*,t2.cNum,t2.cName,t2.tId  FROM (SELECT t3.*,t4.`cId` FROM t_stu t3 LEFT JOIN t_xuanke t4 ON t3.`stuId` = t4.`stuId`) t1 RIGHT JOIN (SELECT * FROM t_course WHERE tId = ?) t2 ON t1.`cId` = t2.cId WHERE stuId!= 0 ) t5";
		Long num=(Long)qr.query(sql, new ScalarHandler(),teaId);
		return num.intValue();
	}


	

}
