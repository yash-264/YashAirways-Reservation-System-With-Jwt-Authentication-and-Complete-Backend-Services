package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yash.s.project.Airline.Reservation.System.Yash.Entity.Ticket;
import yash.s.project.Airline.Reservation.System.Yash.Repositories.TicketRepository;
import yash.s.project.Airline.Reservation.System.Yash.Services.PdfGeneratorService;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/download/ticket/by/pnr")
public class PdfDownloadCantroller {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public ResponseEntity<byte[]> downloadTicket(@RequestParam String pnr) {
        try {
            Ticket ticket = ticketRepository.findTicketBypnr(pnr);

            if (ticket == null) {
                return ResponseEntity.notFound().build();
            }

            String ticketDetails = String.format(
                    "\n" +
                            "═══════════════════════════════════════════════════════════════════\n" +
                            "                    " + ticket.getAirlinename().toUpperCase() + "                  \n" +

                            "═══════════════════════════════════════════════════════════════════\n\n" +

                            "🎫 Passenger Name : %-32sGender        : %s\n" +
                            "📄 PNR            : %-35s Ticket Number : %s\n" +
                            "🛫 Flight Number  : %-33dStatus        : %s\n" +
                            "🌍 Source         : %-35s      Destination   : %s\n" +
                            "📅 Date           : %-35s\n" +
                            "⏰ Boarding Time  : %-35s Arrival Time  : %s\n" +
                            "💺 Seat Number    : %-35s   Fare          : ₹%.2f\n\n" +

                            "═══════════════════════════════════════════════════════════════════\n" +
                            "                Wishing You a Safe & Wonderful Journey!            \n" +
                            "═══════════════════════════════════════════════════════════════════\n" +


                            "🌟 Welcome Aboard " + ticket.getAirlinename() + "! 🌟\n" +
                            "May your flight be smooth, your luggage light, and your heart full of excitement. ✈️✨\n" +
                            "We hope you create beautiful memories, meet amazing people, and cherish every moment of your journey. 📸🌍\n" +
                            "Safe travels, and we look forward to welcoming you back, refreshed and inspired! 💼😊\n\n" +
                            "Best Wishes,\n" +
                            "YashAirways Reservation System , Technical Team.",


                    ticket.getPassengername(),
                    ticket.getGender().toString(),
                    ticket.getPnr(),
                    ticket.getTicketnumber(),
                    ticket.getFlightnumber(),
                    ticket.getCurrentstatus(),
                    ticket.getSource(),
                    ticket.getDestination(),
                    new SimpleDateFormat("dd/MM/yyyy").format(ticket.getDate()),
                    ticket.getBoardingtime().toString(),
                    ticket.getArrivaltime().toString(),
                    ticket.getSeatnumber().toString(),
                    ticket.getTicketfare()
            );



            ByteArrayOutputStream outputStream = pdfGeneratorService.generateTicketPdf(ticketDetails);

            byte[] pdfBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "ticket-" + ticket.getPnr() + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
