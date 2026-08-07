package com.groupeisi.bibliotheque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuration Spring MVC : uniquement les préoccupations "web" (résolution des vues,
 * ressources statiques). Les beans de persistance (SessionFactory, DataSource,
 * TransactionManager) ont été déplacés dans {@code PersistenceConfig} (module repository)
 * à l'étape 3 — ce fichier ne s'en occupe plus, il n'a même plus besoin de savoir qu'ils
 * existent.
 * <p>
 * {@code @ComponentScan("com.groupeisi.bibliotheque")} parcourt tout le classpath sous ce
 * package de base : il trouvera aussi bien les {@code @Controller} d'ihm-jsp que les
 * {@code @Service}/{@code @Repository} de metier/repository et le
 * {@code @Configuration PersistenceConfig} de repository, tous présents dans WEB-INF/lib
 * une fois le WAR construit (grâce aux dépendances Maven en cascade
 * ihm-jsp -> metier -> repository -> common).
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.groupeisi.bibliotheque"})
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
