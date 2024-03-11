package org.example.backend.Controllers;

import org.example.backend.Entities.Attendee;
import org.example.backend.Services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<List<Attendee>> getAllAttendees() {
        List<Attendee> attendees = attendeeService.getAllAttendees();
        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(@PathVariable Long id) {
        return attendeeService.getAttendeeById(id)
                .map(attendee -> new ResponseEntity<>(attendee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Attendee> createAttendee(@RequestBody Attendee attendee) {
        Attendee createdAttendee = attendeeService.createAttendee(attendee);
        return new ResponseEntity<>(createdAttendee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> updateAttendee(@PathVariable Long id,
                                                   @RequestBody Attendee updatedAttendee) {
        Attendee updated = attendeeService.updateAttendee(id, updatedAttendee);
        return (updated != null)
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendee(@PathVariable Long id) {
        attendeeService.deleteAttendee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
