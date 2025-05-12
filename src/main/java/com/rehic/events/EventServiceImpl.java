package com.rehic.events;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public Event getEventById(String id) {
        return null;
    }

    @Override
    public List<Event> getEvents() {
        return List.of();
    }

    @Override
    public Event addEvent(Event event) {
        return null;
    }
}
