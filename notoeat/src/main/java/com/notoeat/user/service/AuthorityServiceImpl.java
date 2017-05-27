package com.notoeat.user.service;

import com.notoeat.user.dao.AuthorityDao;
import com.notoeat.utils.PageResults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by l on 2017/4/24.
 */
@Service
@Transactional
public class AuthorityServiceImpl<T, PK> implements AuthorityService<T, PK> {
    @Resource
    private AuthorityDao authorityDao;

    @Override
    public void save(T o) {
        authorityDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        authorityDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) authorityDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) authorityDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        authorityDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        authorityDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        authorityDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        authorityDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) authorityDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) authorityDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return authorityDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return authorityDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return authorityDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        authorityDao.refresh(o);
    }

    @Override
    public void update(T o) {
        authorityDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return authorityDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
}
