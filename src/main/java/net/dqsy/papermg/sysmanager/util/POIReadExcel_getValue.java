package net.dqsy.papermg.sysmanager.util;

import org.apache.poi.ss.usermodel.Cell;

public class POIReadExcel_getValue {
    public static Object getCellValue(Cell cell) {
        if (cell.getCellType() == 4)
            return String.valueOf(cell.getBooleanCellValue());
        if (cell.getCellType() == 0) {
            return Double.valueOf(cell.getNumericCellValue());
        }

        return cell.getStringCellValue();
    }
}