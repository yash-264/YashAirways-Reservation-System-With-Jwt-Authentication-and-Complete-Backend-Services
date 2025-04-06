package yash.s.project.Airline.Reservation.System.Yash.Generator;

import java.util.Random;

public class TicketNumberGenerator {

    private static final String AIRLINE_CODE = "123";

    public static String generateTicketNumber() {
        Random random = new Random();
        long randomNumber = 1000000000L + (long)(random.nextDouble() * 8999999999L);

        return AIRLINE_CODE + "-" + randomNumber;
    }
}
