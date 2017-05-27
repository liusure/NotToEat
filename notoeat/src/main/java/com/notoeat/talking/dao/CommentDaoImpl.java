package com.notoeat.talking.dao;

import com.notoeat.talking.model.CommentModel;
import com.notoeat.core.dao.DaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class CommentDaoImpl extends DaoImpl<CommentModel,Integer> implements CommentDao<CommentModel,Integer> {

}
