package com.groupeisi.bibliotheque.config;

import com.groupeisi.bibliotheque.tiles.TilesConfigurer;
import com.groupeisi.bibliotheque.tiles.TilesViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration Spring MVC du module ihm-tiles.
 *
 * <p>Contrairement à ihm-jsp (InternalResourceViewResolver -> JSP directes),
 * les noms de vue renvoyés par les @Controller sont ici résolus par
 * {@link TilesViewResolver} vers des &lt;definition&gt; Tiles déclarées dans
 * /WEB-INF/tiles.xml (voir ce fichier), chacune assemblant un layout commun
 * (WEB-INF/views/tiles/layout/main-layout.jsp) + un fragment de contenu.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.groupeisi.bibliotheque")
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /** Boot le TilesContainer à partir de /WEB-INF/tiles.xml (voir TilesConfigurer). */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("/WEB-INF/tiles.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }

    /** Traduit un nom de vue (ex: "auteur/list") en definition Tiles du même nom. */
    @Bean
    public TilesViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }
}
