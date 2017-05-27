package com.notoeat.media.dao;

import com.notoeat.core.dao.DaoImpl;
import com.notoeat.media.model.MediaModel;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class MediaDaoImpl extends DaoImpl<MediaModel,Integer> implements MediaDao<MediaModel,Integer> {

}
