package com.zjut.ida.tool;


import javafx.scene.control.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Veloma on 2020/11/7.
 */
public class ExcelFormatUtil {

    public static List<List<Object>> getBankListByExcel() throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();     //读取的数据放入该集合中
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("/Users/zhouweiyue/Downloads/教师表3.xlsx");
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            List<Object> list1 = new ArrayList<>();
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j <= row.getLastCellNum(); j=j+2) {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        String value = cell.getStringCellValue();
                        if (value != null && !"".equals(value)) ;
                        list1.add(value);
                    }
                }
                if (list1.size() > 0)
                    list.add(list1);
            }
        }
        return list;
    }
}
