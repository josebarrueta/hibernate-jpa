package org.oss.tx.listeners;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHibernateListener implements PreInsertEventListener, PostInsertEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestHibernateListener.class);

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        LOGGER.info("onPostInsert - event={}", postInsertEvent.getId());
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        LOGGER.info("onPreInsert - event={}", preInsertEvent.getId());
        return false;
    }
}
