package com.example.calender.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Component
public class EventServices {
    @Autowired
    EventRepository eventRepository;


    public void save(Event event){
        eventRepository.save(event);
    }
    public void removeEvent(long id){
        eventRepository.deleteById(id);

    }
    public List<Event> geteventByDate(Date date){
        return eventRepository.findByDate(date);
    }
}
