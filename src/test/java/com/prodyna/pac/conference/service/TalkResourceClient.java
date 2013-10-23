package com.prodyna.pac.conference.service;


import com.prodyna.pac.conference.entity.Talk;

import java.net.URI;

public class TalkResourceClient extends ResourceClient<Talk> {

    public TalkResourceClient(final URI resourcePath) {
        super(resourcePath, Talk.class);
    }

}
