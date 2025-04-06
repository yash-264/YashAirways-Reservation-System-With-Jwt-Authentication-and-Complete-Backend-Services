package yash.s.project.Airline.Reservation.System.Yash.Generator;

import java.util.Random;


    public class PNRGenerator {

        public static String generatePNR() {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder pnr = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < 6; i++) {
                pnr.append(characters.charAt(random.nextInt(characters.length())));
            }
            return pnr.toString();
        }


    }

