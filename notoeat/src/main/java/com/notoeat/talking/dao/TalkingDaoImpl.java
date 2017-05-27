package com.notoeat.talking.dao;

import com.notoeat.talking.model.TalkingModel;
import com.notoeat.core.dao.DaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class TalkingDaoImpl extends DaoImpl<TalkingModel,Integer> implements TalkingDao<TalkingModel,Integer> {

}
