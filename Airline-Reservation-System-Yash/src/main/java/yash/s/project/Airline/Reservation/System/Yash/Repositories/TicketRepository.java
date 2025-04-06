package yash.s.project.Airline.Reservation.System.Yash.Repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket,ObjectId> {


    Ticket findTicketBypnr(String pnr);

    void deleteTicketBypnr(String pnr);

    List<Ticket> findByflightnumber(int flightnumber);
}