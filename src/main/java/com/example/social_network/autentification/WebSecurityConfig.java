package com.example.social_network.autentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("u")
                    .password("p")
                    .authorities("ROLE_USER");



//        http
//                .csrf()
//                    .disable()
//                .authorizeRequests()
//                    .antMatchers("/signup").not().fullyAuthenticated()
//                    .antMatchers("/main").hasRole("USER")
//                    .antMatchers("/", "/resources/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/");

//        http
//                .authorizeRequests()
//                        .antMatchers( "/", "/main", "/signup").permitAll()
//                        .anyRequest().authenticated()
//                        .and()
//                .formLogin()
//                        .loginPage("/login")
//                        .permitAll()
//                        .and()
//                .logout()
//                        .permitAll();
    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select name, password from users where name = ?")
//            ;
//    }
}
