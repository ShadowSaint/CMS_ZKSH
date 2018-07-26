package com.cdzksh.index.dao;

import com.cdzksh.index.domain.ArticleQueryParam;
import com.cdzksh.index.util.GRQUtil;

import java.util.Map;

/**
 * @Author Created by ShadowSaint on 2018/7/25
 */
public class MenuProvider {
    public String queryMenuByParam(Map<String, Object> param) {
        String sql = "select * from cms_menu where 1=1 ";
        String p_id= (String) param.get("p_id");
        if (!GRQUtil.checkNull(p_id)) {
            sql += " and p_id = " + p_id + "";
        }
        String type = (String) param.get("type");
        if (!GRQUtil.checkNull(type)) {
            sql += " and type = " + type + "";
        }
        return sql;
    }
}
