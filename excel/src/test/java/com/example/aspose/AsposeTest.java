package com.example.aspose;


import com.aspose.cells.*;
import org.junit.jupiter.api.Test;

public class AsposeTest {

    @Test
    public void testWorkbook() throws Exception {
        Workbook workbook = new Workbook();
        WorksheetCollection worksheets = workbook.getWorksheets();
        worksheets.add("Sheet123");
        worksheets.add("Sheet234");
        Worksheet sheet123 = worksheets.get("Sheet123");
        Cells cells = sheet123.getCells();
        Style style = new Style();
        style.getFont().setBold(true);
        style.setBackgroundColor(Color.getPurple());
        cells.setStyle(style);
        Cell a1 = cells.get("A1");
        a1.setStyle(style);
        a1.setValue("Hello World");
        workbook.save("Template.xlsx");
    }
}
