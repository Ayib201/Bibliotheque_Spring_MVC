package com.groupeisi.bibliotheque.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Remplace web.xml pour la partie "bootstrap Spring MVC" : enregistre le
 * DispatcherServlet et le fait pointer sur {@link SpringWebConfig}.
 * Identique en tout point à celui de ihm-jsp — seule la configuration Spring
 * (SpringWebConfig) change de moteur de vue.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Pas de contexte "racine" séparé ici : tout (services métier compris)
        // est chargé dans le contexte du DispatcherServlet, comme dans ihm-jsp.
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
