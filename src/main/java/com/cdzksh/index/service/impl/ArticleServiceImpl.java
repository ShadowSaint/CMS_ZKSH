package com.cdzksh.index.service.impl;

import com.cdzksh.index.dao.ArticleMapper;
import com.cdzksh.index.domain.ArticleDO;
import com.cdzksh.index.domain.ArticleQueryParam;
import com.cdzksh.index.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void insertArticle(ArticleDO articleDO) {
        articleMapper.insertArticleInfo(articleDO);
        articleMapper.insertArticleDetail(articleDO);
    }

    @Override
    public void deleteArticle(int id) {
        articleMapper.deleteArticleInfo(id);
        articleMapper.deleteArticleDetail(id);
    }

    @Override
    public void updateArticle(ArticleDO articleDO) {
        articleMapper.updateArticleInfo(articleDO);
        articleMapper.updateArticleDetail(articleDO);
    }

    @Override
    public Page<ArticleDO> listArticleInfoByParam(ArticleQueryParam articleQueryParam, int page, int size) {
        Page<ArticleDO> articleDOPage= PageHelper.startPage(page,size);
        articleMapper.listArticleInfoByParam(articleQueryParam);
        return articleDOPage;
    }

    @Override
    public ArticleDO getArticleById(int id) {
        return articleMapper.getArticleById(id);
    }
}
