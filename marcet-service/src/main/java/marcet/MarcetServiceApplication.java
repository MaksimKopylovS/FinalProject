package marcet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients
@SpringBootApplication
@PropertySource("classpath:/secured.properties")
public class MarcetServiceApplication {
    public static void main(String args[]) {SpringApplication.run(MarcetServiceApplication.class, args); }
}
