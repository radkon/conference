package com.prodyna.pac.conference.talk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class TalkChangeObserver {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jms/queue/TalkChanges")
    private Queue queue;

    @Asynchronous
    public void publishMessage(@Observes(during = TransactionPhase.AFTER_SUCCESS) TalkChangeEvent event) {
        log.info("Talk has changed!");
        jmsContext.createProducer().send(queue, event);
    }

}
