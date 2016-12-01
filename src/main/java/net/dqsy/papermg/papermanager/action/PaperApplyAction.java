package net.dqsy.papermg.papermanager.action;

import java.util.List;

import net.dqsy.papermg.papermanager.po.PaperApply;
import net.dqsy.papermg.papermanager.service.PaperApplyService;
import net.dqsy.papermg.papermanager.po.PaperApply;
import net.dqsy.papermg.papermanager.service.PaperApplyService;

public class PaperApplyAction {
	private PaperApplyService paperApplyService;

	public boolean saveWithTitleId(int titleId, PaperApply paperApply) {
		return this.paperApplyService.saveWithTitleId(titleId, paperApply);
	}

	public boolean updateWithTitleId(int titleId, PaperApply paperApply) {
		System.out.println("123131231313\n\n\n\n\n\n");
		return this.paperApplyService.updateWithTitleId(titleId, paperApply);
	}

	public List findByTitleId(int titleId) {
		return this.paperApplyService.findByTitleId(titleId);
	}

	public void setPaperApplyService(PaperApplyService paperApplyService) {
		this.paperApplyService = paperApplyService;
	}
}