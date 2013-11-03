package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.TalkChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@ApplicationScoped
public class TalkChangeLogger {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public void log(Message message) {
        try {
            log.info("Received message: {}", message.getBody(TalkChangeEvent.class));
        } catch (JMSException e) {
            log.error("Failed to retrieve message from queue 'jms/queue/TalkChanges': {}", e.getMessage());
        }
    }

}
