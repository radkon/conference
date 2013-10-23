package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.Conference;

import java.net.URI;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class ConferenceResourceClient extends ResourceClient<Conference> {

    protected ConferenceResourceClient(URI resourcePath) {
        super(resourcePath, Conference.class);
    }

}
