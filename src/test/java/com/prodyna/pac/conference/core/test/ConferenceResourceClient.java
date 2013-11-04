package com.prodyna.pac.conference.core.test;

import com.prodyna.pac.conference.conference.Conference;

import java.net.URI;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class ConferenceResourceClient extends ResourceClient<Conference> {

    protected ConferenceResourceClient(URI resourcePath) {
        super(resourcePath, Conference.class);
    }

}
