package com.notoeat.article.dao;

import com.notoeat.article.model.ArticleModel;
import com.notoeat.core.dao.DaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by l on 2017/4/23.
 */
@Repository
public class ArticleDaoImpl extends DaoImpl<ArticleModel,Integer> implements ArticleDao<ArticleModel,Integer> {

}
