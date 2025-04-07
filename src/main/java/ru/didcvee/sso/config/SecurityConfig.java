package ru.didcvee.sso.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Активация конфигурации Spring Security
@RequiredArgsConstructor // Lombok-аннотация для генерации final-конструктора
@Configuration(proxyBeanMethods = false) // Явно указываем, что конфигурация не использует прокси (ускоряет запуск)
public class SecurityConfig {

    /**
     * Настраивает цепочку фильтров безопасности для обработки HTTP-запросов.
     * <p>
     * - Требует аутентификацию для любого запроса.
     * - Включает стандартную форму логина от Spring Security.
     *
     * @param http конфигурация безопасности
     * @return фильтр безопасности
     * @throws Exception если возникает ошибка конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.formLogin(Customizer.withDefaults()).build();
    }

    /**
     * Реализация сервиса пользователей на основе памяти (in-memory).
     * <p>
     * Используется исключительно для целей разработки/тестирования.
     * Пользователь:
     *   - username: user
     *   - password: 2121 (хранится как plaintext с {noop} — небезопасно для продакшена)
     *   - роль: ROLE_USER
     * <p>
     * !!! В продакшене заменить на реализацию, подключающуюся к БД или внешней системе.
     *
     * @return in-memory UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetails = new User(
                "user",
                "{noop}2121",
                AuthorityUtils.createAuthorityList("ROLE_USER")
        );

        return new InMemoryUserDetailsManager(userDetails);
    }
}
