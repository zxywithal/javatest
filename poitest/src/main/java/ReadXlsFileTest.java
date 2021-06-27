import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.StringJoiner;

public class ReadXlsFileTest {
    public static void main(String[] args) throws Exception {
        Workbook wb = WorkbookFactory.create(new File("fieldList for MOTO migration.xlsx"));
        for (Sheet sheet : wb) {
            System.out.println("sheet name is ["+sheet.getSheetName()+"] ");

            for (Row row : sheet) {
                StringJoiner sj = new StringJoiner(":");
                sj.add(" row number is ["+row.getRowNum()+"] ");
                for (Cell cell : row) {
                    StringJoiner cellValue = new StringJoiner("","[","]");
                    if (cell.getCellType().equals(CellType.STRING)) {
                        cellValue.add(cell.getStringCellValue());
                    } else if (cell.getCellType().equals(CellType.NUMERIC)) {
                        cellValue.add(String.valueOf(cell.getNumericCellValue()));
                    } else if (cell.getCellType().equals(CellType._NONE)) {
                        cellValue.add("blank");
                    }
                    sj.add("cell address is ["+cell.getAddress().toString()+"] value is "+cellValue);
                }
                System.out.println(sj);
            }
        }
        wb.close();

    }
}
