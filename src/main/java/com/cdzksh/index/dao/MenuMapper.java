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
            "`seq`)\n" +
            "VALUES\n" +
            "(\n" +
            "#{m.gmt_create},\n" +
            "#{m.gmt_modified},\n" +
            "#{m.p_id},\n" +
            "#{m.name},\n" +
            "#{m.seq});\n")
    @Options(useGeneratedKeys = true,keyColumn = "m.id")
    void insertMenu(@Param("m")MenuDO menuDO);

    @Delete("delete from cms_menu where id = #{0}")
    void deleteMenu(int id);

    @Update("UPDATE `CMS_ZKSH`.`cms_menu`\n" +
            "SET\n" +
            "`gmt_modified` = #{m.gmt_modified},\n" +
            "`p_id` = #{m.p_id},\n" +
            "`name` = #{m.name},\n" +
            "`seq` = #{m.seq}\n" +
            "WHERE `id` = #{m.id};\n")
    void updateMenu(@Param("m")MenuDO menuDO);

    @Select("select * from cms_menu where p_id = #{0} order by seq")
    List<MenuDO> listMenu(int p_id);
}
