package cn.itcast.czjf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Demo;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.utils.JDBCUtils;

public class DemoDao {

	public void addDemo(Demo demo) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String cNum = demo.getcNum();
		String sql1 = "SELECT * FROM t_course WHERE cNum = ?";
		Course cour = qr.query(sql1, new BeanHandler<Course>(Course.class),cNum);
		int cId = cour.getcId();
		String sql="INSERT INTO t_demo VALUES(NULL , ? ,  ? ,  ? ,  ? ,  ?  ,? ,? )";
		Object[] params= {demo.getDemoName(),demo.getDemoAttachment(),demo.getAttachmentOldName(),demo.getUploadTime(),demo.getDel(),demo.getStatus(),cId};
		qr.update(sql,params);
		
	}

	public int findTotalRecordsByTeacher(int tId) throws SQLException {
		String sql = "SELECT COUNT(t3.demoId) FROM (SELECT t1.cNum,t1.cName,t1.tId,t2.* FROM (SELECT * FROM t_course WHERE tId = ?) t1 LEFT JOIN  t_demo t2 ON t1.`cId` = t2.cId WHERE t2.`demoId`!=0 )t3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),tId);
		return num.intValue();
	}

	public List<Demo> findDemosWithPageByTeacher(int tId, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT t1.cNum,t1.cName,t1.tId,t2.* FROM (SELECT * FROM t_course WHERE tId = ?) t1 LEFT JOIN  t_demo t2 ON t1.`cId` = t2.cId WHERE t2.`demoId`!=0 ORDER BY t1.cId LIMIT ?,? ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Demo>(Demo.class),tId,startIndex,pageSize);
	}

	public void deleteDemoByTeacher(int demoId) throws SQLException {
		String sql = "delete from t_demo where demoId = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,demoId);		
	}

	public Demo findDemoById(int demoId) throws SQLException {
		String sql="SELECT * FROM t_demo t1 JOIN t_course t2 ON t1.`cId`=t2.`cId` WHERE t1.demoId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Demo>(Demo.class),demoId);
	}

	public int findTotalRecordsByCourse(int cId) throws SQLException {
		String sql = "SELECT COUNT(cId) FROM t_demo WHERE cId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),cId);
		return num.intValue();
	}

	public List<Demo> findDemosWithPageByCourse(int cId, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT t1.*,t2.`cName`,t2.`cNum` FROM t_demo t1 JOIN t_course t2 ON t1.`cId` = t2.`cId` WHERE t1.`cId` = ?  ORDER BY demoId LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Demo>(Demo.class),cId,startIndex,pageSize);
	}

	public List<Demo> findDemoExerciseByCourse(int cId) throws SQLException {
		String sql = "SELECT t1.*,t2.`cName`,t2.`cNum` FROM t_demo t1 JOIN t_course t2 ON t1.`cId` = t2.`cId` WHERE t1.`cId` = ?  ORDER BY demoId ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Demo>(Demo.class),cId);
	}

	public Demo findDemoRecordsByCourse(int cId) throws SQLException {
		String sql = "SELECT * FROM t_demo WHERE cId = ? ORDER BY demoId DESC LIMIT 1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Demo>(Demo.class),cId);
	}

}
