package com.xncoding.pos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xncoding.pos.model.BaseResponse;
import com.xncoding.pos.model.User;
import com.xncoding.pos.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ApplicationTests
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/4
 */

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request;

        // 1、get查一下user列表，应该为空
        request = get("/users/");
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        BaseResponse<List<User>> response = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<List<User>>>() {});
        assertThat(response.isSuccess(), is(true));
        assertThat(response.getMsg(), is("查询列表成功"));
        assertThat(((List) response.getData()).size(), is(0));

        // 2、post提交一个user
        request = post("/users/")
                .param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        BaseResponse<String> response1 = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<String>>() {});
        assertThat(response1.isSuccess(), is(true));
        assertThat(response1.getMsg(), is("新增成功"));

        // 3、get获取user列表，应该有刚才插入的数据
        request = get("/users/");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        BaseResponse<List<User>> response2 = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<List<User>>>() {});
        assertThat(response2.isSuccess(), is(true));
        assertThat(response2.getMsg(), is("查询列表成功"));
        assertThat((response2.getData()).size(), is(1));

        // 4、put修改id为1的user
        request = put("/users/1")
                .param("name", "测试终极大师")
                .param("age", "30");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        BaseResponse<String> response3 = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<String>>() {});
        assertThat(response3.isSuccess(), is(true));
        assertThat(response3.getMsg(), is("更新成功"));

        // 5、get一个id为1的user
        request = get("/users/1");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        BaseResponse<User> response4 = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<User>>() {});
        assertThat(response4.isSuccess(), is(true));
        assertThat(response4.getMsg(), is("查询成功"));
        User user = response4.getData();
        assertThat(user.getId(), is(1L));
        assertThat(user.getName(), is("测试终极大师"));

        // 6、del删除id为1的user
        request = delete("/users/1");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        BaseResponse<String> response5 = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<String>>() {});
        assertThat(response5.isSuccess(), is(true));
        assertThat(response5.getMsg(), is("删除成功"));

        // 7、get查一下user列表，应该为空
        request = get("/users/");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        BaseResponse<List<User>> response6 = JacksonUtil.json2Bean(content, new TypeReference<BaseResponse<List<User>>>() {});
        assertThat(response6.isSuccess(), is(true));
        assertThat(response6.getMsg(), is("查询列表成功"));
        assertThat((response6.getData()).size(), is(0));
    }

}
