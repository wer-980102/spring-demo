package com.wer.email.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;


    /**
     * �򵥵��ʼ�����
     * @throws Exception
     */
    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("1578535923@qq.com","test simple mail"," hello this is simple mail");
    }

    /**
     * html����
     * @throws Exception
     */
    @Test
    public void testHtmlMail() throws Exception {
        String content="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" +
                "<html>" +
                "<body>" +
                "    <h3>hello world ! ����һ��html�ʼ�!</h3>" +
                "</body>" +
                "</html>";

        String str1 = new String(content.getBytes("ISO-8859-1"), "utf-8");
        mailService.sendHtmlMail("1780104168@qq.com","test simple mail",str1);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="e:\\tmp\\application.log";
        mailService.sendAttachmentsMail("2540808026@qq.com", "���⣺���������ʼ�", "�и���������գ�", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>������ͼƬ���ʼ���<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "F:\\img\\test.jpg";

        mailService.sendInlineResourceMail("1780104168@qq.com", "���⣺������ͼƬ���ʼ�", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        //�����ʼ�����
        Context context = new Context();
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("1780104168@qq.com","This is a clocked email!",emailContent);
    }
}
