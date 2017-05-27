package com.notoeat.media.service;

import com.notoeat.media.dao.MediaDao;
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
public class MediaServiceImpl<T, PK> implements MediaService<T, PK> {
    @Resource
    private MediaDao mediaDao;

    @Override
    public void save(T o) {
        mediaDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        mediaDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) mediaDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) mediaDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        mediaDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        mediaDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        mediaDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        mediaDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) mediaDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) mediaDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return mediaDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return mediaDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return mediaDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        mediaDao.refresh(o);
    }

    @Override
    public void update(T o) {
        mediaDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return mediaDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
}
