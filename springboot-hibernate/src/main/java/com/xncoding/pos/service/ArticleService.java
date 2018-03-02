package com.xncoding.pos.service;

import java.util.List;

import com.xncoding.pos.dao.entity.Article;
import com.xncoding.pos.dao.repository.IArticleDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ArticleService
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/2
 */
@Service
public class ArticleService {

    @Resource
    private IArticleDAO articleDAO;

    public Article getArticleById(int articleId) {
        Article obj = articleDAO.getArticleById(articleId);
        return obj;
    }

    public List<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }

    public synchronized boolean addArticle(Article article) {
        if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            articleDAO.addArticle(article);
            return true;
        }
    }

    public void updateArticle(Article article) {
        articleDAO.updateArticle(article);
    }

    public void deleteArticle(int articleId) {
        articleDAO.deleteArticle(articleId);
    }
}
