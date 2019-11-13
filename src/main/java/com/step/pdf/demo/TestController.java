package com.step.pdf.demo;

import com.step.pdf.demo.util.PdfUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("download")
    public void test(HttpServletResponse response) throws Exception {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "表格标题");
        variables.put("array", new String[]{"土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "芹菜", "土豆",
                "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆",
                "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆",
                "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆",
                "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆", "番茄", "白菜", "芹菜", "土豆",
                "番茄", "白菜", "芹菜"});
        PdfUtil.buildPdf(response, "用于测试的pdf", "example", variables);
    }
}
