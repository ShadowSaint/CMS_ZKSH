package com.cdzksh.index.dao;

import com.cdzksh.index.domain.ArticleDO;
import com.cdzksh.index.domain.ArticleQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/4
 */
@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO `CMS_ZKSH`.`cms_article_info`\n" +
            "(\n" +
            "`gmt_create`,\n" +
            "`gmt_modified`,\n" +
            "`menu_id`,\n" +
            "`p_id`,\n" +
            "`title`,\n" +
            "`author`,\n" +
            "`cover`,\n" +
            "`description`)\n" +
            "VALUES\n" +
            "(\n" +
            "#{a.gmt_create},\n" +
            "#{a.gmt_modified},\n" +
            "#{a.menu_id},\n" +
            "#{a.p_id},\n" +
            "#{a.title},\n" +
            "#{a.author},\n" +
            "#{a.cover},\n" +
            "#{a.description});\n")
    @Options(useGeneratedKeys = true,keyProperty = "a.id")
    void insertArticleInfo(@Param("a")ArticleDO articleDO);

    @Insert("INSERT INTO `CMS_ZKSH`.`cms_article_detail`\n" +
            "(`id`,\n" +
            "`content`)\n" +
            "VALUES\n" +
            "(#{a.id},\n" +
            "#{a.content});\n")
    void insertArticleDetail(@Param("a")ArticleDO articleDO);

    @Delete("DELETE FROM `CMS_ZKSH`.`cms_article_info`\n" +
            "WHERE id=#{0};\n")
    void deleteArticleInfo(int id);

    @Delete("DELETE FROM `CMS_ZKSH`.`cms_article_detail`\n" +
            "WHERE id=#{0};\n")
    void deleteArticleDetail(int id);

    @Update("UPDATE `CMS_ZKSH`.`cms_article_info`\n" +
            "SET\n" +
            "`gmt_modified` = #{a.gmt_modified},\n" +
            "`title` = #{a.title},\n" +
            "`author` = #{a.author},\n" +
            "`cover` = #{a.cover},\n" +
            "`description` = #{a.description}\n" +
            "WHERE `id` = #{a.id};\n")
    void updateArticleInfo(@Param("a")ArticleDO articleDO);

    @Update("UPDATE `CMS_ZKSH`.`cms_article_detail`\n" +
            "SET\n" +
            "`content` = #{a.content}\n" +
            "WHERE `id` = #{a.id};\n")
    void updateArticleDetail(@Param("a")ArticleDO articleDO);

    @SelectProvider(type = ArticleProvider.class, method = "queryArticleInfoByParam")
    List<ArticleDO> listArticleInfoByParam(@Param("param")ArticleQueryParam articleQueryParam);

    @Select("select a.*,b.content from cms_article_info a left join cms_article_detail b on a.id=b.id where a.id = #{0}")
    ArticleDO getArticleById(int id);
}
