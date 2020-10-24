package com.example.social_network.autentification;

import com.example.social_network.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSecurity
@EnableWebSocketMessageBroker
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
                                implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/admin", "/profile", "/main", "/messages", "/")
                    .authenticated()
                .and()
                    .logout()
                        .permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                .and()
                    .csrf()
                        .disable();


        //                .and().rememberMe();
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //регистрируем конечную точку, которую клиенты будут использовать, чтобы подключиться к нашему Websocket-серверу. SockJS – для браузеров, которые не поддерживают Websocket.
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
