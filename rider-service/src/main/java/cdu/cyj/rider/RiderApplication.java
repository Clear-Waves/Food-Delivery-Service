package cdu.cyj.rider;

import cdu.cyj.openfeign.clients.ProductServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {ProductServiceClient.class})
public class RiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiderApplication.class, args);
    }
}
