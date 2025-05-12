package com.rehic.events;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "events")
@Data
public class Event {
    private String id;
   private String eventName;
    private String eventType;
    private String description;
    private String startDate;
    private String endDate;
    private String time;
    private String location;
    private List<String> rsvp;
}
