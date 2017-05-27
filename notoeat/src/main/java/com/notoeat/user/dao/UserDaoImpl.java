package com.notoeat.user.dao;

import com.notoeat.core.dao.DaoImpl;
import com.notoeat.user.model.UserModel;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class UserDaoImpl extends DaoImpl<UserModel,Integer> implements UserDao<UserModel,Integer> {

}
