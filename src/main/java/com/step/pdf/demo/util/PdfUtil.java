package com.step.pdf.demo.util;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

public class PdfUtil {
    /**
     * @param response     http请求后的相应
     * @param pdfFileName  pdf文件名称
     * @param templateName 模板名称
     * @param variables    模板变量
     */
    public static void buildPdf(HttpServletResponse response, String pdfFileName, String templateName, Map<String, Object> variables) throws Exception {
        OutputStream os = response.getOutputStream();
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("print/templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariables(variables);
        context.setVariable("templateName", templateName);
        context.setVariable("pdfFileName", pdfFileName);
        //渲染模板
        String example = templateEngine.process("parent", context);
        System.out.println(example);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFont(ResourceUtils.getFile("classpath:print/fonts/simsun.ttf"), "simsun");
        builder.useFastMode();
        builder.withHtmlContent(example, ResourceUtils.getURL("classpath:print/img/").toString());
        builder.toStream(os);
        builder.run();
        response.setContentType("application/pdf");
        response.flushBuffer();
    }
}
