package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Flight;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.FlightRepository;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;
import yash.s.project.Airline.Reservation.System.Yash.Services.FlightServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;

import java.util.List;

@RestController
@RequestMapping("/admin/flight")
public class FlightCantroller {


    @Autowired
    private FlightServices flightServices;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserServices userServices;



    @GetMapping("/get/all/flights")
    public ResponseEntity<?> getAllFlight(){

        List<Flight> allflights=flightServices.findAllFlight();

        return new ResponseEntity<>(allflights, HttpStatus.OK);
    }


    @PostMapping("/add/flight")
    public ResponseEntity<?> addFlight(@RequestBody Flight newflight){

    flightServices.addFlight(newflight);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/delete/flight/by/flightnumber")
    public ResponseEntity<String> deleteFlightByFlightNumber(@RequestParam int flightnumber) {

        Flight flight = flightServices.findFlightByFlightNumber(flightnumber);

        if (flight == null) {
            return new ResponseEntity<>(" Flight not found with flight number: " + flightnumber, HttpStatus.NOT_FOUND);
        }

        flightServices.deleteFlightByFlightNumber(flightnumber);


        List<Ticket> tickets = ticketRepository.findByflightnumber(flightnumber);

        if (tickets == null || tickets.isEmpty()) {
            return new ResponseEntity<>(" Flight deleted, but no tickets were found associated with this flight.", HttpStatus.OK);
        }

        ticketRepository.deleteAll(tickets);


        for (Ticket ticket : tickets) {
            User user = userServices.findUserByUserName(ticket.getPassengername());

            if (user != null && user.getTicketList() != null && !user.getTicketList().isEmpty()) {
                boolean removed = user.getTicketList().removeIf(t -> t.getFlightnumber() == flightnumber);

                if (removed) {
                    userServices.saveprevious(user);
                }
            }
        }

        return new ResponseEntity<>(
                " Flight deleted with flight number: " + flightnumber +
                        ".\n All tickets associated with this flight have been successfully cancelled.",
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/all/flights")
    public ResponseEntity<?> deleteAllFlight(){

        flightServices.deleteAllFlights();

        return new ResponseEntity<>("All Flight Deleted" ,HttpStatus.OK);
    }


}
