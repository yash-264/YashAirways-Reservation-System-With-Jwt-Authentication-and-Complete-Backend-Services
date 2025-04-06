package yash.s.project.Airline.Reservation.System.Yash.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import yash.s.project.Airline.Reservation.System.Yash.enums.Gender;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Tickets")
public class Ticket {

    @Id
    @NonNull
    private ObjectId TicketId;

    @NonNull
    @Indexed(unique = true)
    private String pnr;

    @NonNull
    @Indexed(unique = true)
    private String ticketnumber;

    @NonNull
    private String source;

    @NonNull
    private String destination;

    @NonNull
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    @NonNull
    private String currentstatus;

    @NonNull
    private LocalTime boardingtime;

    @NonNull
    private LocalTime arrivaltime;

    @NonNull
    private String passengername;

    @NonNull
    private Gender gender;

    @NonNull
    private int flightnumber;

    @NonNull
    private List<String> seatnumber;

    @NonNull
    private double ticketfare;

    @NonNull
    private String airlinename;

    public @NonNull ObjectId getTicketId() {
        return TicketId;
    }

    public void setTicketId(@NonNull ObjectId ticketId) {
        this.TicketId = ticketId;
    }

    public @NonNull String getPnr() {
        return pnr;
    }

    public void setPnr(@NonNull String pnr) {
        this.pnr = pnr;
    }

    public @NonNull String getTicketnumber() {
        return ticketnumber;
    }

    public void setTicketnumber(@NonNull String ticketnumber) {
        this.ticketnumber = ticketnumber;
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

    public @NonNull Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public @NonNull String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(@NonNull String currentstatus) {
        this.currentstatus = currentstatus;
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

    public @NonNull String getPassengername() {
        return passengername;
    }

    public void setPassengername(@NonNull String passengername) {
        this.passengername = passengername;
    }

    public @NonNull Gender getGender() {
        return gender;
    }

    public void setGender(@NonNull Gender gender) {
        this.gender = gender;
    }

    @NonNull
    public int getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(@NonNull int flightnumber) {
        this.flightnumber = flightnumber;
    }

    public @NonNull List<String> getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(@NonNull List<String> seatnumber) {
        this.seatnumber = seatnumber;
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
