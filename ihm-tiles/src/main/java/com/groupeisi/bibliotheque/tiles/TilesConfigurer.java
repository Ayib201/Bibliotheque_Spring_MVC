package com.groupeisi.bibliotheque.tiles;

import jakarta.servlet.ServletContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.definition.DefinitionsReader;
import org.apache.tiles.definition.dao.BaseLocaleUrlDefinitionDAO;
import org.apache.tiles.definition.dao.CachingLocaleUrlDefinitionDAO;
import org.apache.tiles.definition.digester.DigesterDefinitionsReader;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.locale.LocaleResolver;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.ApplicationResource;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.startup.DefaultTilesInitializer;
import org.apache.tiles.startup.TilesInitializer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Boot et expose un {@link TilesContainer} pour l'application, à utiliser par
 * {@link TilesView} / {@link TilesViewResolver}.
 *
 * <p>Ceci réimplémente, en version très allégée, l'ancien
 * {@code org.springframework.web.servlet.view.tiles3.TilesConfigurer} : Spring
 * Framework a supprimé toute son intégration Apache Tiles à partir de la
 * version 6.0 ("Several outdated Servlet-based integrations have been
 * dropped: ... Apache Tiles"). On ne dépend donc plus que de Tiles lui-même
 * (module tiles-core/tiles-servlet du fork jakarta, voir le pom du module et
 * le README de ihm-tiles), avec le même enchaînement que la classe Spring
 * d'origine : DefaultTilesInitializer -&gt; BasicTilesContainerFactory -&gt;
 * TilesContainer stocké dans le ServletContext via TilesAccess.
 *
 * <p>Déclarée comme bean Spring (voir {@link com.groupeisi.bibliotheque.config.SpringWebConfig}),
 * elle reçoit son ServletContext automatiquement via {@link ServletContextAware}.
 */
public class TilesConfigurer implements ServletContextAware, InitializingBean, DisposableBean {

    /** Valeur par défaut identique à celle de l'ancien TilesConfigurer Spring. */
    private String[] definitions = { "/WEB-INF/tiles.xml" };

    private boolean checkRefresh = false;

    private ServletContext servletContext;

    private TilesInitializer tilesInitializer;

    /** Emplacement(s) du/des fichier(s) de définitions Tiles. Défaut : "/WEB-INF/tiles.xml". */
    public void setDefinitions(String... definitions) {
        this.definitions = definitions;
    }

    /** Si true, Tiles revérifie les fichiers de définitions à chaque requête (utile en dev). */
    public void setCheckRefresh(boolean checkRefresh) {
        this.checkRefresh = checkRefresh;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(this.servletContext != null, "No ServletContext available");
        // IMPORTANT : on construit un ApplicationContext Tiles neuf ici, on ne va
        // PAS le chercher via ServletUtil.getApplicationContext(...) — à ce stade
        // (bootstrap), rien n'est encore enregistré dans le ServletContext, donc
        // ce lookup renvoie null (c'est justement initialize() ci-dessous qui va
        // l'enregistrer, via ApplicationAccess.register(...), pour que TilesView
        // puisse ensuite le retrouver avec ServletUtil.getApplicationContext(...)).
        // C'est exactement ce que faisait l'ancien TilesConfigurer de Spring, qui
        // instanciait lui aussi son propre ApplicationContext au lieu d'en chercher
        // un déjà existant.
        ApplicationContext tilesApplicationContext = new ServletApplicationContext(this.servletContext);
        this.tilesInitializer = new LocalTilesInitializer();
        this.tilesInitializer.initialize(tilesApplicationContext);
    }

    @Override
    public void destroy() {
        if (this.tilesInitializer != null) {
            this.tilesInitializer.destroy();
        }
    }

    private class LocalTilesInitializer extends DefaultTilesInitializer {
        @Override
        protected AbstractTilesContainerFactory createContainerFactory(ApplicationContext context) {
            return new LocalTilesContainerFactory();
        }
    }

    private class LocalTilesContainerFactory extends BasicTilesContainerFactory {

        @Override
        protected List<ApplicationResource> getSources(ApplicationContext applicationContext) {
            List<ApplicationResource> result = new LinkedList<>();
            for (String definition : definitions) {
                Collection<ApplicationResource> resources = applicationContext.getResources(definition);
                if (resources != null) {
                    result.addAll(resources);
                }
            }
            return result;
        }

        @Override
        protected BaseLocaleUrlDefinitionDAO instantiateLocaleDefinitionDao(
                ApplicationContext applicationContext, LocaleResolver resolver) {
            BaseLocaleUrlDefinitionDAO dao = super.instantiateLocaleDefinitionDao(applicationContext, resolver);
            if (checkRefresh && dao instanceof CachingLocaleUrlDefinitionDAO cachingDao) {
                cachingDao.setCheckRefresh(true);
            }
            return dao;
        }

        @Override
        protected DefinitionsReader createDefinitionsReader(ApplicationContext context) {
            DigesterDefinitionsReader reader = (DigesterDefinitionsReader) super.createDefinitionsReader(context);
            // Pas de DTD à valider ici : nos tiles.xml restent volontairement simples.
            reader.setValidating(false);
            return reader;
        }
    }
}
