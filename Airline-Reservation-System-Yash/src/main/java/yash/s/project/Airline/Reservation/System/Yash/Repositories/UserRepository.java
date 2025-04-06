package yash.s.project.Airline.Reservation.System.Yash.Repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findUserByusername(String username);

    void deleteUserByusername(String username);

    User findUserBygmail(String gmail);


//    Optional<User> findByusername(String username);
}
