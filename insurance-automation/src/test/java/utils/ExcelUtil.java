package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExcelUtil - Reads test data from Excel files for data-driven testing
 * Supports .xlsx format using Apache POI
 */
public class ExcelUtil {

    /**
     * Reads all rows from a given sheet and returns list of key-value maps
     * First row is treated as the header/key row
     */
    public static List<Map<String, String>> getTestData(String filePath, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    String key = getCellValue(headerRow.getCell(j));
                    String value = getCellValue(row.getCell(j));
                    rowData.put(key, value);
                }
                testData.add(rowData);
            }

        } catch (IOException e) {
            LoggerUtil.error("Failed to read Excel file: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return testData;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:  return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default:      return "";
        }
    }
}
