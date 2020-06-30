package cn.itcast.czjf.dao;

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


//excel 读取类
class excelRead{
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
					String sql="INSERT INTO t_person VALUES( ? ,  ? ,  ? )";
					QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
					qr.update(sql,null,values[1],values[2]);
				}
			}
		}
		workbook.close();
	}
}

public class TestPatchAdd {
	public static void main(String[] args) throws BiffException, IOException, SQLException{
		File path = new File("C:\\Users\\Timetellu\\Desktop\\test.xls");
		excelRead.readExcel(path);  // 传入要操作的excel路径 
	}
}
