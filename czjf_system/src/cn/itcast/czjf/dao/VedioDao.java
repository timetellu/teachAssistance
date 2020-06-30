package cn.itcast.czjf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Vedio;
import cn.itcast.czjf.utils.JDBCUtils;

public class VedioDao {

	public List<Vedio> findPrevVedio() throws SQLException {
		String sql="SELECT * FROM t_vedio ORDER BY uploadTime DESC LIMIT 0 , 5";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Vedio>(Vedio.class));
	}

	
	public List<Vedio> findVedioWithPage(int sId,int startIndex, int pageSize) throws SQLException {
		String sql="SELECT t1.*,t2.stuId FROM t_vedio t1 RIGHT JOIN (SELECT cId,stuId FROM t_xuanke WHERE stuId=?) t2 ON t1.`cId` = t2.cId WHERE t1.`vedioId`!=0 ORDER BY t2.cId LIMIT ? , ? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Vedio>(Vedio.class),sId,startIndex,pageSize);
		
	}
	public int findTotalRecords() throws SQLException {
		String sql="SELECT COUNT(*) FROM t_vedio";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}


	public Vedio findVedioByVid(String vId) throws SQLException {
		String sql="SELECT * FROM t_vedio t1 JOIN t_course t2 ON t1.`cId`=t2.`cId` WHERE vedioId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Vedio>(Vedio.class),vId);
	}


	public List<Vedio> findVediosWithPageByTeacher(int tId, int startIndex, int pageSize) throws SQLException {
		String sql="SELECT t1.cNum,t1.cName,t1.tId,t2.* FROM (SELECT * FROM t_course WHERE tId = ?) t1 LEFT JOIN  t_vedio t2 ON t1.`cId` = t2.`cId` WHERE t2.`vedioId`!=0 ORDER BY t1.cId limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Vedio>(Vedio.class),tId,startIndex,pageSize);
		
	}


	public void deleteVedioByTeacher(String vId) throws SQLException {
		String sql="delete from t_vedio where vedioId=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,vId);
	}


	public void addVedio(Vedio v) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String cNum = v.getcNum();
		System.out.println("cNUm:  "+cNum); //测试数据
		String sql1 = "SELECT * FROM t_course WHERE cNum = ?";
		Course cour = qr.query(sql1, new BeanHandler<Course>(Course.class),cNum);
		int cId = cour.getcId();
		String sql="INSERT INTO t_vedio VALUES(NULL , ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,?)";
		Object[] params= {v.getVedioName(),v.getVedioPro(),v.getVedioAttachment(),v.getAttachmentOldName(),v.getUploadTime(),v.getDel(),v.getStatus(),cId};
		qr.update(sql,params);
	}


	public int findTotalRecordsByteacher(int tId) throws SQLException {
		String sql="SELECT COUNT(t3.vedioId) FROM (SELECT t1.cNum,t1.cName,t1.tId,t2.* FROM (SELECT * FROM t_course WHERE tId = ?) t1 LEFT JOIN  t_vedio t2 ON t1.`cId` = t2.`cId` WHERE t2.`vedioId`!=0 ) t3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),tId);
		return num.intValue();
	}


	public int findTotalRecordsByStudent(int sId) throws SQLException {
		String sql="SELECT COUNT(t3.vedioId) FROM (SELECT t1.*,t2.stuId FROM t_vedio t1 RIGHT JOIN (SELECT cId,stuId FROM t_xuanke WHERE stuId=?) t2 ON t1.`cId` = t2.cId WHERE t1.`vedioId`!=0 ORDER BY t2.cId) t3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),sId);
		return num.intValue();
	}


	public int findTotalRecordsByCourse(int cId) throws SQLException {
		String sql = "SELECT COUNT(vedioId) FROM t_vedio WHERE cId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),cId);
		return num.intValue();
	}


	public List<Vedio> findVedioWithPageByCourse(int cId, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT t1.*,t2.`cName`,t2.`cNum` FROM t_vedio t1 JOIN t_course t2 ON t1.`cId` = t2.`cId` WHERE t1.`cId`= ?  ORDER BY vedioId LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Vedio>(Vedio.class),cId,startIndex,pageSize);
	}


	
}
