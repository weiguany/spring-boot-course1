package top.wgy.boot.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * ⽂件上传
     *
     * @param file ⽂件
     */
    String upload(MultipartFile file);
}