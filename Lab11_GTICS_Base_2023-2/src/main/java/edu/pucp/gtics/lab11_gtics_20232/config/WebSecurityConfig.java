/*
package edu.pucp.gtics.lab11_gtics_20232.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {
    final DataSource dataSource;


    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "SELECT correo, password, enabled FROM usuarios WHERE correo = ?";
        String sql2 = "SELECT correo, autorizacion FROM usuarios WHERE correo = ? and enabled = 1";
        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);

        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.httpBasic();

        http.authorizeRequests()
                .antMatchers("/juegos/**").hasAnyAuthority("ADMIN")
                .antMatchers("/distribuidora/**").hasAnyAuthority("ADMIN")
*/
/*
                .antMatchers("/usuarios/listaUsuarios").hasAnyAuthority("ADMIN")
*//*

                .antMatchers("/usuarios/actualizarUsuario").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().permitAll();

        return http.build();
    }

}*/
