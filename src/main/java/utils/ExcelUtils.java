package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {
    private static Workbook workbook;

    public static Workbook setExcel(String excelFilePath) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            workbook = getWorkbook(inputStream, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static void closeExcel() {
        try {
            if (workbook != null) workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        if (excelFilePath.endsWith("xlsx")) return new XSSFWorkbook(inputStream);
        if (excelFilePath.endsWith("xls")) return new HSSFWorkbook(inputStream);
        throw new IllegalArgumentException("Invalid Excel file type");
    }

    public static Map<String, List<String>> getData(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        List<String> headers = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            headers.add(cell != null ? cell.getStringCellValue() : "");
        }

        Map<String, List<String>> data = new HashMap<>();
        for (int col = 0; col < headers.size(); col++) {
            List<String> columnData = new ArrayList<>();
            for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                Row currentRow = sheet.getRow(row);
                Cell cell = currentRow != null ? currentRow.getCell(col) : null;
                columnData.add(new DataFormatter().formatCellValue(cell));
            }
            data.put(headers.get(col), columnData);
        }
        return data;
    }

    public static String getCellValueByRowAndColumnName(String filePath, String sheetName, String rowName, String columnName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            Map<String, Integer> columnMap = new HashMap<>();
            for (Cell cell : headerRow) {
                columnMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }

            int rowNameColIndex = 0;
            int targetColIndex = columnMap.getOrDefault(columnName, -1);
            if (targetColIndex == -1) return null;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Cell rowNameCell = row.getCell(rowNameColIndex);
                if (rowNameCell != null && rowName.equals(rowNameCell.getStringCellValue())) {
                    Cell targetCell = row.getCell(targetColIndex);
                    return new DataFormatter().formatCellValue(targetCell);
                }
            }
        }
        return null;
    }

    public static List<String> getDataList(String sheetName, String columnName) {
        try {
            Map<String, List<String>> data = getData(sheetName);
            return data.getOrDefault(columnName, Collections.emptyList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}