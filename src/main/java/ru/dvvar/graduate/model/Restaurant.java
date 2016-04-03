package ru.dvvar.graduate.model;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class Restaurant extends NamedEntity {

    private Menu currentMenu;

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
