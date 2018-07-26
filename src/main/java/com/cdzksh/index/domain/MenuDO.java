package com.cdzksh.index.domain;

/**
 * @Author Created by ShadowSaint on 2018/7/4
 */
public class MenuDO {
    private Integer id;
    private String gmt_create;
    private String gmt_modified;
    private Integer p_id;
    private String name;
    private Integer seq;
    private String content;
    private Integer type;

    @Override
    public String toString() {
        return "MenuDO{" +
                "id=" + id +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modified='" + gmt_modified + '\'' +
                ", p_id=" + p_id +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create.split("\\.")[0];
    }

    public String getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(String gmt_modified) {
        this.gmt_modified = gmt_modified.split("\\.")[0];
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
