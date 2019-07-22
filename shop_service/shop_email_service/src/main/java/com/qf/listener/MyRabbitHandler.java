package com.qf.listener;

import com.qf.entity.Email;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author 王铭
 * @Date 2019/7/20
 */
@Component
public class MyRabbitHandler {

  private ExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String Fromemail;


    @RabbitListener(queues = "email_queue")
    public void handler(Email email){
        executorService.submit(() ->{
            //发送邮件
          MimeMessage mimeMessage = javaMailSender.createMimeMessage();
          //创建一个spring提供的邮件帮助对象
          MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

          try {
            messageHelper.setSubject(email.getSubject());
            messageHelper.setFrom(Fromemail);
            messageHelper.setTo(email.getTo());
            messageHelper.setText(email.getContent(),true);
            messageHelper.setSentDate(new Date());
            //发送邮件
            javaMailSender.send(mimeMessage);
          } catch (MessagingException e) {
            e.printStackTrace();
          }


        });
    }

}
