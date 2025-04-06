package yash.s.project.Airline.Reservation.System.Yash.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Booking;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.BookingRepository;

import java.util.List;

@Service
public class BookingServices {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserServices userServices;

    public void saveTicket(Booking ticketCredentials,String userName){

        User user=userServices.findUserByUserName(userName);

        Booking booking=bookingRepository.save(ticketCredentials);

        user.getBookingList().add(booking);

        userServices.save(user);

    }

    public void cancelAllTickets(){

         bookingRepository.deleteAll();
    }


    public List<Booking> findTicket(){

        return bookingRepository.findAll();

    }


}
