package imooc.security.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author pxc
 */
@SpringBootApplication
public class ImoocSecurityCoreApplication {


    public static void main(String[] args) {
        SpringApplication.run(ImoocSecurityCoreApplication.class, args);
    }
}
