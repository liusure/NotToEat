package com.notoeat.user.service;

import com.notoeat.user.dao.UserDao;
import com.notoeat.utils.PageResults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserServiceImpl<T, PK> implements UserService<T, PK>, UserDetailsService {
    @Resource
    private UserDao userDao;

    @Override
    public void save(T o) {
        userDao.save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        userDao.saveOrUpdate(o);
    }

    @Override
    public T load(PK id) {
        return (T) userDao.load(id);
    }

    @Override
    public T get(PK id) {
        return (T) userDao.get(id);
    }

    @Override
    public boolean contains(T o) {
        return contains(o);
    }

    @Override
    public void delete(T o) {
        userDao.delete(o);
    }

    @Override
    public boolean deleteById(PK Id) {
        return deleteById(Id);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        userDao.deleteAll(entities);
    }

    @Override
    public void queryHql(String hqlString, Object... values) {
        userDao.queryHql(hqlString, values);
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        userDao.querySql(sqlString, values);
    }

    @Override
    public T getByHQL(String hqlString, Object... values) {
        return (T) userDao.getByHQL(hqlString, values);
    }

    @Override
    public T getBySQL(String sqlString, Object... values) {
        return (T) userDao.getBySQL(sqlString, values);
    }

    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        return userDao.getListByHQL(hqlString, values);
    }

    @Override
    public List<T> getListBySQL(String sqlString, Object... values) {
        return userDao.getListBySQL(sqlString, values);
    }

    @Override
    public List<T> findListBySql(String sql, RowMapper map, Object... values) {
        return userDao.findListBySql(sql, map, values);
    }

    @Override
    public void refresh(T o) {
        userDao.refresh(o);
    }

    @Override
    public void update(T o) {
        userDao.update(o);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        return countByHql(hql, values);
    }

    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values) {
        return userDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails UserDetails = (UserDetails) getByHQL("FROM UserModel u WHERE u.userName = ?",s);
        return UserDetails;
    }
}
