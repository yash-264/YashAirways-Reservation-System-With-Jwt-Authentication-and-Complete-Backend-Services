package yash.s.project.Airline.Reservation.System.Yash.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Flight;

public interface FlightRepository extends MongoRepository<Flight, ObjectId> {

     void deleteFligthByflightnumber(int flightnumber);

     Flight findFlightByflightnumber(int flightnumber);
}
