package yash.s.project.Airline.Reservation.System.Yash.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import yash.s.project.Airline.Reservation.System.Yash.enums.Gender;

import java.util.Date;


@Document(collection = "Bookings & History")
public class Booking {


    @Id
    @NonNull
    private ObjectId BookingId;

    @NonNull
    private String source;

    @NonNull
    private String destination;

    @NonNull
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date traveldate;


    @NonNull
    private String name;

    @NonNull
    private String useremail;

    @NonNull
    private int age;

    @NonNull
    private Gender gender;

    @NonNull
    private int numofseat;


    public ObjectId getBookingId() {
        return BookingId;
    }

    public void setBookingId(ObjectId bookingId) {
        this.BookingId = bookingId;
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

    public @NonNull Date getTraveldate() {
        return traveldate;
    }

    public void setTraveldate(@NonNull Date traveldate) {
        this.traveldate = traveldate;
    }


    public @NonNull String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public @NonNull String getUseremail() {
        return useremail;
    }

    public void setUseremail(@NonNull String useremail) {
        this.useremail = useremail;
    }

    @NonNull
    public int getAge() {
        return age;
    }

    public void setAge(@NonNull int age) {
        this.age = age;
    }

    @NonNull
    public int getNumofseat() {
        return numofseat;
    }

    public void setNumofseat(@NonNull int numofseat) {
        this.numofseat = numofseat;
    }


    public @NonNull Gender getGender() {
        return gender;
    }

    public void setGender(@NonNull Gender gender) {
        this.gender = gender;
    }

}
