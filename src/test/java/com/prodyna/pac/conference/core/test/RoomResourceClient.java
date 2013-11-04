package com.prodyna.pac.conference.core.test;

import com.prodyna.pac.conference.room.Room;

import java.net.URI;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class RoomResourceClient extends ResourceClient<Room> {

    protected RoomResourceClient(URI resourcePath) {
        super(resourcePath, Room.class);
    }

}
