package net.dqsy.papermg.papermanager.action;

import net.dqsy.papermg.papermanager.po.PaperMidcheck;
import net.dqsy.papermg.papermanager.service.PaperMidcheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class PaperMidcheckAction {

    @Autowired
    private PaperMidcheckService paperMidcheckService;

    public boolean saveWithTitleId(int titleId, PaperMidcheck paperMidcheck) {
        return this.paperMidcheckService.saveWithTitleId(titleId, paperMidcheck);
    }

    public boolean updateWithTitleId(int titleId, PaperMidcheck paperMidcheck) {
        return this.paperMidcheckService.updateWithTitleId(titleId, paperMidcheck);
    }

    public List findByTitleId(int titleId) {
        return this.paperMidcheckService.findByTitleId(titleId);
    }
}