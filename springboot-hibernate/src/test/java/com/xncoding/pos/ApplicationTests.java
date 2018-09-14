package com.xncoding.pos;

import com.xncoding.pos.dao.entity.Article;
import com.xncoding.pos.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

    @Resource
    private ArticleService articleService;

    /**
     * 测试增删改查
     */
    @Test
    public void test() {
        Article article = articleService.getArticleById(1);
        assertThat(article.getTitle(), is("Java Concurrency"));

        List<Article> list = articleService.getAllArticles();
        assertThat(list, notNullValue());
        assertThat(list.size(), is(3));

        boolean flag = articleService.addArticle(article);
        assertThat(flag, is(false));

        article.setTitle("Python Concurrency");
        articleService.updateArticle(article);
        Article article1 = articleService.getArticleById(1);
        assertThat(article1.getTitle(), is("Python Concurrency"));


        articleService.deleteArticle(1);
        Article article2 = articleService.getArticleById(1);
        assertThat(article2, nullValue());
    }
}
