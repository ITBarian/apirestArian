package cat.itb.m09.apirestarian.security.jwt;

import cat.itb.m09.apirestarian.model.services.ServiceUserDetails;
import cat.itb.m09.apirestarian.security.jwt.jwt.AuthEntryPoint;
import cat.itb.m09.apirestarian.security.jwt.jwt.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//mirar: https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt
//Openwebinars


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ConfSecurityWeb extends WebSecurityConfigurerAdapter {

    private final AuthEntryPoint elmeuEntryPoint;
    private final ServiceUserDetails elmeuUserDetailsService;
    private final PasswordEncoder xifrat;
    private final JWTAuthFilter jwtAuthorizationFilter;

//Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().anyRequest();
//    }



//codi per fer una prova autenticant en memòria "inMemoryAuthentication()"
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(xifrat)
//                .withUser("Montse")
//                .password(xifrat.encode("secret"))
//                .roles("ADMIN"); // és necessari posar tots els camps, fins el rol (authorities)
//    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(elmeuUserDetailsService).passwordEncoder(xifrat);
    }



    @Bean //s'utilitza al Controller del Login d'Usuaris
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /* projecte jwt2OW
    usuaris i passwords de prova
    "Montse", "1234" comença en majúscula
    "Pepi", "1234"
     */


    /* mirar: https://www.adictosaltrabajo.com/2017/09/25/securizar-un-api-rest-utilizando-json-web-tokens/
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .cors()
//                .and()
                .httpBasic()
                .and()
                //    .exceptionHandling().authenticationEntryPoint(elmeuEntryPoint)
                //    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //per poder accedir al h2-console
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()


                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                // .antMatchers(HttpMethod.GET,"/login").permitAll()
                //.antMatchers(HttpMethod.GET, "/me/**").hasRole("ADMIN") //per fer proves del forbidden
                .antMatchers(HttpMethod.GET, "/login/**","/usuaris/**", "/videojocs/**").hasRole("USER")
//                    .antMatchers(HttpMethod.POST, "/usuaris/**", "/videojocs/**").hasRole("USER")
//                    .antMatchers(HttpMethod.PUT, "/videojocs/**").hasRole("USER")
//                    .antMatchers(HttpMethod.DELETE, "/videojocs/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.POST, "/videojocs/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated().and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
}