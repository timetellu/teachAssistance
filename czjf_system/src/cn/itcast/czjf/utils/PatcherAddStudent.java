package cn.itcast.czjf.utils;
//导入相关的包 mysql
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import cn.itcast.czjf.utils.JDBCUtils;
//导入操作Excel的包
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

//批量添加学生工具类
public class PatcherAddStudent{
	public static void readExcel(File path) throws BiffException, IOException, SQLException{
		//读取excel数据表
		Workbook workbook = Workbook.getWorkbook(path);
		Sheet[] sheets = workbook.getSheets();
		if(sheets!=null)
		{
			for(Sheet sheet:sheets)
			{
				//获取行数
				int rows = sheet.getRows();
				System.out.println(rows);
				//获取列数
				int cols = sheet.getColumns();
				System.out.println(cols);
				//读取数据
				for(int row = 1;row<rows;row++)  //z这里row从1开始是因为去除了表头占的一行
				{
					String values[] = new String[cols];
					for(int col=0;col<cols;col++)
					{
						//将每行不同列的内容放入数组
						values[col] = sheet.getCell(col,row).getContents();
					}
					//将读取出来的内容写入mysql数据库
					JDBCUtils.startTransaction();
					QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
					//1_添加学生
					String sql="INSERT INTO t_stu VALUES( null ,  ? ,  ? , ? ,  ? ,  ? , ? , ?)";
					qr.update(sql,values[0],values[1],values[2],values[3],values[4],100,"no");
					//2_往t_xuanke表中添加学生和课程的绑定关系
					String sql2="INSERT INTO t_xuanke VALUES(NULL,(SELECT cId FROM t_course WHERE cNum=?), (SELECT stuId FROM t_stu WHERE stuNum = ?))";
					Object[] params2= {values[5],values[0]};
					qr.update(sql2, params2);
					//3_ 提交事务
					JDBCUtils.commitAndClose();
				}
			}
		}
		workbook.close();
	}
}
