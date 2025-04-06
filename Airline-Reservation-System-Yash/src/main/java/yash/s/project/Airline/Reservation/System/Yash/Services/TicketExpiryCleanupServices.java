package yash.s.project.Airline.Reservation.System.Yash.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/admin")
public class TicketExpiryCleanupServices {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    // Scheduled cleanup - Runs every day at midnight (00:00)
    @Scheduled(cron = "0 0 0 * * ?")
    

    @DeleteMapping("/cleanup/tickets")
    public ResponseEntity<?> cleanupCompletedTickets() {

        Date currentDate = new Date();


        List<Ticket> expiredTickets = ticketRepository.findAll().stream()
                .filter(ticket -> ticket.getDate().before(currentDate))
                .collect(Collectors.toList());



        for (Ticket ticket : expiredTickets) {
            User user = userRepository.findUserByusername(ticket.getPassengername());
            if (user != null) {
                user.getTicketList().removeIf(t -> t.getPnr().equals(ticket.getPnr()));
                userServices.saveprevious(user);
            }
        }

        ticketRepository.deleteAll(expiredTickets);


        return new ResponseEntity<>("Tickets with completed journeys have been removed successfully.", HttpStatus.OK);
    }
}

