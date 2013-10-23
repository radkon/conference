package com.prodyna.pac.conference.service;


import com.prodyna.pac.conference.entity.Speaker;

import java.net.URI;

public class SpeakerResourceClient extends ResourceClient<Speaker> {

    public SpeakerResourceClient(final URI resourcePath) {
        super(resourcePath, Speaker.class);
    }

}
