package ru.dvvar.graduate.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dvvar.graduate.model.Vote;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public class VoteController {

    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    public void vote(Vote vote, int userId) {
        LOG.info("vote {} user with id {}", vote, userId);
    }

    public void cancel(Vote vote, int userId) {
        LOG.info("cancel {} user with id {}", vote, userId);
    }
}
