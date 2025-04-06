package yash.s.project.Airline.Reservation.System.Yash.Generator;

import org.springframework.stereotype.Component;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Booking;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;



@Component
public class TicketGenerator {

//    private static int seatNumber = 1;

    public Ticket ticketGenerate(Booking ticketCredentials){

        Ticket ticket = new Ticket();

        ticket.setPnr(PNRGenerator.generatePNR());

        ticket.setTicketnumber(TicketNumberGenerator.generateTicketNumber());

        ticket.setPassengername(ticketCredentials.getName());

        ticket.setFlightnumber(12345);  //bydefault number

//        ticket.setSeatnumber(seatNumber++);

        return ticket;
    }
}
