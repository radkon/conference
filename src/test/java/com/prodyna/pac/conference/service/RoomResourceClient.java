package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.Room;

import java.net.URI;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class RoomResourceClient extends ResourceClient<Room> {

    protected RoomResourceClient(URI resourcePath) {
        super(resourcePath, Room.class);
    }

}
