package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PagingSupport;

public abstract interface PaperUserRoleService extends BaseService
{
  public abstract PagingSupport getUserRole(int paramInt);
}