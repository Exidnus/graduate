package ru.dvvar.graduate;

import ru.dvvar.graduate.model.Dish;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.util.matcher.ModelMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.dvvar.graduate.model.BaseEntity.START_SEQ;

/**
 * Created by Dmitriy_Varygin on 17.05.2016.
 */
public class RestaurantTestData {

    public static final ModelMatcher<Restaurant, String> RESTAURANT_MATCHER = new ModelMatcher<>(Restaurant::toString, Restaurant.class);
    public static final ModelMatcher<Menu, String> MENU_MATCHER = new ModelMatcher<>(Menu::toString, Menu.class);

    public static final int RESTAURANT_ID_1 = START_SEQ + 2;
    public static final int RESTAURANT_ID_2 = START_SEQ + 3;
    public static final int RESTAURANT_ID_3 = START_SEQ + 4;
    public static final int MENU_ID_1 = START_SEQ + 5;
    public static final int MENU_ID_2 = START_SEQ + 6;
    public static final int MENU_ID_3 = START_SEQ + 7;
    public static final int MENU_ID_4 = START_SEQ + 8;
    public static final int DISH_ID_1 = START_SEQ + 9;
    public static final int DISH_ID_2 = START_SEQ + 10;
    public static final int DISH_ID_3 = START_SEQ + 11;
    public static final int DISH_ID_4 = START_SEQ + 12;
    public static final int DISH_ID_5 = START_SEQ + 13;
    public static final int DISH_ID_6 = START_SEQ + 14;
    public static final int DISH_ID_7 = START_SEQ + 15;
    public static final int DISH_ID_8 = START_SEQ + 16;
    public static final int DISH_ID_9 = START_SEQ + 17;
    public static final int DISH_ID_10 = START_SEQ + 18;
    public static final int DISH_ID_11 = START_SEQ + 19;
    public static final int DISH_ID_12 = START_SEQ + 20;

    public static final Dish DISH_1 = new Dish(DISH_ID_1, "Тайское обычное блюдо 1", "Описание обычного блюда 1", 155.00f, 0);
    public static final Dish DISH_2 = new Dish(DISH_ID_2, "Тайское обычное блюдо 2", "Описание обычного блюда 2", 45.00f, 1);
    public static final Dish DISH_3 = new Dish(DISH_ID_3, "Тайское обычное блюдо 3", "Описание обычного блюда 3", 25.25f, 2);
    public static final Dish DISH_4 = new Dish(DISH_ID_4, "Тайское праздничное блюдо 1", "Описание праздничного блюда 1", 250.45f, 0);
    public static final Dish DISH_5 = new Dish(DISH_ID_5, "Тайское праздничное блюдо 2", "Описание праздничного блюда 2", 55.00f, 1);
    public static final Dish DISH_6 = new Dish(DISH_ID_6, "Pizza and sushi dish 1", "Description pizza and sushi dish 1", 120.00f, 0);
    public static final Dish DISH_7 = new Dish(DISH_ID_7, "Pizza and sushi dish 2", "Description pizza and sushi dish 2", 100.00f, 1);
    public static final Dish DISH_8 = new Dish(DISH_ID_8, "Pizza and sushi dish 3", "Description pizza and sushi dish 3", 10.45f, 2);
    public static final Dish DISH_9 = new Dish(DISH_ID_9, "Pizza and sushi dish 4", "Description pizza and sushi dish 4", 27.30f, 3);
    public static final Dish DISH_10 = new Dish(DISH_ID_10, "Pizza and sushi dish 5", "Description pizza and sushi dish 1", 5.50f, 4);
    public static final Dish DISH_11 = new Dish(DISH_ID_11, "Coffee and delicious cakes блюдо 1", "Описание блюда Coffee and delicious cakes 1", 110.75f, 0);
    public static final Dish DISH_12 = new Dish(DISH_ID_12, "Coffee and delicious cakes блюдо 2", "Описание блюда Coffee and delicious cakes 2", 25.00f, 1);

    public static List<Dish> DISHES_1 = Arrays.asList(DISH_1, DISH_2, DISH_3);
    public static List<Dish> DISHES_2 = Arrays.asList(DISH_4, DISH_5);
    public static List<Dish> DISHES_3 = Arrays.asList(DISH_6, DISH_7, DISH_8, DISH_9, DISH_10);
    public static List<Dish> DISHES_4 = Arrays.asList(DISH_11, DISH_12);

    public static final Menu MENU_1 = new Menu(MENU_ID_1, "Тайское меню 1", "Стандартное меню", DISHES_1, 15);
    public static final Menu MENU_2 = new Menu(MENU_ID_2, "Тайское меню 2", "Меню для праздничных дней", DISHES_2, 25, 75);
    public static final Menu MENU_3 = new Menu(MENU_ID_3, "Pizza and sushi menu 1", "Standart menu", DISHES_3, 43);
    public static final Menu MENU_4 = new Menu(MENU_ID_4, "Coffee and delicious cakes standart menu", "Just standart menu", DISHES_4);

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, "Тайский ресторан", "Маленький ресторан с тайской едой, шеф-повар родом из Таиланда!", MENU_2);
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID_2, "Pizza and sushi", "Интересное смешение культур: в этом ресторане подают как пиццу, так и суши", MENU_3);
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID_3, "Coffee and delicious cakes", "Любите кофе с вкусными пирожными? Тогда вам сюда.");

    public static final List<Restaurant> TWO_RESTAURANTS_EXCEPT_FIRST = Arrays.asList(RESTAURANT_2, RESTAURANT_3)
            .stream()
            .sorted((r1, r2) -> r1.getName().compareTo(r2.getName()))
            .collect(Collectors.toList());

    public static final Dish DISH_FOR_SAVE_1 = new Dish("Dish for save 1", "Description dish for save 1", 25.50f, 0);
    public static final Dish DISH_FOR_SAVE_2 = new Dish("Dish for save 2", "Description dish for save 2", 35.50f, 1);
    public static final Menu MENU_FOR_SAVE = new Menu("Menu for save", "Description menu for save",
            Arrays.asList(DISH_FOR_SAVE_1, DISH_FOR_SAVE_2));
    public static final Restaurant RESTAURANT_FOR_SAVE = new Restaurant("Restaurant for save", "Description restaurant for save",
            MENU_FOR_SAVE);

    public static final Dish NEW_DISH = new Dish("New Dish", "New Dish in updated menu", 40.00f, 0);
    public static final Menu UPDATED_MENU = new Menu(MENU_ID_3, "Updated Menu", "Description of updated menu",
            Collections.singletonList(NEW_DISH));

    static {
        RESTAURANT_1.setMenus(Arrays.asList(MENU_1, MENU_2)
                .stream()
                .sorted((m1, m2) -> m2.getAllUpvotes() - m1.getAllUpvotes())
                .peek(m -> m.setDishes(m.getDishes()
                        .stream()
                        .sorted((d1, d2) -> d1.getPosition() - d2.getPosition())
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)
            .stream()
            .sorted((r1, r2) -> r1.getName().compareTo(r2.getName()))
            .collect(Collectors.toList());

    public static void main(String[] args) {
        System.out.println(MENU_1);
    }
}
