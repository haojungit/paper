package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperThesisProposal;
import net.dqsy.papermg.papermanager.service.PaperThesisProposalService;
import net.dqsy.papermg.papermanager.po.PaperThesisProposal;
import net.dqsy.papermg.papermanager.service.PaperThesisProposalService;

import java.util.List;

public class PaperThesisProposalAction
{
  private PaperThesisProposalService paperThesisProposalService;

  public boolean saveWithTitleId(int titleId, PaperThesisProposal paperThesisProposal)
  {
    return this.paperThesisProposalService.saveWithTitleId(titleId, 
      paperThesisProposal);
  }

  public boolean updateWithTitleId(int titleId, PaperThesisProposal paperThesisProposal)
  {
    return this.paperThesisProposalService.updateWithTitleId(titleId, 
      paperThesisProposal);
  }

  public List findByTitleId(int titleId)
  {
    return this.paperThesisProposalService.findByTitleId(titleId);
  }

  public void setPaperThesisProposalService(PaperThesisProposalService paperThesisProposalService)
  {
    this.paperThesisProposalService = paperThesisProposalService;
  }
}