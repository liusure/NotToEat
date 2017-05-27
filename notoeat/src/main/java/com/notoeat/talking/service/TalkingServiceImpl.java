package com.notoeat.talking.service;

import com.notoeat.talking.dao.TalkingDao;
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
public class TalkingServiceImpl<T, PK> implements TalkingService<T, PK> {
    @Resource
    private TalkingDao talkingDao;

    @Override
    public void save(T o) {
        talkingDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        talkingDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) talkingDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) talkingDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        talkingDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        talkingDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        talkingDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        talkingDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) talkingDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) talkingDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return talkingDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return talkingDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return talkingDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        talkingDao.refresh(o);
    }

    @Override
    public void update(T o) {
        talkingDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return talkingDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
}
