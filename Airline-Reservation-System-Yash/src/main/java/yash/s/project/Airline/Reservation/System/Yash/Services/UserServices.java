package yash.s.project.Airline.Reservation.System.Yash.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.UserRepository;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public void save(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveprevious(User user){
        userRepository.save(user);
    }


    public List<User> findAll(){

        return userRepository.findAll();
    }


    public void deleteAllUsers(){

        userRepository.deleteAll();
    }

    public User findUserByUserName(String username){

       return userRepository.findUserByusername(username);


    }

    public void deleteUserByUserName(String userName){

        userRepository.deleteUserByusername(userName);
    }

    public User findUserByGmail(String gmail){

        return userRepository.findUserBygmail(gmail);
    }


}
