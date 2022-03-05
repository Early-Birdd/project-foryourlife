package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.request.InquiryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryService {

    private final JavaMailSender javaMailSender;
    //추후 admin role member를 리스트로 받을 것?
    private static final String AdminAddress = "goqhwk1100@gmail.com";

    public void sendMail(InquiryDto inquiryDto){

//        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(InquiryService.AdminAddress);
            simpleMailMessage.setFrom(InquiryService.AdminAddress);
            simpleMailMessage.setSubject(inquiryDto.getTitle());
            simpleMailMessage.setText("문의 고객 메일 : " + inquiryDto.getEmail()
                    + System.lineSeparator() + inquiryDto.getContent());
            javaMailSender.send(simpleMailMessage);
//        } catch (MailParseException e){
//            e.printStackTrace();
//        } catch (MailAuthenticationException e){
//            e.printStackTrace();
//        } catch (MailSendException e){
//            e.printStackTrace();
//        } catch (MailException e){
//            e.printStackTrace();
//        }
    }
}
