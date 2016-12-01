package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.util.PagingSupport;
import net.dqsy.papermg.util.PagingSupport;

public abstract interface PaperRoleService extends BaseService
{
  public abstract PagingSupport getAllRole();

  public abstract PagingSupport getAllPermission();
}