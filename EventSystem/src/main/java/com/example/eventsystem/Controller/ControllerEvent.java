package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.Api;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/event")
public class ControllerEvent {

    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public Api addEvent(@RequestBody Event event){
        events.add(event);
        return new Api("Event added successfully");
    }
    @GetMapping("/get")
    public ArrayList<Event> getAllEvents() {
        return events;
    }

    @PutMapping("/update/{id}")
    public Api updateEvent(@PathVariable String id, @RequestBody Event event){
        for(Event e:events){
            if(e.getId().equals(id)){
                e.setDescription(event.getDescription());
                e.setStartDate(event.getStartDate());
                e.setEndDate(event.getEndDate());
                e.setCapacity(event.getCapacity());
                return  new Api("Event updated successfully");
            }
        }
        return new Api("Event not found");
    }
    @DeleteMapping("/{id}")
    public Api deleteEvent(@PathVariable String id){
        for(Event e:events){
            if(e.getId().equals(id)){
                events.remove(e);
                return new Api("Event deleted successfully");
            }
        }
   return new Api("Event not found");
    }
    @GetMapping("/chang/{capacity}")
    public ArrayList<Event> changeCapacity(@PathVariable int capacity){
        ArrayList<Event> newEvents = new ArrayList<>();
        for(Event e:events){
            if(e.getCapacity() == capacity){
                newEvents.add(e);
                return  newEvents;
            }
        }
    return newEvents;
    }

    @GetMapping("/search/{id}")
    public ArrayList<Event> searchEvents(@PathVariable String id){
        ArrayList<Event> newEvents = new ArrayList<>();
        for(Event e:events){
            if(e.getId().equals(id)){
                newEvents.add(e);
                return  newEvents;
            }
        }
    return newEvents;
    }

}
