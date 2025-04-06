package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Booking;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Flight;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Generator.TicketGenerator;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.BookingRepository;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;
import yash.s.project.Airline.Reservation.System.Yash.Services.BookingServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.FlightServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserCantroller {

    @Autowired
    private UserServices userServices;

    @Autowired
    private BookingServices bookingServices;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightServices flightServices;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    TicketGenerator ticketGenerator;

    @GetMapping("/get/my/all/bookings/and/history")
    public ResponseEntity<?> getAllBookingsByUserName(){


        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String userName=authentication.getName();

        User user=userServices.findUserByUserName(userName);

        List<Booking> Bookings=user.getBookingList();


        return new ResponseEntity<>(Bookings, HttpStatus.OK);
    }

    @PutMapping("/update/my/username/and/password")
    public ResponseEntity<?> updateUserName(@RequestBody User updateUser){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getName();

      User userInDb=userServices.findUserByUserName(username);

      try {
          if (userInDb != null){

              userInDb.setUsername(updateUser.getUsername());
              userInDb.setPassword(updateUser.getPassword());
              userServices.save(userInDb);

              return new ResponseEntity<>(HttpStatus.ACCEPTED);
          }
      }
      catch (Exception e) {
          throw new RuntimeException(e);

      }

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @DeleteMapping("/delete/my/user/id")
    public ResponseEntity<?> deleteUserByUserName(){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String userName=authentication.getName();

        User user=userServices.findUserByUserName(userName);

        bookingRepository.deleteAll(user.getBookingList());

        ticketRepository.deleteAll(user.getTicketList());

        userServices.deleteUserByUserName(userName);

        return new ResponseEntity<>("user deleted",HttpStatus.OK);
    }


    @DeleteMapping("delete/my/all/bookings/and/history")
    public ResponseEntity<?> cancelMyAllBookings(){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getName();

        User user=userServices.findUserByUserName(username);

        bookingRepository.deleteAll(user.getBookingList());

        user.setBookingList(null);

        ticketRepository.deleteAll(user.getTicketList());

        user.setTicketList(null);

        userServices.saveprevious(user);

        return new ResponseEntity<>("MY ALL BOOKINGS DELETED",HttpStatus.ACCEPTED);
    }


    @GetMapping("get/all/flights")
    public ResponseEntity<?> getAllFlight(){

        List<Flight> allflights=flightServices.findAllFlight();

        return new ResponseEntity<>(allflights, HttpStatus.OK);
    }



    @GetMapping("/get/flights/by/source/and/destination")
    public ResponseEntity<?> getFlightBySouceAndDestination(@RequestParam String source,@RequestParam String destination){

        List<Flight> allFlights=flightServices.findAllFlight();

        List<Flight> flightBySoAndDes=new ArrayList<>();

        for (int i=0; i<allFlights.size(); i++){

            Flight flight=allFlights.get(i);

            if (flight.getSource().equals(source) && flight.getDestination().equals(destination)){

                flightBySoAndDes.add(flight);
            }
        }

        Map<String, Object> response = new HashMap<>();

        if (flightBySoAndDes.isEmpty()) {
            response.put("message", "There is no flight for this route.");

        } else {
            response.put("message", "Flights for this route are available -> ");
            response.put("flights", flightBySoAndDes);
        }

        return ResponseEntity.ok(response);

        }


    @GetMapping("/get/all/flights/by/user")
    public ResponseEntity<?> getAllFlightByUser(){

        List<Flight> allflights=flightServices.findAllFlight();

        return new ResponseEntity<>(allflights, HttpStatus.OK);
    }


}
