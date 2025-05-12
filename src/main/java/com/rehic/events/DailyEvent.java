package com.rehic.events;

import org.springframework.stereotype.Service;

@Service
public class DailyEvent implements EventModule{

    @Override
    public String addEvent(Event event, EventService eventService) {
        Event dailyEvent = eventService.addEvent(event);
        return dailyEvent.getEventName()+ "added successfully";
    }
}
