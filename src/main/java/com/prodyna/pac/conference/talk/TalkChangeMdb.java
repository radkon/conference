package com.prodyna.pac.conference.talk;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/queue/TalkChanges", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class TalkChangeMdb implements MessageListener {

    @Inject
    private TalkChangeLogger logger;

    @Override
    public void onMessage(Message message) {
        logger.log(message);
    }
}
