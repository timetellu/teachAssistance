package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.CourseDao;
import cn.itcast.czjf.dao.DocumentDao;
import cn.itcast.czjf.domain.Course;
import cn.itcast.czjf.domain.Document;
import cn.itcast.czjf.utils.PageModel;

public class CourseService {

	public PageModel findCoursesWithPage(int currentNum) throws SQLException {
		//1_创建PageModel对象,计算分页参数
		CourseDao CourseDao = new CourseDao() ;
		int totalRecords=CourseDao.findTotalRecords();
		PageModel pm=new PageModel(currentNum,totalRecords,5);
		//2_为PageModel对象设置集合(当前页中的学生信息)
		//select * from t_stu limit ? , ?
		List<Course> list=CourseDao.findCoursesWithPage((currentNum-1)*5,5);
		pm.setList(list);
		//3_为PageModel对象设置url
		pm.setUrl("CourseServlet?method=findCoursesWithPage");
		return pm;
	}

	public void delCourseById(String id) throws SQLException {
		CourseDao CourseDao = new CourseDao() ;
		CourseDao.delCourseById(id);
	}

	public void addCourse(Course course) throws SQLException {
		CourseDao CourseDao = new CourseDao() ;
		CourseDao.addCourse(course);
	}

	public Course validateCourseExist(String cNum) throws SQLException {
		CourseDao CourseDao = new CourseDao() ;
		return CourseDao.validateCourseExist(cNum);
	}

	public Course validateCourseExistByteacher(String cNum, int teaId) throws SQLException {
		CourseDao CourseDao = new CourseDao() ;
		return CourseDao.validateCourseExistByteacher(cNum,teaId);
	}

	public PageModel findCoursesWithPageByStudent(int num, int sId) throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		CourseDao CourseDao = new CourseDao() ;
		int totalRecords=CourseDao.findTotalRecordsByStudent(sId); 
		PageModel pm=new PageModel(num,totalRecords,5);
		//2_为PageModel关联集合 集合中存放的就是当前页中的资料信息
		//调用DAO层查询当前页中的资料信息 select * from t_doc limit  ? , ?
		List<Course> list=CourseDao.findCoursesWithPageByStudent(sId,(num-1)*5,5);
		pm.setList(list);
		//3.为PageModel关联url属性
		pm.setUrl("CourseServlet?method=findCoursesWithPageByStudent");
		return pm;
	}

	public Course findCourseById(int cId) throws SQLException {
		CourseDao CourseDao = new CourseDao() ;
		return CourseDao.findCourseById(cId);
	}


}
