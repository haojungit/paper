package net.dqsy.papermg.sysmanager.util;

import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class POIReadExcel_teacher {
    private Workbook wb;
    private Sheet sheet;
    private Row row;
    private Cell cell;

    public List<PaperTeacher> readExcel(String excelFilePath)
            throws ParseException {
        List list = new ArrayList();

        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(excelFilePath));
            this.wb = WorkbookFactory.create(stream);

            this.sheet = this.wb.getSheetAt(0);

            int rows = this.sheet.getLastRowNum();
            int cells = 0;
            int flag = 0;

            for (int i = 0; i < rows; i++) {
                flag = 0;

                this.row = this.sheet.getRow(i + 1);
                PaperTeacher paperTeacher = new PaperTeacher();

                cells = this.sheet.getRow(i + 1).getPhysicalNumberOfCells();

                for (int j = 0; j < cells; j++) {
                    this.cell = this.row.getCell(j);
                    Object obj = POIReadExcel_getValue.getCellValue(this.cell);
                    switch (j) {
                        case 0:
                            paperTeacher.setTeacherNumber((String) obj);
                            flag++;
                            break;
                        case 1:
                            paperTeacher.setTeacherName((String) obj);
                            flag++;
                            break;
                        case 2:
                            paperTeacher.setTeacherSex((String) obj);
                            break;
                        case 3:
                            paperTeacher.setTeacherAge(Integer.valueOf(((Double) obj).intValue()));
                            break;
                        case 4:
                            paperTeacher.setTeacherPhone((String) obj);
                            break;
                        case 5:
                            paperTeacher.setTeacherUnits((String) obj);
                            break;
                        case 6:
                            paperTeacher.setTeacherMajor((String) obj);
                            break;
                        case 7:
                            paperTeacher.setTeacherEducation((String) obj);
                            break;
                        case 8:
                            paperTeacher.setTeacherJobTitle((String) obj);
                            break;
                        case 9:
                            paperTeacher.setTeacherDirection((String) obj);
                    }

                }

                if (flag == 2)
                    list.add(paperTeacher);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}