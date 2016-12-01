package net.dqsy.papermg.sysmanager.util;

import net.dqsy.papermg.sysmanager.po.PaperStudent;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class POIReadExcel_student
{
  private Workbook wb;
  private Sheet sheet;
  private Row row;
  private Cell cell;

  public List<PaperStudent> readExcel(String excelFilePath)
    throws ParseException
  {
    List list = new ArrayList();
    try
    {
      InputStream stream = new FileInputStream(new File(excelFilePath));
      this.wb = WorkbookFactory.create(stream);

      this.sheet = this.wb.getSheetAt(0);

      int rows = this.sheet.getLastRowNum();
      int cells = 0;
      int flag = 0;

      for (int i = 0; i < rows; i++)
      {
        flag = 0;
        this.row = this.sheet.getRow(i + 1);
        PaperStudent paperStudent = new PaperStudent();

        cells = this.sheet.getRow(i + 1).getPhysicalNumberOfCells();

        for (int j = 0; j < cells; j++)
        {
          this.cell = this.row.getCell(j);
          Object obj = POIReadExcel_getValue.getCellValue(this.cell);
          switch (j) {
          case 0:
            paperStudent.setStudentNumber((String)obj);
            flag++;
            break;
          case 1:
            paperStudent.setStudentName((String)obj);
            flag++;
            break;
          case 2:
            paperStudent.setStudentSex((String)obj);
            break;
          case 3:
            paperStudent.setStudentAge(Integer.valueOf(((Double)obj).intValue()));
            break;
          case 4:
            paperStudent.setStudentPhone((String)obj);
            break;
          case 5:
            paperStudent.setStudentGrade((String)obj);
            break;
          case 6:
            paperStudent.setStudentFaculty((String)obj);
            break;
          case 7:
            paperStudent.setStudentMajor((String)obj);
            break;
          case 8:
            paperStudent.setStudentDirection((String)obj);
          }

        }

        if (flag == 2)
          list.add(paperStudent);
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