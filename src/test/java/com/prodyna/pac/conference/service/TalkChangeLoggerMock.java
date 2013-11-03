package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.TalkChangeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Alternative
@ApplicationScoped
public class TalkChangeLoggerMock extends TalkChangeLogger {

    private TalkChangeEvent event;

    @Override
    public void log(Message message) {
        try {
            event = message.getBody(TalkChangeEvent.class);
        } catch (JMSException e) {
            event = null;
        }
    }

    public TalkChangeEvent getEvent() {
        return event;
    }

}
