package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.util.BaseService;

import java.util.List;

public abstract interface PaperUserService extends BaseService {
    public abstract List login(String paramString1, String paramString2);

    public abstract String updatePassword(String paramString1, String paramString2, String paramString3);
}