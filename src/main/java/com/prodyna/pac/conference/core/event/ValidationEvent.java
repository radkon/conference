package com.prodyna.pac.conference.core.event;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public abstract class ValidationEvent<T> {

    private final T validationTarget;

    protected ValidationEvent(T validationTarget) {
        this.validationTarget = validationTarget;
    }

    public T getValidationTarget() {
        return validationTarget;
    }
}
