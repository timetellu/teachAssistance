package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.DemoDao;
import cn.itcast.czjf.dao.DocumentDao;
import cn.itcast.czjf.domain.Demo;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.utils.PageModel;

public class DemoService {

	public void addDemo(Demo demo) throws SQLException {
		DemoDao DemoDao = new DemoDao();
		DemoDao.addDemo(demo);		
		
	}

	public PageModel findDemosWithPageByTeacher(int num, int tId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		DemoDao DemoDao = new DemoDao();
		int totalRecords=DemoDao.findTotalRecordsByTeacher(tId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的资料信息
		//调用DAO层查询当前页中的资料信息 select * from t_doc limit  ? , ?
		List<Demo> list=DemoDao.findDemosWithPageByTeacher(tId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("DemoServlet?method=findDemosWithPageByTeacher");
		return pm;
	}

	public void deleteDemoByTeacher(int demoId) throws SQLException {
		DemoDao DemoDao = new DemoDao();
		DemoDao.deleteDemoByTeacher(demoId);
	}

	public Demo findDemoById(int demoId) throws SQLException {
		DemoDao DemoDao = new DemoDao();
		return DemoDao.findDemoById(demoId);
	}

	public PageModel findDemosWithPageByCourse(int num, int cId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		DemoDao DemoDao = new DemoDao();
		int totalRecords=DemoDao.findTotalRecordsByCourse(cId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的资料信息
		//调用DAO层查询当前页中的资料信息 select * from t_doc limit  ? , ?
		List<Demo> list=DemoDao.findDemosWithPageByCourse(cId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("DemoServlet?method=findDemosWithPageByCourse");
		return pm;
	}

	public List<Demo> findDemoExerciseByCourse(int num, int cId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		DemoDao DemoDao = new DemoDao();
		List<Demo> list=DemoDao.findDemoExerciseByCourse(cId);
		return list;
	}


	public Demo findDemoRecordsByCourse(int cId) throws SQLException {
		DemoDao DemoDao = new DemoDao();
		return DemoDao.findDemoRecordsByCourse(cId); 
	}

}
