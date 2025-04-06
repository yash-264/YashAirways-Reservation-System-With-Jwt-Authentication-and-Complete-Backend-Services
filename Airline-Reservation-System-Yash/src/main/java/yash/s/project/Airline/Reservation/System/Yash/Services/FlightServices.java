package yash.s.project.Airline.Reservation.System.Yash.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Flight;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.FlightRepository;

import java.util.List;

@Service
public class FlightServices {

    @Autowired
    private FlightRepository flightRepository;


    public void addFlight(Flight newflight){

        flightRepository.save(newflight);
    }


    public List<Flight> findAllFlight(){

        return flightRepository.findAll();
    }

    public void deleteFlightByFlightNumber(int flightnumber){

        flightRepository.deleteFligthByflightnumber(flightnumber);


    }


    public void deleteAllFlights(){

        flightRepository.deleteAll();
    }

    public Flight findFlightByFlightNumber(int flightnumber){

        return  flightRepository.findFlightByflightnumber(flightnumber);

    }

}
