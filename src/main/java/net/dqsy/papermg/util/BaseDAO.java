package net.dqsy.papermg.util;

public abstract interface BaseDAO
{
  public abstract void save(Object paramObject)
    throws PaperManagerException;

  public abstract void update(Object paramObject)
    throws PaperManagerException;

  public abstract PagingSupport find(String paramString, int paramInt1, int paramInt2)
    throws PaperManagerException;

  public abstract Object findById(String paramString, int paramInt)
    throws PaperManagerException;

  public abstract PagingSupport findByProperty(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
    throws PaperManagerException;

  public abstract PagingSupport findByProperty(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
    throws PaperManagerException;

  public abstract PagingSupport findAll(String paramString, int paramInt1, int paramInt2)
    throws PaperManagerException;

  public abstract void delete(Object paramObject)
    throws PaperManagerException;
}