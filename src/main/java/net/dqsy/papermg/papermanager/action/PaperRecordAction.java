package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperRecord;
import net.dqsy.papermg.papermanager.service.PaperRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class PaperRecordAction {

    @Autowired
    PaperRecordService paperRecordService;

    public int writeRecord(int titleId, PaperRecord record) {
        try {
            return this.paperRecordService.writeRecord(titleId, record) ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}