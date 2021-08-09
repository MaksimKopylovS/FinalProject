package marcet.configs;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Защита от подделки межсайтовых запросов, отключена
                .authorizeRequests() // метод для ограничения доступа
//                .antMatchers("/").permitAll()
//                .antMatchers("/registration/**").permitAll()
//                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/get-products-all/**").authenticated()
                .antMatchers("service/order").authenticated() // antMatchers определяет какую ссылку и каким способом защищать
                .antMatchers("service/get-all-admin").hasAnyRole("ROLE_ADMIN")
//                .antMatchers("/**").authenticated()
                .anyRequest()
                .permitAll() // anyRequest определяет защиту всех ссылок
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
