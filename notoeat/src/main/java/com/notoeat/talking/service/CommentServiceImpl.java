package com.notoeat.talking.service;

import com.notoeat.talking.dao.CommentDao;
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
public class CommentServiceImpl<T, PK> implements CommentService<T, PK> {
    @Resource
    private CommentDao commentDao;

    @Override
    public void save(T o) {
        commentDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        commentDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) commentDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) commentDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        commentDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        commentDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        commentDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        commentDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) commentDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) commentDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return commentDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return commentDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return commentDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        commentDao.refresh(o);
    }

    @Override
    public void update(T o) {
        commentDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return commentDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
}
