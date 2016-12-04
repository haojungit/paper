package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperThesisProposal;
import net.dqsy.papermg.papermanager.service.PaperThesisProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class PaperThesisProposalAction {

    @Autowired
    private PaperThesisProposalService paperThesisProposalService;

    public boolean saveWithTitleId(int titleId, PaperThesisProposal paperThesisProposal) {
        return this.paperThesisProposalService.saveWithTitleId(titleId,
                paperThesisProposal);
    }

    public boolean updateWithTitleId(int titleId, PaperThesisProposal paperThesisProposal) {
        return this.paperThesisProposalService.updateWithTitleId(titleId,
                paperThesisProposal);
    }

    public List findByTitleId(int titleId) {
        return this.paperThesisProposalService.findByTitleId(titleId);
    }
}