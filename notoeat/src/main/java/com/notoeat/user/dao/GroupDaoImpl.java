package com.notoeat.user.dao;


import com.notoeat.core.dao.DaoImpl;
import com.notoeat.user.model.GroupModel;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class GroupDaoImpl extends DaoImpl<GroupModel,Integer> implements GroupDao<GroupModel,Integer> {

}
