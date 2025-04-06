package yash.s.project.Airline.Reservation.System.Yash.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class EmailServices {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSignupEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to YashAirways Reservation System!");
        message.setText("Dear " + name + ",\n\n" +
                "Congratulations! You have successfully signed up.\n" +
                "Thank you for choosing our YashAirways Reservation System.\n\n" +
                "Best Regards,\n" +
                "YashAirways Reservation System , Technical Team.");

        mailSender.send(message);
    }


    private static final String DOWNLOAD_URL = "yashairways-reservation-system-w-production.up.railway.app/download/ticket/by/pnr?pnr=";


    public void sendTicketEmail(String toEmail, String name, String pnr) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Your Ticket - YashAirways Reservation System");

        String downloadLink = DOWNLOAD_URL + pnr;

        String emailContent = String.format(
                "Dear %s,\n\n" +
                        "Your Ticket has been Successfully Booked with PNR: %s.\n\n" +
                        "To download your ticket, click the link below:\n%s\n\n" +
                        "Wishing you a pleasant journey!\n\n" +
                        "Best Regards,\n" +
                        "YashAirways Reservation System, Technical Team.",
                name, pnr, downloadLink
        );

        helper.setText(emailContent);
        mailSender.send(message);
    }




    public void sendAdminCreationEmail(String toEmail, String name) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Admin Account Created - YashAirways Reservation System");
        message.setText("Dear " + name + ",\n\n" +
                "Congratulations! Your account has been successfully created with ADMIN privileges.\n" +
                "You now have full administrative access to the YashAirways Reservation System.\n\n" +
                "Please ensure to keep your credentials safe and secure.\n\n" +
                "Best Regards,\n" +
                "YashAirways Reservation System, Technical Team.");

        mailSender.send(message);
    }


}
