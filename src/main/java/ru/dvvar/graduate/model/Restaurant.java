package ru.dvvar.graduate.model;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class Restaurant extends NamedEntity {

    private Menu currentMenu;

    private List<Menu> menus;

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
