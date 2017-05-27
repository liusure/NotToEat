package com.notoeat.article.service;

import com.notoeat.article.dao.ArticleDao;
import com.notoeat.utils.PageResults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by l on 2017/4/23.
 */
@Service
@Transactional
public class ArticleServiceImpl<T, PK> implements ArticleService<T, PK> {
    @Resource
    private ArticleDao articleDao;

    @Override
    public void save(T o) {
        articleDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        articleDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) articleDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) articleDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        articleDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        articleDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        articleDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        articleDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) articleDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) articleDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return articleDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return articleDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return articleDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        articleDao.refresh(o);
    }

    @Override
    public void update(T o) {
        articleDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return articleDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
}
