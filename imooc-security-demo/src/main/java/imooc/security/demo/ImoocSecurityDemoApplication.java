package imooc.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("imooc.security.*.dao")
@ComponentScan(basePackages = {"imooc.security"})
public class ImoocSecurityDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ImoocSecurityDemoApplication.class, args);
    }
}
