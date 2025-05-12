package com.rehic.events;

import java.util.List;


public interface EventService {
    Event getEventById(String id);
    List<Event> getEvents();
    Event addEvent(Event event);
}
