package com.prodyna.pac.conference.core.test;


import com.prodyna.pac.conference.talk.Talk;

import java.net.URI;

public class TalkResourceClient extends ResourceClient<Talk> {

    public TalkResourceClient(final URI resourcePath) {
        super(resourcePath, Talk.class);
    }

}
