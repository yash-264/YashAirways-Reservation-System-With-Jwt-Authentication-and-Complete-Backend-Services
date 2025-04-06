package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Services.EmailServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.TicketServices;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;
import yash.s.project.Airline.Reservation.System.Yash.enums.Role;


@RestController
@RequestMapping("/yashairways")
public class PublicCantrollerForCreateUser {


    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailServices emailServices;

    @Autowired
    private TicketServices ticketServices;

    @PostMapping("signup/user")
    public ResponseEntity<?> CreateUser(@RequestBody User newUser) {


        User user=userServices.findUserByUserName(newUser.getUsername());

        User user1=userServices.findUserByGmail(newUser.getGmail());

        if (user != null) {
            return new ResponseEntity<>("Username already exists. Please choose a different one.", HttpStatus.BAD_REQUEST);
        }

        if (user1 != null) {
            return new ResponseEntity<>("Usergmail already exists. Please choose a different one.", HttpStatus.BAD_REQUEST);
        }


        if (newUser.getRole().equals(Role.user)){

            userServices.save(newUser);

            emailServices.sendSignupEmail(newUser.getGmail(), newUser.getUsername());

            return new ResponseEntity<>("User registered successfully, and a confirmation email has been sent!",HttpStatus.CREATED);
        }

        return new ResponseEntity<>(" Not authorized to create an admin. Please select role 'user'.or username already exist.",HttpStatus.BAD_REQUEST);

    }



    // here we check pnr status by pnr


    @GetMapping("/check/pnr/status/{pnr}")
    public ResponseEntity<?> checkPnrStatusBypnr(@PathVariable String pnr) {

        Ticket ticket = ticketServices.findTicketBypnr(pnr);

        if (ticket == null) {


            return new ResponseEntity<>("PNR Not Found !",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ticket,HttpStatus.OK);

    }


}
