package com.example.calender.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventServices eventServices;
    @GetMapping("/getByDate/{date}")
    public List<Event> getevents(@RequestParam  Date date) {
        return eventServices.geteventByDate(date);
    }
    @PostMapping
    public ResponseEntity postEvent(@RequestBody long id,@RequestBody String name,@RequestBody  String description,@RequestBody  String startTime,
                                    @RequestBody  String endTime,@RequestBody Date date, @RequestBody Boolean isNew,Errors errors){
        Event event=new Event();
        if(isNew){
            event=new Event(name,description,startTime,endTime,date);
        }
        else{
            event=new Event(id,name,description,startTime,endTime,date);
        }
        eventServices.save(event);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id) {
        eventServices.removeEvent(id);
        return ResponseEntity.ok().build();
    }
}
