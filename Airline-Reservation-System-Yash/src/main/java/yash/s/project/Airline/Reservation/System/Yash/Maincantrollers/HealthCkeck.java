package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;

import java.time.LocalDateTime;

@RestController
@RequestMapping("check")
public class HealthCkeck {


    @GetMapping
    public ResponseEntity<?> healthCheck() {

        String message = " YashAirways Reservation System backend is running — " + LocalDateTime.now();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
