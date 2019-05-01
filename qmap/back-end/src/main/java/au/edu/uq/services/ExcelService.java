package au.edu.uq.services;

public interface ExcelService {
    String getAllAsJson(String excelName);

    String getSpecificSheetAsJson(String excelName, String sheetName);
}
