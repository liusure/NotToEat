package com.notoeat.user.service;

import com.notoeat.user.dao.GroupDao;
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
public class GroupServiceImpl<T, PK> implements GroupService<T, PK> {
    @Resource
    private GroupDao groupDao;

    @Override
    public void save(T o) {
        groupDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        groupDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) groupDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) groupDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        groupDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        groupDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        groupDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        groupDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) groupDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) groupDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return groupDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return groupDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return groupDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        groupDao.refresh(o);
    }

    @Override
    public void update(T o) {
        groupDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return groupDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
}
