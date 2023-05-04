package com.example.calenderBackEnd.Event;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class EventServices {
    EventRepository eventRepository;
    public Event geteventById(long id){
        return eventRepository.getReferenceById(id);
    }
    public void saveevent(Event event){
        eventRepository.save(event);
    }
    public void removeevent(long id){
        eventRepository.deleteById(id);
    }
    public List<Event> geteventsByDateId(Date date, long id){
        return eventRepository.findEventByDate(date,id);
    }

}
