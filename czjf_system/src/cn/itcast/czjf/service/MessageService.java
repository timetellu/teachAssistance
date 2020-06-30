package cn.itcast.czjf.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.czjf.dao.MessageDao;
import cn.itcast.czjf.domain.Message;
import cn.itcast.czjf.domain.Student;
import cn.itcast.czjf.utils.PageModel;

public class MessageService {

	public PageModel findMessagesWithPage(int currentNum, Student stu) throws SQLException,NullPointerException {
		//1_创建PageModel对象，计算分页参数
		MessageDao MessageDao=new MessageDao();
		//select count(*) from t_message where stuId=?
		int totalRecords=MessageDao.findTotalRecordsByStuId(stu);
		PageModel pm=new PageModel(currentNum,totalRecords,5);
		
		//2_为PageModel设置集合
		List<Message> list=MessageDao.findMessagesWithPage(pm.getStartIndex(),pm.getPageSize(),stu);
		pm.setList(list);
		//3_为PageModel设置url
		pm.setUrl("MessageServlet?method=findMessagesWithPage");
		return pm;
	}

	public void addMessage(Message msg)  throws SQLException {
		MessageDao MessageDao=new MessageDao();
		MessageDao.addMessage(msg);
	}

	public PageModel findMessagesWithPageByTeacher(int currentPageNum)  throws SQLException {
		//1_创建PageModel对象，计算分页参数信息
		MessageDao MessageDao=new MessageDao();
		int totalRecords=MessageDao.findTotalRecords();
		PageModel pm=new PageModel(currentPageNum,totalRecords,5);
		//2_为PageModel设置集合(当前页中的留言信息)
		//select * from t_message limit ? , ?
		List<Message> list=MessageDao.findMessagesWithPageByTeacher(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_为PageModel设置url
		pm.setUrl("MessageServlet?method=findMessagesWithPageByTeacher");
		return pm;
	}

	public void replayMessage(String id, String replay) throws SQLException {
		//调用DAO层replayMessage,将id,replay传递给dao
		MessageDao MessageDao=new MessageDao();
		MessageDao.replayMessage(id,replay);
	}

	public Message findMessageById(int messageId) throws SQLException {
		MessageDao MessageDao=new MessageDao();
		return MessageDao.findMessageById(messageId);
		
	}

	public List<Message> findPrevMessage() throws SQLException {
		MessageDao MessageDao=new MessageDao();
		return MessageDao.findPrevMessage();
	}

}
