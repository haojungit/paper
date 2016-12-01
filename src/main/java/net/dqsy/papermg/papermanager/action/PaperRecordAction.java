package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperRecord;
import net.dqsy.papermg.papermanager.service.PaperRecordService;
import net.dqsy.papermg.papermanager.po.PaperRecord;

public class PaperRecordAction
{
  PaperRecordService paperRecordService;

  public void setPaperRecordService(PaperRecordService paperRecordService)
  {
    this.paperRecordService = paperRecordService;
  }

  public int writeRecord(int titleId, PaperRecord record)
  {
    try {
      return this.paperRecordService.writeRecord(titleId, record) ? 1 : 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
}