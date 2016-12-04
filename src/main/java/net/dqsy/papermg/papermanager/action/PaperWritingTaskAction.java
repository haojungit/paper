package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperPlan;
import net.dqsy.papermg.papermanager.po.PaperWritingTask;
import net.dqsy.papermg.papermanager.service.PaperWritingTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class PaperWritingTaskAction {

    @Autowired
    private PaperWritingTaskService paperWritingTaskService;

    public boolean saveWritingTask(int titleId, PaperWritingTask paperWritingTask, List<PaperPlan> list) {
        return this.paperWritingTaskService.saveWritingTask(titleId,
                paperWritingTask, list);
    }

    public List findByTieleId(int titleId) {
        return this.paperWritingTaskService.findByTieleId(titleId);
    }
}