package com.groupeisi.bibliotheque.tiles;

import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * ViewResolver de convenance pour {@link TilesView}, équivalent à l'ancien
 * {@code org.springframework.web.servlet.view.tiles3.TilesViewResolver}.
 * Le "nom de vue" retourné par un @Controller (ex: "auteur/list") est transmis
 * tel quel à TilesView, qui le cherche comme nom de &lt;definition&gt; Tiles.
 */
public class TilesViewResolver extends UrlBasedViewResolver {

    public TilesViewResolver() {
        setViewClass(TilesView.class);
    }
}
