package cdu.cyj.file.utils;

import cdu.cyj.file.config.OssConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 封装文件上传方法
 */
@Component
public class AliyunOssUtil {

    @Autowired
    private OssConfiguration config;

    public String upload(InputStream inputStream, String fileName) {
        String endPoint = config.getEndPoint();
        String keyId = config.getAccessKeyId();
        String keySecret = config.getAccessKeySecret();
        String bucketName = config.getBucketName();
        String fileHost = config.getFileHost();
        String prePath = config.getPrePath();

        //定义子文件的格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        //阿里云文件上传客户端
        OSSClient client = new OSSClient(endPoint, keyId, keySecret);

        try {
            //判断桶是否存在
            if (!client.doesBucketExist(bucketName)) {
                //创建桶
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                //设置访问权限为公共读
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                //发起创建桶的请求
                client.createBucket(createBucketRequest);
            }

            //当桶存在时,进行文件上传
            //设置文件路径和名称
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", ""));

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");

            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, inputStream, meta));

            //文件上传成功后,返回当前文件的路径
            if (result != null) {
                return prePath + fileUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }

        return null;
    }

}