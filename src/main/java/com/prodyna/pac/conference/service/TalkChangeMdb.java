package com.prodyna.pac.conference.service;


import com.prodyna.pac.conference.entity.TalkChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/queue/TalkChanges", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class TalkChangeMdb implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        try {
            log.info("Received message: {}", message.getBody(TalkChangeEvent.class));
        } catch (JMSException e) {
            log.error("Failed to retrieve message from queue 'jms/queue/TalkChanges': {}", e.getMessage());
        }
    }
}
