package com.notoeat.user.dao;

import com.notoeat.core.dao.DaoImpl;
import com.notoeat.user.model.AuthorityModel;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class AuthorityDaoImpl extends DaoImpl<AuthorityModel,Integer> implements AuthorityDao<AuthorityModel,Integer> {

}
