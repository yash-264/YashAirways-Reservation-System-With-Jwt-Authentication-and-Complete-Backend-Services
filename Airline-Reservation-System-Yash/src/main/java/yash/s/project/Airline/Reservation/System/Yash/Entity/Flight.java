package yash.s.project.Airline.Reservation.System.Yash.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Flights")
public class Flight {


    @Id
    @NonNull
    private ObjectId FlightId;


    @NonNull
    @Indexed(unique = true)
    private int flightnumber;

    @NonNull
    private String source;

    @NonNull
    private String destination;

    @NonNull
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date flightdate;

    @NonNull
    private LocalTime boardingtime;

    @NonNull
    private LocalTime arrivaltime;

    @NonNull
    private int avlseat;

    @NonNull
    private double numofkm;

    @NonNull
    private double ticketfare;

    @NonNull
    private String airlinename;


    public ObjectId getFlightId() {
        return FlightId;
    }

    public void setFlightId(ObjectId flightId) {
        this.FlightId = flightId;
    }

    @NonNull
    public int getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(@NonNull int flightnumber) {
        this.flightnumber = flightnumber;
    }

    public @NonNull String getSource() {
        return source;
    }

    public void setSource(@NonNull String source) {
        this.source = source;
    }

    public @NonNull String getDestination() {
        return destination;
    }

    public void setDestination(@NonNull String destination) {
        this.destination = destination;
    }

    public @NonNull Date getFlightdate() {
        return flightdate;
    }

    public void setFlightdate(@NonNull Date flightdate) {
        this.flightdate = flightdate;
    }

    public @NonNull LocalTime getBoardingtime() {
        return boardingtime;
    }

    public void setBoardingtime(@NonNull LocalTime boardingtime) {
        this.boardingtime = boardingtime;
    }

    public @NonNull LocalTime getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(@NonNull LocalTime arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    @NonNull
    public int getAvlseat() {
        return avlseat;
    }

    public void setAvlseat(@NonNull int avlseat) {
        this.avlseat = avlseat;
    }

    @NonNull
    public double getNumofkm() {
        return numofkm;
    }

    public void setNumofkm(@NonNull double numofkm) {
        this.numofkm = numofkm;
    }

    @NonNull
    public double getTicketfare() {
        return ticketfare;
    }

    public void setTicketfare(@NonNull double ticketfare) {
        this.ticketfare = ticketfare;
    }

    public @NonNull String getAirlinename() {
        return airlinename;
    }

    public void setAirlinename(@NonNull String airlinename) {
        this.airlinename = airlinename;
    }
}
