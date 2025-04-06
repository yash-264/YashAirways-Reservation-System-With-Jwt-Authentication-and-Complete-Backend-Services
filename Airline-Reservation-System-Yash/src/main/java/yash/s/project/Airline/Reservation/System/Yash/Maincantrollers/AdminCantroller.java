package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Booking;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.BookingRepository;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;
import yash.s.project.Airline.Reservation.System.Yash.Services.BookingServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.TicketServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminCantroller {

    @Autowired
    private UserServices userServices;

    @Autowired
    BookingServices bookingServices;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketServices ticketServices;


    @GetMapping("/get/all/users/and/details")
    public ResponseEntity<?> getAllUser(){

        List<User> users=userServices.findAll();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }


    @DeleteMapping("/delete/all/users/and/details")
    public ResponseEntity<?> removeAllUsers(){

        List<User> alluser=userServices.findAll();

        if (alluser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

            bookingRepository.deleteAll();
            ticketServices.deleteAllTickets();
            userServices.deleteAllUsers();

            return new ResponseEntity<>("Deleted ! All Users And Details.", HttpStatus.OK);

    }

    @DeleteMapping("delete/user/by/username")
    public ResponseEntity<?> deleteUserByUserName(@RequestParam String username){

        User user=userServices.findUserByUserName(username);

        bookingRepository.deleteAll(user.getBookingList());

        userServices.deleteUserByUserName(username);

        return new ResponseEntity<>("User Deleted With Username -> "+username,HttpStatus.OK);
    }



    @DeleteMapping("/cancel/all/tickets/of/all/users")
    private ResponseEntity<?> cancelAllTicket(){

        bookingServices.cancelAllTickets();

        return new ResponseEntity<>("All Tickets Of All Users has been Successfully Cancelled !",HttpStatus.OK);
    }




}
