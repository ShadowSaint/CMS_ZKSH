package com.cdzksh.index.service;

import com.cdzksh.index.domain.ArticleDO;
import com.cdzksh.index.domain.ArticleQueryParam;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
public interface ArticleService {
    void insertArticle(ArticleDO articleDO);
    void deleteArticle(int id);
    void updateArticle(ArticleDO articleDO);
    Page<ArticleDO> listArticleInfoByParam(ArticleQueryParam articleQueryParam, int page,int size);
    ArticleDO getArticleById(int id);

}
