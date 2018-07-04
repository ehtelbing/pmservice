import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Web应用总入口.
 *
 * @author richard
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(basePackages = {"org.building"})
@MapperScan(basePackages = "org.building.pmservice.*.mapper")
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }

    public static void main(String[] args) throws Exception {
        //获取外部配置文件
//        Properties properties = new Properties();
//        InputStream in = Application.class.getResourceAsStream("application.properties");
//        properties.load(in);
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setDefaultProperties(properties);
//        app.run(args);

        SpringApplication.run(Application.class, args);
    }
}
