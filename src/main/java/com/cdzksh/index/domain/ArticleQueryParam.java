package com.cdzksh.index.domain;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
public class ArticleQueryParam {
    private String start_time;
    private String end_time;
    private String search;
    private Integer menu_id;
    private Integer p_id;

    @Override
    public String toString() {
        return "ArticleQueryParam{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", search='" + search + '\'' +
                ", menu_id=" + menu_id +
                ", p_id=" + p_id +
                '}';
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

}
