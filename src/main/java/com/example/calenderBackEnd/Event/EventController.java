package com.example.calenderBackEnd.Event;


import com.example.calenderBackEnd.Category.Category;
import com.example.calenderBackEnd.Category.CategoryServices;
import com.example.calenderBackEnd.Util.JasyptConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
@CrossOrigin("*")
public class EventController {
    @Autowired
    EventServices eventServices;
    @Autowired
    JasyptConfig jasyptConfig;
    @Autowired
    CategoryServices categoryServices;
    @GetMapping("/{id}/{date}")
    public List<Event> getevent(@PathVariable Long id,@PathVariable Date date) throws IOException {
        List<Event> events=eventServices.geteventsByDateId(date,id);

        for(int i=0;i< events.size();i++){
            Event event=events.get(i);
            event.setDescription(jasyptConfig.encryptor().decrypt(event.getDescription()));
            events.set(i,event);
        } 
        return events;
    }
    @PostMapping
    public ResponseEntity createevent(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        Event event=new Event();
        try{
            event=eventServices.geteventById((long)payLoad.get("id"));
        }
        catch(Exception e){

        }

        event.setDescription(jasyptConfig.encryptor().encrypt((String)payLoad.get("description")));

        event.setName((String)payLoad.get("name"));
        event.setDateOfEvent((Date)payLoad.get("dateOfEvent"));
        Category category=categoryServices.getcategoryById((long)payLoad.get("categoryId"));
        event.setCategory(category);
        eventServices.saveevent(event);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        eventServices.removeevent(id);
        return ResponseEntity.ok().build();
    }
}
