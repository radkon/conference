package com.prodyna.pac.conference.core.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -6761949640646316176L;

    private List<String> messages;

    public ValidationException() {
        this(new ArrayList<String>());
    }

    public ValidationException(final Collection<String> messages) {
//        super(Response.status(Response.Status.EXPECTATION_FAILED).entity(messages).type(MediaType.APPLICATION_JSON_TYPE).build());
        this.messages = new ArrayList<>(messages);
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(final String message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "messages=" + messages +
                '}';
    }
}
