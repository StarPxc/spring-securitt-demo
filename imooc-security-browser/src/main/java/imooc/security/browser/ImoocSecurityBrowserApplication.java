package imooc.security.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author pxc
 */
@SpringBootApplication
@EnableJpaRepositories("imooc.security.*.dao")
@ComponentScan(basePackages = {"imooc.security"})
public class ImoocSecurityBrowserApplication {


    public static void main(String[] args) {
        SpringApplication.run(ImoocSecurityBrowserApplication.class, args);
    }
}
