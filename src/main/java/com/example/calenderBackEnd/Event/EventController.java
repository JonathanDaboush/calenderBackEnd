package com.example.calenderBackEnd.Event;


import com.example.calenderBackEnd.User.User;
import com.example.calenderBackEnd.User.UserServices;
import com.example.calenderBackEnd.Util.JasyptConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;

@RestController
@RequestMapping("/event")
@CrossOrigin("*")
public class EventController {
    @Autowired
    EventServices eventServices;
    @Autowired
    JasyptConfig jasyptConfig;
    @Autowired
    UserServices userServices;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}/{date}")
    public ResponseEntity<List<Event>> getevent(@PathVariable Long id, @PathVariable Date date) throws IOException {
        List<Event> events = eventServices.geteventsByDateId(date, id);

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            event.setDescription(jasyptConfig.encryptor().decrypt(event.getDescription()));
            events.set(i, event);
        }

        List<Event> result = objectMapper.convertValue(events, new TypeReference<List<Event>>(){});
        return ResponseEntity.ok().body(result);
    }
    @PostMapping
    public ResponseEntity createevent(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        Event event=new Event();
        try{
            event=eventServices.geteventById(Long.parseLong(String.valueOf(Integer.parseInt(String.valueOf((Integer)payLoad.get("id"))))));
        }
        catch(Exception e){

        }

        Object value = payLoad.get("userId");
long userId;
        if (value instanceof Integer || value instanceof Long) {
            // The value is a valid integer
            Long id = Long.valueOf(value.toString());
            userId = id; // assign the parsed Long value to userId
            // do something with id
        } else if (value instanceof String) {
            // The value is a string
            String str = (String) value;
            // try to parse the string as a long value
            try {
                Long id = Long.parseLong(str);
                userId = id; // assign the parsed Long value to userId
                // do something with id
            } catch (NumberFormatException e) {
                // the string is not a valid long value
                throw new IllegalArgumentException("Invalid value for userId: " + value);
            }
        } else {
            // The value is not a valid integer or string
            throw new IllegalArgumentException("Invalid value for userId: " + value);
        }


        User user=userServices.getuserById(userId);
        event.setDescription(jasyptConfig.encryptor().encrypt((String)payLoad.get("description")));
        event.setUser(user);
        event.setName((String)payLoad.get("name"));
        event.setDateOfEvent(Date.valueOf((String)payLoad.get("dateOfEvent")));

        eventServices.saveevent(event);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        eventServices.removeevent(id);
        return ResponseEntity.ok().build();
    }

}
