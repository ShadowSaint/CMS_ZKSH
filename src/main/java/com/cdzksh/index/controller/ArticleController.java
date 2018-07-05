package com.cdzksh.index.controller;

import com.alibaba.fastjson.JSONObject;
import com.cdzksh.index.domain.ArticleDO;
import com.cdzksh.index.domain.ArticleQueryParam;
import com.cdzksh.index.domain.ResultVO;
import com.cdzksh.index.service.ArticleService;
import com.cdzksh.index.util.GRQUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/4
 */
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/api/manage/article/insert")
    public ResultVO apiManageArticleInsert(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int menu_id = Integer.valueOf(request.getParameter("menu_id"));
            int p_id = GRQUtil.getRequestInteger(request, "p_id", 0);
            String title = request.getParameter("title");
            if (title.length() > 100) {
                title = title.substring(0, 99);
            }
            String author = request.getParameter("author");
            if (author.length() > 100) {
                author = author.substring(0, 99);
            }
            String cover = request.getParameter("cover");
            String content = request.getParameter("content");
            String description = content;
            if (content.length() > 100) {
                description = content.substring(0, 99);
            }

            ArticleDO articleDO = new ArticleDO();
            articleDO.setGmt_create(now);
            articleDO.setGmt_modified(now);
            articleDO.setMenu_id(menu_id);
            articleDO.setP_id(p_id);
            articleDO.setTitle(title);
            articleDO.setAuthor(author);
            articleDO.setCover(cover);
            articleDO.setDescription(description);
            articleDO.setContent(content);

            articleService.insertArticle(articleDO);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/article/delete")
    public ResultVO apiManageArticleDelete(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            articleService.deleteArticle(id);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/article/update")
    public ResultVO apiManageArticleUpdate(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String title = request.getParameter("title");
            if (title.length() > 100) {
                title = title.substring(0, 99);
            }
            String author = request.getParameter("author");
            if (author.length() > 100) {
                author = author.substring(0, 99);
            }
            String cover = request.getParameter("cover");
            String content = request.getParameter("content");
            String description = content;
            if (content.length() > 100) {
                description = content.substring(0, 99);
            }

            ArticleDO articleDO = new ArticleDO();
            articleDO.setId(id);
            articleDO.setGmt_modified(now);
            articleDO.setTitle(title);
            articleDO.setAuthor(author);
            articleDO.setCover(cover);
            articleDO.setDescription(description);
            articleDO.setContent(content);

            articleService.updateArticle(articleDO);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/article/detail")
    public ResultVO apiManageArticleDetail(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            int id = Integer.valueOf(request.getParameter("id"));

            ArticleDO articleDO = articleService.getArticleById(id);

            resultVO.setData(articleDO);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/article/list")
    public ResultVO apiManageArticleList(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            String start_time = request.getParameter("start_time");
            String end_time = request.getParameter("end_time");
            String search = request.getParameter("search");
            int menu_id = Integer.valueOf(request.getParameter("menu_id"));
            int p_id = GRQUtil.getRequestInteger(request, "p_id", 0);
            int page = GRQUtil.getRequestInteger(request, "page", 1);
            int size = GRQUtil.getRequestInteger(request, "size", 20);

            ArticleQueryParam articleQueryParam = new ArticleQueryParam();
            articleQueryParam.setStart_time(start_time);
            articleQueryParam.setEnd_time(end_time);
            articleQueryParam.setSearch(search);
            articleQueryParam.setMenu_id(menu_id);
            articleQueryParam.setP_id(p_id);

            Page<ArticleDO> articleDOPage = articleService.listArticleInfoByParam(articleQueryParam, page, size);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list", (List<ArticleDO>) articleDOPage);
            jsonObject.put("count", articleDOPage.size());

            resultVO.setData(jsonObject);

        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }
}
