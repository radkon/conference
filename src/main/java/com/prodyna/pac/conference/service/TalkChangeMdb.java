package com.prodyna.pac.conference.service;


import com.prodyna.pac.conference.entity.TalkChangeEvent;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/queue/TalkChanges", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class TalkChangeMdb implements MessageListener {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("Received message: " + message.getBody(TalkChangeEvent.class));
        } catch (JMSException e) {
            logger.severe("Failed to retrieve message from queue 'jms/queue/TalkChanges': " + e.getMessage());
        }
    }
}
