package net.dqsy.papermg.util;

public abstract interface BaseService {
    public abstract boolean save(Object paramObject);

    public abstract boolean update(Object paramObject);

    public abstract PagingSupport find(String paramString, int paramInt1, int paramInt2);

    public abstract Object findById(int paramInt);

    public abstract PagingSupport findByProperty(String paramString1, String paramString2, int paramInt1, int paramInt2);

    public abstract PagingSupport findByProperty(String paramString, int paramInt1, int paramInt2, int paramInt3);

    public abstract PagingSupport findAll(int paramInt1, int paramInt2);

    public abstract boolean del(Object paramObject);
}