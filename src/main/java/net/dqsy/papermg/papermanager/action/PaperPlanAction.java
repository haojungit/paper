package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.service.PaperPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class PaperPlanAction {
    @Autowired
    private PaperPlanService paperPlanService;

    public List findByWritingTaskID(int writingTaskID) {
        return this.paperPlanService.findByWritingTaskID(writingTaskID);
    }
}