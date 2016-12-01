package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.util.BaseService;

public abstract interface PaperPermissionService extends BaseService
{
  public abstract boolean delsoftOrRenew(int paramInt);
}