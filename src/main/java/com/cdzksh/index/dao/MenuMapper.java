package com.cdzksh.index.dao;

import com.cdzksh.index.domain.MenuDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@Mapper
public interface MenuMapper {
    @Insert("INSERT INTO `CMS_ZKSH`.`cms_menu`\n" +
            "(\n" +
            "`gmt_create`,\n" +
            "`gmt_modified`,\n" +
            "`p_id`,\n" +
            "`name`,\n" +
            "`seq`,\n" +
            "`content`,\n" +
            "`type`)\n" +
            "VALUES\n" +
            "(\n" +
            "#{m.gmt_create},\n" +
            "#{m.gmt_modified},\n" +
            "#{m.p_id},\n" +
            "#{m.name},\n" +
            "#{m.seq},\n" +
            "#{m.content},\n" +
            "#{m.type});\n")
    @Options(useGeneratedKeys = true,keyColumn = "m.id")
    void insertMenu(@Param("m")MenuDO menuDO);

    @Delete("delete from cms_menu where id = #{0}")
    void deleteMenu(int id);

    @Update("UPDATE `CMS_ZKSH`.`cms_menu`\n" +
            "SET\n" +
            "`gmt_modified` = #{m.gmt_modified},\n" +
            "`p_id` = #{m.p_id},\n" +
            "`name` = #{m.name},\n" +
            "`seq` = #{m.seq},\n" +
            "`content` = #{m.content},\n" +
            "`type` = #{m.type}\n" +
            "WHERE `id` = #{m.id};\n")
    void updateMenu(@Param("m")MenuDO menuDO);

    @SelectProvider(type = MenuProvider.class,method = "queryMenuByParam")
    List<MenuDO> listMenu(@Param("p_id") String p_id,@Param("type")String type);

    @Select("select * from cms_menu where id = #{0}")
    MenuDO getMenu(int id);
}
