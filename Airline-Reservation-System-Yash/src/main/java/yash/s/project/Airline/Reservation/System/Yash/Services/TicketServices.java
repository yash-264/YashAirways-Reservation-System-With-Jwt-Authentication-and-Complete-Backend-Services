package yash.s.project.Airline.Reservation.System.Yash.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;

import java.util.List;

@Service
public class TicketServices {

    @Autowired
    private TicketRepository ticketRepository;


    public void deleteBookingByPnr(String pnr){

        ticketRepository.deleteTicketBypnr(pnr);

    }

    public Ticket findTicketBypnr(String pnr){

       return ticketRepository.findTicketBypnr(pnr);
    }


    public void deleteAllTickets(){

        ticketRepository.deleteAll();
    }

    public List<Ticket> findByFlightNumber(int flightnumber){

        return ticketRepository.findByflightnumber(flightnumber);
    }

}
