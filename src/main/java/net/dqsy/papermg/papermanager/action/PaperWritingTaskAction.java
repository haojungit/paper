package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperPlan;
import net.dqsy.papermg.papermanager.po.PaperWritingTask;
import net.dqsy.papermg.papermanager.service.PaperWritingTaskService;
import net.dqsy.papermg.papermanager.service.PaperWritingTaskService;

import java.util.List;

public class PaperWritingTaskAction {
    private PaperWritingTaskService paperWritingTaskService;

    public boolean saveWritingTask(int titleId, PaperWritingTask paperWritingTask, List<PaperPlan> list) {
        return this.paperWritingTaskService.saveWritingTask(titleId,
                paperWritingTask, list);
    }

    public List findByTieleId(int titleId) {
        return this.paperWritingTaskService.findByTieleId(titleId);
    }

    public void setPaperWritingTaskService(PaperWritingTaskService paperWritingTaskService) {
        this.paperWritingTaskService = paperWritingTaskService;
    }
}