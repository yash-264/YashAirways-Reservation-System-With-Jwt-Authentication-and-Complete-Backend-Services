package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Booking;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Flight;
import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Generator.TicketGenerator;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.BookingRepository;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.FlightRepository;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;
import yash.s.project.Airline.Reservation.System.Yash.Services.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class BookingCantroller {


    @Autowired
    private BookingServices bookingServices;

    @Autowired
    private FlightServices flightServices;

    @Autowired
    private TicketGenerator ticketGenerator;

    @Autowired
    private UserServices userServices;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private EmailServices emailServices;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;



    @PostMapping("/book/ticket")
    public ResponseEntity<?> bookTicketIfPresent(@RequestBody Booking credentials) throws MessagingException {

        String source = credentials.getSource();
        String destination = credentials.getDestination();
        Date date=credentials.getTraveldate();

        List<Flight> listOfFlight = flightServices.findAllFlight();

        List<Flight> newlistOfFlight = new ArrayList<>();

        int flightNumber=00000;
        int totalSeats=0;
        Flight flightForSeat=listOfFlight.get(0);


        for (Flight flight : listOfFlight) {

            if (flight.getSource().equals(source) && flight.getDestination().equals(destination) && flight.getFlightdate().equals(date)) {
                newlistOfFlight.add(flight);
                flightNumber=flight.getFlightnumber();
                totalSeats=flight.getAvlseat();
                flightForSeat=flight;
                break;
            }
        }

        HashMap<String,Object> map=new HashMap<>();


        if (newlistOfFlight.isEmpty() || newlistOfFlight==null){

            return new ResponseEntity<>("There Is No Flight Available For This Route Or This Date", HttpStatus.OK);
        }

        else {

            if (totalSeats<credentials.getNumofseat()){

                return new ResponseEntity<>("The Flight Is Available But The Seat Is Not Available !", HttpStatus.OK);

            }

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

            String username=authentication.getName();

            User user=userServices.findUserByUserName(username);

            Ticket ticket = ticketGenerator.ticketGenerate(credentials);

            ticket.setFlightnumber(flightNumber);

            ticket.setSource(credentials.getSource());

            ticket.setDestination(credentials.getDestination());

            ticket.setDate(credentials.getTraveldate());

            ticket.setBoardingtime(flightForSeat.getBoardingtime());

            ticket.setArrivaltime(flightForSeat.getArrivaltime());

            ticket.setGender(credentials.getGender());

            ticket.setTicketfare(flightForSeat.getTicketfare()*credentials.getNumofseat());

            List<String> seatNumbers = ticket.getSeatnumber();

            if (seatNumbers == null) {
                seatNumbers = new ArrayList<>();
            }

            for (int i = 0; i < credentials.getNumofseat(); i++) {
                seatNumbers.add("A" + (i + 1));
            }

            ticket.setSeatnumber(seatNumbers);


            totalSeats=flightForSeat.getAvlseat()-credentials.getNumofseat();

            flightForSeat.setAvlseat(totalSeats);

            if (flightForSeat.getAvlseat()<11){

                ticket.setCurrentstatus("AL-WL-"+flightForSeat.getAvlseat());
            }else {

                ticket.setCurrentstatus("CONFIRM");
            }

            ticket.setAirlinename(flightForSeat.getAirlinename());

            flightRepository.save(flightForSeat);

            bookingRepository.save(credentials);

            ticketRepository.save(ticket);

            user.getBookingList().add(credentials);



            user.getTicketList().add(ticket);
            
            userServices.saveprevious(user);


            emailServices.sendTicketEmail(credentials.getUseremail(),credentials.getName(),ticket.getPnr());


            map.put("Ticket Booked! Your Ticket Is  -> ",ticket);
            return ResponseEntity.ok(map);
        }

    }


    @PostMapping("/book/ticket/by/flightnumber")
    public ResponseEntity<?> bookTicketByTrainNumber(@RequestBody Booking credentials, @RequestParam int flightnumber) throws MessagingException {


        Flight flight=flightServices.findFlightByFlightNumber(flightnumber);


        int totalSeats=flight.getAvlseat();

        HashMap<String,Object> map=new HashMap<>();


        if (flight == null){

            return new ResponseEntity<>("Flight Not Found ! \n  For Flight Number -> "+flightnumber, HttpStatus.NOT_FOUND);
        }

        else {

            if (totalSeats < credentials.getNumofseat()) {

                return new ResponseEntity<>("The Flight Is Available But The Seat Is Not Available !", HttpStatus.OK);

            } else {

                if (flight.getSource().equals(credentials.getSource()) && flight.getDestination().equals(credentials.getDestination()) && flight.getFlightdate().equals(credentials.getTraveldate())) {

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                    String username = authentication.getName();

                    User user = userServices.findUserByUserName(username);

                    Ticket ticket = ticketGenerator.ticketGenerate(credentials);

                    ticket.setFlightnumber(flightnumber);

                    ticket.setSource(credentials.getSource());

                    ticket.setDestination(credentials.getDestination());

                    ticket.setDate(credentials.getTraveldate());

                    ticket.setBoardingtime(flight.getBoardingtime());

                    ticket.setArrivaltime(flight.getArrivaltime());

                    ticket.setGender(credentials.getGender());

                    ticket.setTicketfare(flight.getTicketfare() * credentials.getNumofseat());

                    List<String> seatNumbers = ticket.getSeatnumber();

                    if (seatNumbers == null) {
                        seatNumbers = new ArrayList<>();
                    }

                    for (int i = 0; i < credentials.getNumofseat(); i++) {
                        seatNumbers.add("A" + (i + 1));
                    }

                    ticket.setSeatnumber(seatNumbers);


                    totalSeats = flight.getAvlseat() - credentials.getNumofseat();

                    flight.setAvlseat(totalSeats);

                    if (flight.getAvlseat()<11){

                        ticket.setCurrentstatus("AL-WL-"+flight.getAvlseat());

                    }else {

                        ticket.setCurrentstatus("CONFIRM");
                    }

                    ticket.setAirlinename(flight.getAirlinename());

                    flightRepository.save(flight);

                    bookingRepository.save(credentials);

                    ticketRepository.save(ticket);

                    user.getBookingList().add(credentials);


                    user.getTicketList().add(ticket);

                    userServices.saveprevious(user);

                    emailServices.sendTicketEmail(credentials.getUseremail(),ticket.getPassengername(),ticket.getPnr());

                    map.put("Ticket Booked! Your Ticket Is  -> ", ticket);

                    return ResponseEntity.ok(map);

                }else {

                    return new ResponseEntity<>("Flight Is Available But Source Or Destination or TravelDate MisMatch !",HttpStatus.BAD_REQUEST);
            }

            }


        }


    }
}
