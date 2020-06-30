package cn.itcast.czjf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.utils.JDBCUtils;

public class DocumentDao {

	public List<Document> findPrevDocument() throws SQLException {
		String sql="SELECT * FROM t_doc ORDER BY uploadTime DESC LIMIT 0 , 5";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Document>(Document.class));
	}

	public Document findDocumentById(int docId) throws SQLException {
		String sql="SELECT * FROM t_doc t1 JOIN t_course t2 ON t1.`cId`=t2.`cId` WHERE t1.docId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Document>(Document.class),docId);
	}

	public List<Document> findDocumentsWithPage(int sId,int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT t1.*,t2.stuId FROM t_doc t1 RIGHT JOIN (SELECT cId,stuId FROM t_xuanke WHERE stuId=?) t2 ON t1.`cId` = t2.cId WHERE t1.`docId`!=0 ORDER BY cId LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Document>(Document.class),sId,startIndex,pageSize);
	}

	public List<Document> findDocumentsWithPageByTeacher( int tId, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT t1.cNum,t1.cName,t1.tId,t2.* FROM (SELECT * FROM t_course WHERE tId = ?) t1 LEFT JOIN  t_doc t2 ON t1.`cId` = t2.cId WHERE t2.`docId`!=0 ORDER BY t1.cId LIMIT ?,? ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Document>(Document.class),tId,startIndex,pageSize);
	}

	public void deleteDocumentByTeacher(int docId) throws SQLException {
		String sql = "delete from t_doc where docId = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,docId);
	}

	public void addDocument(Document document) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String cNum = document.getcNum();
		System.out.println("cNUm:  "+cNum);
		String sql1 = "SELECT * FROM t_course WHERE cNum = ?";
		Course cour = qr.query(sql1, new BeanHandler<Course>(Course.class),cNum);
		int cId = cour.getcId();
		String sql="INSERT INTO t_doc VALUES(NULL , ? ,  ? ,  ? ,  ? ,  ?  ,? ,? )";
		Object[] params= {document.getDocName(),document.getDocAttachment(),document.getAttachmentOldName(),document.getUploadTime(),document.getDel(),document.getStatus(),cId};
		qr.update(sql,params);
		
	}

	public int findTotalRecordsByTeacher(int tId) throws SQLException {
		String sql = "SELECT COUNT(t3.docId) FROM (SELECT t1.cNum,t1.cName,t1.tId,t2.* FROM (SELECT * FROM t_course WHERE tId = ?) t1 LEFT JOIN  t_doc t2 ON t1.`cId` = t2.cId WHERE t2.`docId`!=0 )t3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),tId);
		return num.intValue();
	}

	public int findTotalRecordsByStudent(int sId) throws SQLException {
		String sql = "SELECT COUNT(t3.docId) FROM (SELECT t1.*,t2.stuId FROM t_doc t1 RIGHT JOIN (SELECT cId,stuId FROM t_xuanke WHERE stuId=?) t2 ON t1.`cId` = t2.cId WHERE t1.`docId`!=0) t3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),sId);
		return num.intValue();
	}

	public int findTotalRecordsByCourse(int cId) throws SQLException {
		// SELECT COUNT(cId) FROM t_doc WHERE cId = 1
		String sql = "SELECT COUNT(cId) FROM t_doc WHERE cId = ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),cId);
		return num.intValue();
	}

	public List<Document> findDocumentsWithPageByCourse(int cId, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT t1.*,t2.`cName`,t2.`cNum` FROM t_doc t1 JOIN t_course t2 ON t1.`cId` = t2.`cId` WHERE t1.`cId` = ?  ORDER BY docId LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Document>(Document.class),cId,startIndex,pageSize);
	}



}
