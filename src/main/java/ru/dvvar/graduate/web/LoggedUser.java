package ru.dvvar.graduate.web;

import static ru.dvvar.graduate.model.BaseEntity.START_SEQ;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class LoggedUser {

    public static int getId() {
        return START_SEQ;
    }
}
