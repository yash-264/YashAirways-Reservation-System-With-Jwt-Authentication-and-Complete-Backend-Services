package yash.s.project.Airline.Reservation.System.Yash.Entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import yash.s.project.Airline.Reservation.System.Yash.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "AirlineUsers")
public class User {


    @Id
    @NonNull
    private ObjectId UserId;

    @NonNull
    @Indexed(unique = true)
    private String username;

    @NonNull
    private String  password;

    @NonNull
    private String gmail;

    @NonNull
    private Role role;


    @DBRef
    private List<Booking> bookingList =new ArrayList<>();

    @DBRef
    private List<Ticket> ticketList=new ArrayList<>();


    public @NonNull ObjectId getUserId() {
        return UserId;
    }

    public void setUserId(@NonNull ObjectId userId) {
        this.UserId = userId;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public @NonNull String getGmail() {
        return gmail;
    }

    public void setGmail(@NonNull String gmail) {
        this.gmail = gmail;
    }

    public @NonNull Role getRole() {
        return role;
    }

    public void setRole(@NonNull Role role) {
        this.role = role;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }


    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
