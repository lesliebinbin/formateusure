package au.edu.uq.services.impl;

import au.edu.uq.configs.ClojureConfig;
import au.edu.uq.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private ClojureConfig clojureConfig;

    @Override
    public String getAllAsJson(String excelName) {

        return (String) clojureConfig
                .getClojureFunc("au.edu.uq.utils.excel-reader", "read-excel")
                .invoke(excelName + ".xlsx");
    }

    @Override
    public String getSpecificSheetAsJson(String excelName, String sheetName) {
        return (String) clojureConfig
                .getClojureFunc("au.edu.uq.utils.excel-reader", "read-excel")
                .invoke(excelName + ".xlsx", sheetName);
    }
}
