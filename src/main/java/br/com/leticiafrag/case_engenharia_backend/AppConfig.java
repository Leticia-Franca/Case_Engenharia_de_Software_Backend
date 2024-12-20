package br.com.leticiafrag.case_engenharia_backend;

import br.com.leticiafrag.case_engenharia_backend.domain.UserService;
import br.com.leticiafrag.case_engenharia_backend.ports.output.UserOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService UserService(UserOutputPort userOutputPort) {
        return new UserService(userOutputPort);
    }

}
