package ru.dvvar.graduate.to;

import ru.dvvar.graduate.model.Menu;

/**
 * Created by dmitriy_varygin on 16.06.16.
 */
public class TransformerUtil {

    private TransformerUtil() {

    }

    public static Menu updateFromMenuTo(Menu menu, MenuTo menuTo) {
        menu.setDescription(menuTo.getDescription());
        menu.setDishes(menuTo.getDishes());
        return menu;
    }

    public static MenuTo fromMenu(Menu menu) {
        return new MenuTo(menu.getId(), menu.getDescription(), menu.getDishes());
    }
}
