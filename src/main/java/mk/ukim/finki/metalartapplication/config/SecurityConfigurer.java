package mk.ukim.finki.metalartapplication.config;

import mk.ukim.finki.metalartapplication.service.UserService;
import mk.ukim.finki.metalartapplication.web.filter.JwtRequestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public SecurityConfigurer(UserService userService, JwtRequestFilter jwtRequestFilter, CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/user-mail/*/verify-account","/authenticate", "/user/register", "/products/*/similar-products", "/products/*/details", "/products").permitAll()
                .anyRequest().authenticated().
                and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
