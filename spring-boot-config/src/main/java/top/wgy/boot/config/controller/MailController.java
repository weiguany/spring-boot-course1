package top.wgy.boot.config.controller;

import enums.ResultStatus;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.wgy.boot.config.model.Mail;
import top.wgy.boot.config.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;

    /**
     * 发送邮件
     * @param mail
     * @return
     */
    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<ResultStatus>> sendSimpleMail(@Valid
                                                                        @RequestBody Mail mail){
        ResultStatus rs = mailService.sendSimpleMail(mail);
        if (rs == ResultStatus.SUCCESS){
            return ResponseEntity.ok(ApiResponse.success("发送成功",rs));
        }
        //业务失败建议返回 400 （参数/业务错误），而不是 200
        return ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }
    /**
     * 发送HTML邮件
     *
     * @param mail 邮件信息
     * @return 邮件发送结果
     */
    @PostMapping("/html")
    public ResponseEntity<ApiResponse<ResultStatus>> sendHtmlMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendHTMLMail(mail);
        return rs == ResultStatus.SUCCESS ? ResponseEntity.ok(ApiResponse.success("发送成功", rs)) : ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }

    /**
     * 发送带附件的邮件
     *
     * @param mail  邮件信息
     * @param files 附件文件
     * @return 邮件发送结果
     */
    @PostMapping(value = "/attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ResultStatus>> sendAttachmentsMail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("content") String content,
            @RequestPart("files") MultipartFile[] files) {

        // 手动创建Mail对象
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setContent(content);

        ResultStatus rs = mailService.sendAttachmentsMail(mail, files);
        return rs == ResultStatus.SUCCESS ?
                ResponseEntity.ok(ApiResponse.success("发送成功", rs)) :
                ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }
}
