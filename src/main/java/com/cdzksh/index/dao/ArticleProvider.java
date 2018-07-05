package com.cdzksh.index.dao;

import com.cdzksh.index.domain.ArticleQueryParam;
import com.cdzksh.index.util.GRQUtil;

import java.util.Map;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
public class ArticleProvider {

    public String queryArticleInfoByParam(Map<String, Object> param) {
        ArticleQueryParam articleQueryParam = (ArticleQueryParam) param.get("param");
        String sql = "select * from cms_article_info where 1=1 ";
        if (!GRQUtil.checkNull(articleQueryParam.getStart_time())) {
            sql += " and gmt_create > '" + articleQueryParam.getStart_time() + "'";
        }
        if (!GRQUtil.checkNull(articleQueryParam.getEnd_time())) {
            sql += " and gmt_create < '" + articleQueryParam.getEnd_time() + "'";
        }
        if (!GRQUtil.checkNull(articleQueryParam.getSearch())) {
            sql += " and title like '%" + articleQueryParam.getSearch() + "%'";
        }
        if (!GRQUtil.checkNull(articleQueryParam.getMenu_id())) {
            sql+= " and menu_id = "+articleQueryParam.getMenu_id();
        }
        if (!GRQUtil.checkNull(articleQueryParam.getP_id())){
            sql+= " and p_id = "+articleQueryParam.getP_id();
        }
        sql+=" order by gmt_create desc ";
        return sql;
    }
}
