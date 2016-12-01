package net.dqsy.papermg.sysmanager.service;

import net.dqsy.papermg.util.BaseService;
import net.dqsy.papermg.util.PagingSupport;

public abstract interface PaperRolePermissionService extends BaseService
{
  public abstract PagingSupport getRolePermission(int paramInt);
}