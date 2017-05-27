package com.notoeat.config.dao;


import com.notoeat.config.model.ConfigModel;
import com.notoeat.core.dao.DaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class ConfigDaoImpl extends DaoImpl<ConfigModel,Integer> implements ConfigDao<ConfigModel,Integer> {

}
