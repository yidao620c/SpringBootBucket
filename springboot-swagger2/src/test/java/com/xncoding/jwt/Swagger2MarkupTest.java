package com.xncoding.jwt;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.BufferedWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Swagger2MarkupTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/26
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Swagger2MarkupTest {
    @Autowired
    private MockMvc mockMvc;

    private static final Logger LOG = LoggerFactory.getLogger(Swagger2MarkupTest.class);

    /**
     * 自动生成adoc文件
     * @throws Exception e
     *
     * 执行完成后生成PDF文件方法：
     *
     * 首先在`swagger/swagger.adoc`的顶部加入：
    ```
    :toclevels: 3
    :numbered:
    ```
    注意有个空行分割，目的是左边导航菜单是3级，并且自动加序号。
    为了美化显示，还要将`swagger.adoc`中全局替换一下，将
    ```
    cols=".^2,.^3,.^9,.^4,.^2"
    ```
    替换成：
    ```
    cols=".^2,.^3,.^6,.^4,.^5"
    ```
    然后在/resources目录下面执行：
    ```
    asciidoctor-pdf -r asciidoctor-pdf-cjk-kai_gen_gothic -a pdf-style=KaiGenGothicCN swagger/swagger.adoc
    ```
    会在`swagger.adoc`的同级目录生成`swagger.pdf`文件。
     */
    @Test
    public void createSpringFoxSwaggerJson() throws Exception {
//        String outputDir = System.getProperty("swaggerOutputDir"); // mvn test
        MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String swaggerJson = response.getContentAsString();
//        Files.createDirectories(Paths.get(outputDir));
//        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)){
//            writer.write(swaggerJson);
//        }
        LOG.info("--------------------swaggerJson create --------------------");
        convertAsciidoc(swaggerJson);
        LOG.info("--------------------swagon.json to asciiDoc finished --------------------");
    }

    /**
     * 将swagger.yaml或swagger.json转换成漂亮的 AsciiDoc
     * 访问：http://localhost:9095/v2/api-docs
     * 将页面结果保存为src/main/resources/swagger.json
     */
    private void convertAsciidoc(String swaggerStr) {
//        Path localSwaggerFile = Paths.get(System.getProperty("swaggerOutputDir"), "swagger.json");
        Path outputFile = Paths.get(System.getProperty("asciiDocOutputDir"));
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        Swagger2MarkupConverter converter = Swagger2MarkupConverter.from(swaggerStr)
                .withConfig(config)
                .build();
        converter.toFile(outputFile);
    }
}
