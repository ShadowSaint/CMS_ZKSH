package com.cdzksh.index.service;

import com.cdzksh.index.domain.ArticleDO;
import com.cdzksh.index.domain.ArticleQueryParam;
import com.cdzksh.index.domain.ArticleVOIndex;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
public interface ArticleService {
    void insertArticle(ArticleDO articleDO);
    void deleteArticle(int id);
    void updateArticle(ArticleDO articleDO);
    Page<ArticleDO> listArticleInfoByParam(ArticleQueryParam articleQueryParam, int page,int size);
    List<ArticleVOIndex> listArticleVOByParam(ArticleQueryParam articleQueryParam, int page, int size);
    List<ArticleDO> listCarouselArticle();

    ArticleDO getArticleById(int id);

}
