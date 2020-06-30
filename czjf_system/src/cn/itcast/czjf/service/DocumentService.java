package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.DocumentDao;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.utils.PageModel;

public class DocumentService {

	public List<Document> findPrevDocument() throws SQLException {
		DocumentDao DocumentDao = new DocumentDao();
		return DocumentDao.findPrevDocument();
	}

	public Document findDocumentById(int docId) throws SQLException {
		DocumentDao DocumentDao = new DocumentDao();
		return DocumentDao.findDocumentById(docId);
	}

	public PageModel findDocumentsWithPage(int num, int sId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		DocumentDao DocumentDao = new DocumentDao();
		int totalRecords=DocumentDao.findTotalRecordsByStudent(sId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的资料信息
		//调用DAO层查询当前页中的资料信息 select * from t_doc limit  ? , ?
		List<Document> list=DocumentDao.findDocumentsWithPage(sId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("DocumentServlet?method=findDocumentsWithPage");
		return pm;
	}

	public PageModel findDocumentsWithPageByTeacher(int num, int tId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		DocumentDao DocumentDao = new DocumentDao();
		int totalRecords=DocumentDao.findTotalRecordsByTeacher(tId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的资料信息
		//调用DAO层查询当前页中的资料信息 select * from t_doc limit  ? , ?
		List<Document> list=DocumentDao.findDocumentsWithPageByTeacher(tId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("DocumentServlet?method=findDocumentsWithPageByTeacher");
		return pm;
	}

	public void deleteDocumentByTeacher(int docId) throws SQLException {
		DocumentDao DocumentDao = new DocumentDao();
		DocumentDao.deleteDocumentByTeacher(docId);
	}

	public void addDocument(Document document) throws SQLException {
		DocumentDao DocumentDao = new DocumentDao();
		DocumentDao.addDocument(document);		
	}

	public PageModel findDocumentsWithPageByCourse(int num, int cId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		DocumentDao DocumentDao = new DocumentDao();
		int totalRecords=DocumentDao.findTotalRecordsByCourse(cId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的资料信息
		//调用DAO层查询当前页中的资料信息 select * from t_doc limit  ? , ?
		List<Document> list=DocumentDao.findDocumentsWithPageByCourse(cId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("DocumentServlet?method=findDocumentsWithPageByCourse");
		return pm;
	}

}
