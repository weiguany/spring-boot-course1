package top.wgy.boot.config.service;

import enums.ResultStatus;
import org.springframework.web.multipart.MultipartFile;
import top.wgy.boot.config.model.Mail;

public interface MailService {
    ResultStatus sendSimpleMail(Mail mail);
    /**
     * 发送HTML邮件
     *
     * @param mail 邮件信息
     */
    ResultStatus sendHTMLMail(Mail mail);


    /**
     * 发送带附件的邮件
     *
     * @param mail 邮件信息
     * @param files 附件文件
     */
    ResultStatus sendAttachmentsMail(Mail mail, MultipartFile[] files);



}