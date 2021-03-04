package com.yestae.user.manage.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtil {
	
	
	private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
      
    /** 
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public List<List<String>> getListByExcel(InputStream in, String fileName) throws Exception{  
        List<List<String>> list = null;  
          
        //创建Excel工作薄  
        Workbook work = getWorkbook(in, fileName);  
        if(null == work){  
            throw new Exception("创建Excel工作薄为空！");  
        }  
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  
          
        list = new ArrayList<List<String>>();  
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}  
              
            //遍历当前sheet中的所有行  
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {  
                row = sheet.getRow(j);  
                if(row==null||row.getFirstCellNum()==j){continue;}  
                  
                //遍历所有的列  
                List<String> li = new ArrayList<String>();  
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                    cell = row.getCell(y);  
                    li.add(getCellValue(cell) == null? "": getCellValue(cell).toString());  
                }  
                list.add(li);  
            }  
        }  
        in.close();
        return list;  
    }  
      
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(excel2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  
  
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public Object getCellValue(Cell cell){  
        Object value = null;  
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
          
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }  
	
	
	/**
	 * 
	 * @param response
	 * @param cellList 从数据库查出的集合
	 * @param strArray 列名称
	 * @param cellMap 存放对应strArray列序号及cellList字段名
	 * @param fname 文件名
	 * @param sheetName Sheet页名
	 */
	public void exportExcel(HttpServletResponse response, List<Map<String, Object>> cellList, String[] strArray, Map<Short, String> cellMap, String fname, String sheetName)
	{
		ServletOutputStream out = null;
		response.setContentType("application/binary;charset=ISO8859_1");  
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			out = response.getOutputStream(); 
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat df1=new SimpleDateFormat("yyyy年MM月dd日_HH_mm_ss");
			fname = fname == null? "": fname;
			String fileName=new String((fname+df1.format(cal.getTime())+".xls").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);// 组装附件名称和格式  
			
			short rownum;
			
			HSSFSheet s = wb.createSheet();
			
			HSSFRow r = null;
			
			HSSFCell c = null;
			
			HSSFCellStyle cs1 = wb.createCellStyle();
			HSSFCellStyle cs2 = wb.createCellStyle();
			
			//设置标题字体
			HSSFFont f1 = wb.createFont();
			
			//设置内容字体
			HSSFFont f2 = wb.createFont();

			f1.setFontHeightInPoints((short) 12);
			f1.setColor( (short)0 );
			f1.setBold(true);

			f2.setFontHeightInPoints((short) 10);
			f2.setColor( (short)0 );
			f2.setBold(true);

			//设置标题样式
			cs1.setFont(f1);
			cs1.setBorderBottom(BorderStyle.THIN);
			cs1.setBorderLeft(BorderStyle.THIN);
			cs1.setBorderRight(BorderStyle.THIN);
			cs1.setBorderTop(BorderStyle.THIN);
			cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs1.setVerticalAlignment(VerticalAlignment.CENTER);
			cs1.setAlignment(HorizontalAlignment.CENTER);  
			cs1.setFillBackgroundColor((short)0x09);
			cs1.setFillForegroundColor((short)0x09);

			//设置内容样式
			cs2.setFont(f2);
			cs2.setBorderBottom(BorderStyle.THIN);
			cs2.setBorderLeft(BorderStyle.THIN);
			cs2.setBorderRight(BorderStyle.THIN);
			cs2.setBorderTop(BorderStyle.THIN);
			cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs2.setVerticalAlignment(VerticalAlignment.CENTER);
			cs2.setAlignment(HorizontalAlignment.CENTER);  
			cs2.setFillBackgroundColor((short)0x09);
			cs2.setFillForegroundColor((short)0x09);

			if(!StringUtils.isEmpty(sheetName)){
				wb.setSheetName(0, sheetName);
			}else{
				wb.setSheetName(0, "Sheet1");
			}
			
			
			//第一行，标题
			r = s.createRow(0);
			r.setHeight((short)600);
			for (short cellnum = (short) 0; cellnum < strArray.length; cellnum ++){
				c = r.createCell(cellnum);
				c.setCellValue(strArray[cellnum]);
				s.setColumnWidth(cellnum, 5000);
				c.setCellStyle(cs1);
			}

			//内容填充
			for (rownum = (short) 1; rownum <= cellList.size(); rownum++)
			{
				Map<String,Object> map = cellList.get(rownum-1);
				r = s.createRow(rownum);
				r.setHeight((short)500);
				for (short cellnum = (short) 0; cellnum < strArray.length; cellnum ++)
				{
					c = r.createCell(cellnum);
					String key = cellMap.get(cellnum);
					c.setCellValue(map.get(key)!=null?map.get(key).toString():"");
						c.setCellStyle(cs2);
				}
			}
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
