package net.dqsy.papermg.papermanager.service;

import net.dqsy.papermg.papermanager.po.PaperThesisProposal;
import net.dqsy.papermg.util.BaseService;

import java.util.List;

public abstract interface PaperThesisProposalService extends BaseService {
    public abstract boolean saveWithTitleId(int paramInt, PaperThesisProposal paramPaperThesisProposal);

    public abstract boolean updateWithTitleId(int paramInt, PaperThesisProposal paramPaperThesisProposal);

    public abstract List findByTitleId(int paramInt);
}