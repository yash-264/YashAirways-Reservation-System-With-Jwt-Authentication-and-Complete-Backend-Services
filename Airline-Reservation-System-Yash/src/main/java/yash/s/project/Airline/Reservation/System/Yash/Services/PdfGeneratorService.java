package yash.s.project.Airline.Reservation.System.Yash.Services;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;

@Service
public class PdfGeneratorService {

    public ByteArrayOutputStream generateTicketPdf(String ticketDetails) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);


        String imagePath = "src/main/resources/static/pngwing.com.png";  // Path to your logo
        File file = new File(imagePath);

        if (!file.exists()) {
            System.out.println("Logo file not found at: " + file.getAbsolutePath());
        } else {
            ImageData imageData = ImageDataFactory.create(file.getAbsolutePath());
            Image logo = new Image(imageData);
            logo.setWidth(135);
            logo.setHeight(100);

            document.add(logo);
        }


        document.add(new Paragraph("Flight Ticket").setBold().setFontSize(20));
        document.add(new Paragraph(ticketDetails).setFontSize(12));

        document.close();

        return outputStream;
    }
}
