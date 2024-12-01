package gtu.cse.se.altefdirt.aymoose.core.infra.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtConverter;


@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Service
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtConverter jwtConverter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                /* .cors(cors -> corsConfigurationSource()) */
               /*  .cors(cors -> cors.disable()) */
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers(HttpMethod.GET, "/**").permitAll()  // Allow all GET requests
                    .requestMatchers(HttpMethod.POST, "/api/v1/accounts").anonymous()
                    .anyRequest().authenticated() // Require authentication for all other requests
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)))
                
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                /* .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())) */;
                        

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200","https://app.aymoose.devodev.online", "https://sahancepte.com", "*"));
        configuration.setAllowCredentials(false);
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(),
                HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
        configuration.setExposedHeaders(
                Arrays.asList(SecurityConstants.AUTHORIZATION, SecurityConstants.CONTENT_TYPE, "token"));
        configuration.setAllowedHeaders(
                Arrays.asList(SecurityConstants.AUTHORIZATION, SecurityConstants.CONTENT_TYPE, "token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        return source;
    } 

   /*  @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            log.warn("Authentication failure : {}", exception.getMessage());
            HttpStatus status;
            if (exception instanceof DisabledException) {
                status = HttpStatus.FORBIDDEN;
            } else {
                status = HttpStatus.UNAUTHORIZED;
            }
            response.setStatus(status.value());
            String jwtResponse = objectMapper.writeValueAsString(ResponseEntity.status(status.value()).body(status));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(jwtResponse);
            response.getWriter().flush();
        };
    } */

/*     @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            log.warn("Authentication failed for request:{}, with cause:{}", request.getRequestURI(),
                    authException.getMessage());
            Response<CustodyErrorResponse> errorResponse = ResponseBuilder.build(HttpStatus.UNAUTHORIZED.value(),
                    AUTH_FAILED);
            String jwtResponse = objectMapper.writeValueAsString(errorResponse);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(jwtResponse);
            response.getWriter().flush();
        };
    } */

    /* @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            log.warn("Access denied for request:{} with reason:{}", request.getRequestURI(),
                    accessDeniedException.getMessage());
            String jwtResponse = objectMapper
                    .writeValueAsString(ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(HttpStatus.FORBIDDEN));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(jwtResponse);
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    } */

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}