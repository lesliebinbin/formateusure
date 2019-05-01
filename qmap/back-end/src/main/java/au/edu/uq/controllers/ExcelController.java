package au.edu.uq.controllers;

import au.edu.uq.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    private ExcelService service;


    @RequestMapping("/{excelName}")
    public String getExcelAsJson(@PathVariable("excelName") String excelName) {
        return service.getAllAsJson(excelName);
    }

    @RequestMapping("/{excelName}/{sheetName}")
    public String getSheetAsJson(@PathVariable("excelName") String excelName, @PathVariable("sheetName") String sheetName) {
        return service.getSpecificSheetAsJson(excelName, sheetName);
    }

}
