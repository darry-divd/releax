package com.example.demo.config;


import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.shade.org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云OSS工具类
 *
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
public class AliyunOSSClientUtils {

    @Value(value = "${aliyun.oss.endpoint}")
    private String endpoint;
    @Value(value = "${aliyun.oss.access.key.id}")
    private String accessKeyId;
    @Value(value = "${aliyun.oss.access.key.secret}")
    private String accessKeySecret;
    @Value(value = "${aliyun.oss.bucket.name}")
    private String bucket;
    @Value(value = "${upload.tempDir}")
    private String tempDir;

    /**
     * 获取阿里OSS客户端
     *
     * @return 返回OSS实例
     */
    public OSS getDefaultOSSClient() {
       // 创建ClientConfiguration实例

        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
       // 设置OSSClient使用的最大连接数，默认1024
        config.setMaxConnections(200);
      //  设置请求超时时间，默认50秒
        config.setSocketTimeout(10000);
      //  设置失败请求重试次数，默认3次
        config.setMaxErrorRetry(3);

        if (StringUtils.isEmpty(endpoint)) {
            throw new NullArgumentException("未配置[aliyun.oss.endpoint]参数");
        }
        if (StringUtils.isEmpty(accessKeyId)) {
            throw new NullArgumentException("未配置[aliyun.oss.access.key.id]参数");
        }
        if (StringUtils.isEmpty(accessKeySecret)) {
            throw new NullArgumentException("未配置[aliyun.oss.access.key.secret]参数");
        }
        log.info("endpoint：" + endpoint + ", accessKeyId: " + accessKeyId + ", accessKeySecret: " + accessKeySecret);
      //  创建OSSClient实例

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, config);
        return ossClient;
    }

    /**
     * 获取<code>fileName</code> 对应文件的公共URL地址（不权限控制）
     *
     * @param fileName 文件名称
     * @return 返回文件的URL地址
     */
    public String getPublicUrl(String fileName) {
        OSS defaultOSSClient = getDefaultOSSClient();
        return getPublicUrl(defaultOSSClient, bucket, fileName);
    }

    /**
     * 获取<code>fileName</code> 对应文件的公共URL地址（不权限控制）
     *
     * @param client     OSSClient实例
     * @param bucketName bucket名称
     * @param fileName   文件名称
     * @return 返回上传文件的URL地址
     */
    public String getPublicUrl(OSS client, String bucketName, String fileName) {
        if (null == client) {
            throw new NullArgumentException("client参数对象不能为空");
        }
        if (StringUtils.isEmpty(bucketName)) {
            throw new NullArgumentException("bucketName参数不能为空");
        }
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);

      //  生成URL
        URL url = getDefaultOSSClient().generatePresignedUrl(bucket, fileName, expiration);
        String urlStr = null;
        if (url != null) {
            urlStr = url.toString();
            urlStr = urlStr.replace("?" + url.getQuery(), "");
        }
        return urlStr;
    }

    /**
     * @param fileName
     * @date: 22:43 2019/7/22
     * @return: boolean
     * 判断文件是否存在
     **/
    public boolean doesObjectExist(String fileName) {

        if (StringUtils.isEmpty(fileName)) {
            throw new NullArgumentException("fileName参数不能为空");
        }

        OSS ossClient = getDefaultOSSClient();
        boolean doesObjectExist = ossClient.doesObjectExist(bucket, fileName);
        log.info("是否存在: {},  {}", fileName, doesObjectExist);
        ossClient.shutdown();
        return doesObjectExist;
    }

    /**
     * @param folderName
     * @param uploadFile
     * @date: 22:52 2019/7/22
     * @return: String
     * 上传文件
     **/
    public String putFile(String folderName, File uploadFile) throws IOException {

        if (null == uploadFile) {
            throw new NullArgumentException("uploadFile参数不能为空");
        }

        OSS ossClient = getDefaultOSSClient();
        FileInputStream fileInputStream = new FileInputStream(uploadFile);
        String suffix = uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1);

        String fileName = UUID.randomUUID().toString() + suffix;
        String targetFile = folderName + fileName;
        PutObjectResult putObjectResult = ossClient.putObject(bucket, targetFile, fileInputStream);
        fileInputStream.close();
        ossClient.shutdown();
        if (null == putObjectResult.getResponse()) {
            return targetFile;
        }
        return null;
    }

    /**
     * @param folderName
     * @param fileInputStream
     * @param suffix
     * @date: 22:52 2019/7/22
     * @return: String
     * 上传文件

    public String putFile(String folderName, InputStream fileInputStream, String suffix) throws IOException {

        if (null == fileInputStream) {
            throw new NullArgumentException("uploadFile参数不能为空");
        }

        OSS ossClient = getDefaultOSSClient();
        String suffix = uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1);


        String fileName = UUID.randomUUID().toString() + "." + suffix;
        String targetFile = "";
        targetFile = fileName;
        if (null != folderName) {
            targetFile = folderName + "/" + fileName;
        }
        PutObjectResult putObjectResult = ossClient.putObject(bucket, targetFile, fileInputStream);
        fileInputStream.close();
        ossClient.shutdown();
        if (null == putObjectResult.getResponse()) {
            return targetFile;
        }
        return null;
    }
     **/
    /**
     * @param fileName
     * @date: 22:59 2019/7/22
     * @return: URL
     * 预览文件
     **/
    public URL getURLFile(String fileName) {

        OSS ossClient = getDefaultOSSClient();
       // 设置URL过期时间毫秒数。当前为1小时。
        long exprie = 3600 * 1000;
        Date expiration = new Date(new Date().getTime() + exprie);
        URL url = ossClient.generatePresignedUrl(bucket, fileName, expiration);

        ossClient.shutdown();
        return url;
    }

    /**
     * @param fileName
     * @date: 22:59 2019/7/22
     * @return: URL
     * 删除文件
     **/
    public void delFile(String fileName) {
        OSS ossClient = getDefaultOSSClient();


        ossClient.deleteObject(bucket, fileName);

    }
    /**
     * @param fileName
     * @date: 22:59 2019/7/22
     * @return: URL
     * 流方式下载文件
     **/
    public InputStream getFileStream(String fileName) {
        OSS ossClient = getDefaultOSSClient();
        OSSObject ossObject = ossClient.getObject(bucket, fileName);
        return ossObject.getObjectContent();
    }

    /**
     * @param fileName
     * @date: 22:59 2019/7/22
     * @return: URL
     * 下载文件
     **/
    public File getFile(String fileName) throws IOException {
        OSS ossClient = getDefaultOSSClient();
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, fileName);
        File fileDir = new File(tempDir);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        File tempFile = File.createTempFile(UUID.randomUUID().toString(), suffix, fileDir);
        ossClient.getObject(getObjectRequest, tempFile);
        tempFile.deleteOnExit();
        return tempFile;
    }


}
