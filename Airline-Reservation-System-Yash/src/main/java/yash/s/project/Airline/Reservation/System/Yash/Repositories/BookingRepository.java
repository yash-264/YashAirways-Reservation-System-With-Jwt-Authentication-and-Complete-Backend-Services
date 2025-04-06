package yash.s.project.Airline.Reservation.System.Yash.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Booking;

public interface BookingRepository extends MongoRepository<Booking, ObjectId> {
}
