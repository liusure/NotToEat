package com.notoeat.config.service;

import com.notoeat.config.dao.ConfigDao;
import com.notoeat.config.model.ConfigModel;
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
public class ConfigServiceImpl<T, PK> implements ConfigService<T, PK> {
    @Resource
    private ConfigDao configDao;

    @Override
    public void save(T o) {
        configDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        configDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) configDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) configDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        configDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        configDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        configDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        configDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) configDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) configDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return configDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return configDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return configDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        configDao.refresh(o);
    }

    @Override
    public void update(T o) {
        configDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return configDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }

    @Override
    public ConfigModel getConfigByName(String name) {
        ConfigModel configModel = (ConfigModel)configDao.getByHQL("FROM ConfigModel c where c.name = ?",name);
       if (configModel==null) {
           configModel = new ConfigModel();
           configModel.setName(name);
       }
       return configModel;
    }
}
