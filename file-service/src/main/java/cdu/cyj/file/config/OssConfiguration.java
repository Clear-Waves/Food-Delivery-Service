package cdu.cyj.file.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 把配置文件中的配置信息读取到该类中.
 */
@Data
@Configuration
@PropertySource(value = {"classpath:application-alibaba-oss.properties"})
public class OssConfiguration {

    @Value("${oss.endpoint}")
    private String endPoint;

    @Value("${oss.access-key}")
    private String accessKeyId;

    @Value("${oss.secret-key}")
    private String accessKeySecret;

    @Value("${oss.fileHost}")
    private String fileHost;

    @Value("${oss.bucket}")
    private String bucketName;

    @Value("${oss.prePath}")
    private String prePath;

}