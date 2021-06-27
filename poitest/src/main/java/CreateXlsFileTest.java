import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Calendar;

public class CreateXlsFileTest {
    public static void main(String[] args) throws Exception {
        //07版本之前的xls文件
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        //创建sheet
        Sheet sheet1 = wb.createSheet("new sheet");
        //创建row
        Row row = sheet1.createRow(0);
        //创建cell
        row.createCell(0).setCellValue(0);
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("this is String");
        RichTextString richTextString = creationHelper.createRichTextString("this is rich text string");
        row.createCell(3).setCellValue(richTextString);
        row.createCell(4).setCellValue(true);
        //没啥鸟用
        row.createCell(5).setCellType(CellType.ERROR);

        Sheet sheet2 = wb.createSheet("second sheet");
        Row sheet2Row1 = sheet2.createRow(0);
        Cell sheet2Row1Cell1 = sheet2Row1.createCell(0);
        sheet2Row1Cell1.setCellValue(LocalDateTime.now());
        CellStyle cellStyle = wb.createCellStyle();
        //这个日期格式没起啥作用
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        sheet2Row1Cell1.setCellStyle(cellStyle);

        Cell sheet2Row1Cell2 = sheet2Row1.createCell(1);
        sheet2Row1Cell2.setCellValue(Calendar.getInstance());
        sheet2Row1Cell2.setCellStyle(cellStyle);

        String safeSheetName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?       ]");
        Sheet sheet3 = wb.createSheet(safeSheetName);

        try(FileOutputStream fileOutputStream = new FileOutputStream("testexcel.xls")){
            wb.write(fileOutputStream);
        }

        Workbook wb2 = new XSSFWorkbook();
        try(FileOutputStream fileOutputStream = new FileOutputStream("testexcel.xlsx")){
            wb2.write(fileOutputStream);
        }


    }
}
