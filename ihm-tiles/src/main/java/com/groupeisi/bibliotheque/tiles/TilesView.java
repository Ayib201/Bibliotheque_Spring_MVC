package com.groupeisi.bibliotheque.tiles;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.renderer.DefinitionRenderer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.render.Renderer;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.JstlUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.util.Map;

/**
 * {@link org.springframework.web.servlet.View} qui délègue le rendu à Tiles :
 * la propriété "url" (héritée de AbstractUrlBasedView, remplie par
 * {@link TilesViewResolver}) est interprétée comme le nom d'une
 * {@code <definition>} déclarée dans tiles.xml.
 *
 * <p>Réimplémentation allégée de l'ancien
 * {@code org.springframework.web.servlet.view.tiles3.TilesView} (supprimé de
 * Spring Framework depuis la 6.0, voir {@link TilesConfigurer}).
 */
public class TilesView extends AbstractUrlBasedView {

    private ApplicationContext tilesApplicationContext;

    private Renderer renderer;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        ServletContext servletContext = getServletContext();
        Assert.state(servletContext != null, "No ServletContext");
        this.tilesApplicationContext = ServletUtil.getApplicationContext(servletContext);
        TilesContainer container = TilesAccess.getContainer(this.tilesApplicationContext);
        this.renderer = new DefinitionRenderer(container);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        Assert.state(this.renderer != null, "No Renderer set");

        // Expose les attributs du ModelAndView comme attributs de requête, pour
        // que les tuiles JSP (header/footer/contenu) puissent les lire avec
        // JSTL (<c:out value="${...}"/>), exactement comme dans ihm-jsp.
        exposeModelAsRequestAttributes(model, request);
        JstlUtils.exposeLocalizationContext(new RequestContext(request, getServletContext()));

        Request tilesRequest = new ServletRequest(this.tilesApplicationContext, request, response);
        this.renderer.render(getUrl(), tilesRequest);
    }
}
