package com.prodyna.pac.conference.service;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.logging.Logger;

@Stateless
public class TalkChangeObserver {

    private Logger log = Logger.getLogger(getClass().getName());

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jms/queue/TalkChanges")
    private Queue queue;

    @Asynchronous
    public void publishMessage(@Observes(during = TransactionPhase.AFTER_SUCCESS) TalkChangeEvent event) {
        log.info("Talk has changed!");
        jmsContext.createProducer().send(queue, "Talk has changed!");
    }

}
