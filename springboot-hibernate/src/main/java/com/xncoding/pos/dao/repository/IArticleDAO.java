package com.xncoding.pos.dao.repository;

import com.xncoding.pos.dao.entity.Article;

import java.util.List;

/**
 * IArticleDAO
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/2
 */
public interface IArticleDAO {
    List<Article> getAllArticles();

    Article getArticleById(int articleId);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);

    boolean articleExists(String title, String category);
}
