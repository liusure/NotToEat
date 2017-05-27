package com.notoeat.config.service;

import com.notoeat.config.model.ConfigModel;
import com.notoeat.core.dao.Dao;

/**
 * Created by l on 2017/4/23.
 */
public interface ConfigService<T,PK> extends Dao<T,PK> {
    public ConfigModel getConfigByName(String name);
}
