package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Flight;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Services.FlightServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.TicketServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TicketCantroller {


    @Autowired
    private TicketServices ticketServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private FlightServices flightServices;


    @DeleteMapping("/cancel/ticket/by/pnr")
    public ResponseEntity<?> deleteTicketBypnr(@RequestParam String pnr) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userServices.findUserByUserName(username);


        Ticket ticket = ticketServices.findTicketBypnr(pnr);

        Flight flight=flightServices.findFlightByFlightNumber(ticket.getFlightnumber());

        if (ticket == null) {
            return new ResponseEntity<>("Ticket not found for PNR: " + pnr, HttpStatus.NOT_FOUND);
        }

        user.getTicketList().removeIf(x -> x.getPnr().equals(pnr));

        int updateFlightSeats = flight.getAvlseat()+ticket.getSeatnumber().size();

        flight.setAvlseat(updateFlightSeats);

        userServices.saveprevious(user);

        ticketServices.deleteBookingByPnr(pnr);

        return new ResponseEntity<>("Booking deleted for PNR: " + pnr, HttpStatus.OK);
    }


    @GetMapping("/get/my/all/tickets")
    public ResponseEntity<?> getMyAllTickets(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getName();

        User user=userServices.findUserByUserName(username);

        List<Ticket> listOfTicket=user.getTicketList();

        HashMap<String,Object> map=new HashMap<>();

        if(listOfTicket.isEmpty()){

            map.put("There Is No Ticket For User -> ",username);
        }else {

            map.put("Tickets are -> ",listOfTicket);
        }

        return ResponseEntity.ok(map);
    }



}
