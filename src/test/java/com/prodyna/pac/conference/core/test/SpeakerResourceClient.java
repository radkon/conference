package com.prodyna.pac.conference.core.test;


import com.prodyna.pac.conference.speaker.Speaker;

import java.net.URI;

public class SpeakerResourceClient extends ResourceClient<Speaker> {

    public SpeakerResourceClient(final URI resourcePath) {
        super(resourcePath, Speaker.class);
    }

}
