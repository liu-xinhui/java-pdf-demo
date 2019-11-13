package com.step.pdf.demo;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws Exception {
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("print/templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("name", "蔬菜列表");
        context.setVariable("array", new String[]{"土豆", "番茄", "白菜", "芹菜"});

        //渲染模板
        //FileWriter write = new FileWriter("result.html");
        String example = templateEngine.process("example", context);
        System.out.println(example);
        File tempPdf = File.createTempFile("out", ".pdf");
        try (FileOutputStream os = new FileOutputStream(tempPdf)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFont(ResourceUtils.getFile("classpath:fonts/simsun.ttf"), "simsun");
            builder.useFastMode();
            builder.withHtmlContent(example, "file");
            builder.toStream(os);
            builder.run();
        }
        System.out.println(tempPdf.length());
    }

}
