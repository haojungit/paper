package net.dqsy.papermg.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {
    Logger logger = Logger.getLogger(getClass().getName());
    private int totalCount;


    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public PagingSupport queryForPage(final String hql, final int numberOfPage, final int length) {
        List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                BaseDAOImpl.this.totalCount = query.list().size();
                query.setFirstResult((numberOfPage - 1) * length);
                query.setMaxResults(length);
                return query.list();
            }
        });


        return new PagingSupport(list, numberOfPage, this.totalCount, length);
    }

    public void save(Object o) throws PaperManagerException {
        getHibernateTemplate().save(o);

        this.logger.info(getHibernateTemplate().getSessionFactory().getCurrentSession().getStatistics());
    }

    public void update(Object o) throws PaperManagerException {
        getHibernateTemplate().update(o);
        getHibernateTemplate().flush();
        this.logger.info(getHibernateTemplate().getSessionFactory().getCurrentSession().getStatistics());
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) throws PaperManagerException {
        return queryForPage(hql, numberOfPage, countOfPage);
    }

    public Object findById(String classs, int id) throws PaperManagerException {
        return getHibernateTemplate().get(classs, Integer.valueOf(id));
    }

    public PagingSupport findByProperty(String table, String property, String value, int numberOfPage, int countOfPage)
            throws PaperManagerException {
        return queryForPage("from " + table + " where " + property + " = '" + value + "'", numberOfPage, countOfPage);
    }

    public PagingSupport findAll(String table, int numberOfPage, int countOfPage) throws PaperManagerException {
        return queryForPage("from " + table, numberOfPage, countOfPage);
    }

    public void delete(Object o) throws PaperManagerException {
        getHibernateTemplate().delete(o);

        this.logger.info(getHibernateTemplate().getSessionFactory().getCurrentSession().getStatistics());
    }

    public PagingSupport findByProperty(String table, String property, int value, int numberOfPage, int countOfPage)
            throws PaperManagerException {
        return queryForPage("from " + table + " where " + property + " = " + value, numberOfPage, countOfPage);
    }
}