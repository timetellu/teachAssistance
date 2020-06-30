package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.DocumentDao;
import cn.itcast.czjf.dao.VedioDao;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.domain.Vedio;
import cn.itcast.czjf.utils.PageModel;

public class VedioService {

	public List<Vedio> findPrevVedio() throws SQLException {
		//调用DAO层功能，返回一个存储着Vedio对象的集合
		VedioDao VedioDao=new VedioDao();
		return VedioDao.findPrevVedio();
		
	}

	public PageModel findVedioWithPage(int num, int sId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		VedioDao VedioDao=new VedioDao();
		int totalRecords=VedioDao.findTotalRecordsByStudent(sId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的视频信息
		//调用DAO层查询当前页中的视频信息 select * from tb_vedio limit  ? , ?
		List<Vedio> list=VedioDao.findVedioWithPage(sId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("VedioServlet?method=findVedioWithPage");
		return pm;
	}

	public Vedio findVedioByVid(String vId) throws SQLException {
		//调用DAO层根据视频ID获取视频对象
		VedioDao VedioDao=new VedioDao();
		return VedioDao.findVedioByVid(vId);
		
	}


	public PageModel findVediosWithPageByTeacher(int currentPageNum, int tId) throws SQLException {
		//1_创建PageModel对象，计算分页参数
		VedioDao VedioDao=new VedioDao();
		int totalRecords=VedioDao.findTotalRecordsByteacher(tId);
		PageModel pm=new PageModel(currentPageNum, totalRecords, 5);
		//2_为PageModel对象设置集合(当前页中的视频信息)
		//select * from t_vedio limit ? , ?
		List<Vedio> list=VedioDao.findVediosWithPageByTeacher(tId,(currentPageNum-1)*5,5);
		pm.setList(list);
		//3_为PageModel对象设置url (PageFile.jsp页面所需)
		pm.setUrl("VedioServlet?method=findVediosWithPageByTeacher");
		return pm;
	}

	public void deleteVedioByTeacher(String vId) throws SQLException {
		//调用DAO层，根据视频编号删除视频功能
		VedioDao VedioDao=new VedioDao();
		VedioDao.deleteVedioByTeacher(vId);
	}

	public void addVedio(Vedio vedio) throws SQLException {
		VedioDao VedioDao=new VedioDao();
		VedioDao.addVedio(vedio);
	}

	public PageModel findVedioWithPageByCourse(int num, int cId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		VedioDao VedioDao=new VedioDao();
		int totalRecords=VedioDao.findTotalRecordsByCourse(cId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的视频信息
		//调用DAO层查询当前页中的视频信息 select * from tb_vedio limit  ? , ?
		List<Vedio> list=VedioDao.findVedioWithPageByCourse(cId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("VedioServlet?method=findVedioWithPageByCourse");
		return pm;
	}

	

}
