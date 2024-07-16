/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.projects.certificategenerator;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;


public class CreatePDF {

    public static void generate(Certificate certificate) throws FileNotFoundException, MalformedURLException, IOException{
        // Putting all the data to seperate variables
        String filename=certificate.getId();
        String idString= certificate.getId();
        String name=certificate.getName();
        String courseName= certificate.getCourse();
       String departmentName= "";
       String score= certificate.getScore();
       String firstPronoun= (certificate.getGender()==Gender.MALE) ? "He" : "She";
       String secondPronoun= (certificate.getGender()==Gender.MALE) ? "his" : "her";;
       String pronoun=(certificate.getGender()==Gender.MALE) ? "he" : "she";
       String date= new SimpleDateFormat("dd-MM-yyyy").format(certificate.getDate());


       // Assigining Departmentname by checking the enums
       switch(certificate.getDepartment()) {
           case Completion:
                departmentName = "Completion";
                break;
            case Achievement:
                departmentName = "Achievement";
                break;
            case Excellence:
                departmentName = "Excellence";
                break;
            case Participation:
                departmentName = "Participation";
                break;
            case Recognition:
                departmentName = "Recognition";
                break;
            default:
                throw new IllegalArgumentException("Invalid department: " + certificate.getDepartment());
        
                
       }

         String pathString = "Certificates/" + filename + ".pdf";
        String path = pathString;

        // Check if image files exist before attempting to create ImageData
        File imageFile = new File("images/cer2.png");
        if (!imageFile.exists()) {
            throw new FileNotFoundException("Image file not found: " + imageFile.getAbsolutePath());
        }
        File logoFile = new File("images/signature.png");
        if (!logoFile.exists()) {
            throw new FileNotFoundException("Logo file not found: " + logoFile.getAbsolutePath());
        }
        ImageData data = ImageDataFactory.create(imageFile.getAbsolutePath());
        ImageData dataforLogo = ImageDataFactory.create(logoFile.getAbsolutePath());
        Image image1 = new Image(data);
        image1.scaleAbsolute(580f, 830f);
        image1.setFixedPosition(5, 5);
        Image signature = new Image(dataforLogo);
        signature.scaleAbsolute(110, 76);
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        PdfFont timesfont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont boldfont = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);

        // ID
        Text id = new Text(idString);
        id.setFont(boldfont);
        Paragraph pForID = new Paragraph().add(id);
        pForID.setTextAlignment(TextAlignment.CENTER);
        Text text1 = new Text("\nCERTIFICATE").setFont(font);
        text1.setFontSize(35f);
        Text text2 = new Text("\nof " + departmentName.toUpperCase()).setFont(timesfont);
        text2.setFontSize(18f);
        Paragraph paragraph = new Paragraph()
                .add(text1)
                .add(text2);
        paragraph.setTextAlignment(TextAlignment.CENTER);

        // Second paragraph
        PdfFont anotherFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Text presentedText = new Text("\nThis is to certify that");
        presentedText.setFontSize(16f);
        Text personName = new Text("\n" + name);
        personName.setFontSize(28f);
        personName.setFont(anotherFont);
        Paragraph p2 = new Paragraph().add(presentedText);
        p2.add(personName);
        p2.setTextAlignment(TextAlignment.CENTER);
        pdfDocument.addNewPage();

        // Third paragraph
        Text segmentText1 = new Text("\n\nhas successfully met the requirements and is hereby awarded this certificate for ");
        Text segmentText2 = new Text(departmentName);
        Text segmentText3 = new Text(". ");
        Text segmentText4 = new Text(firstPronoun);
        Text segmentText5 = new Text(" has demonstrated exceptional skills and knowledge in the related area.");
        // Bolding only the dynamic segments
        PdfFont helveticaBold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        segmentText2.setFont(helveticaBold);
        Paragraph firstParagraph = new Paragraph();
        firstParagraph.add(segmentText1);
        firstParagraph.add(segmentText2);
        firstParagraph.add(segmentText3);
        firstParagraph.add(segmentText4);
        firstParagraph.add(segmentText5);
        firstParagraph.setTextAlignment(TextAlignment.JUSTIFIED);

        // Fourth paragraph
        Text secondSegmentText1 = new Text("\nThis certificate is conferred ");
        Text secondSegmentText2 = new Text(secondPronoun);
        Text secondSegmentText3 = new Text(" with all the rights, honors, and privileges thereto appertaining. ");
        Text secondSegmentText4 = new Text("We are confident that ");
        Text secondSegmentText5 = new Text(pronoun);
        Text secondSegmentText6 = new Text(" will excel in ");
        Text secondSegmentText7 = new Text(secondPronoun);
        Text secondSegmentText8 = new Text(" future endeavors.");

        // Adding segments to the paragraph
        Text thirdText = new Text("\n\nCongratulations for the achievement!\n");
        thirdText.setFontSize(13f);
        Paragraph secondParagraph = new Paragraph();
        secondParagraph.add(secondSegmentText1);
        secondParagraph.add(secondSegmentText2);
        secondParagraph.add(secondSegmentText3);
        secondParagraph.add(secondSegmentText4);
        secondParagraph.add(secondSegmentText5);
        secondParagraph.add(secondSegmentText6);
        secondParagraph.add(secondSegmentText7);
        secondParagraph.add(secondSegmentText8);
        secondParagraph.add(thirdText);
        secondParagraph.setTextAlignment(TextAlignment.JUSTIFIED);

        // Date
        Text daText = new Text("\nDate: " + date + "\n");
        daText.setFontSize(13f);
        Paragraph dateParagraph = new Paragraph();
        dateParagraph.add(daText);

        // Signature
        Text instName = new Text("Maverick Skywalker \n");
        instName.setFontSize(13f);
        Text postName = new Text("Instructor");
        Paragraph lastParagraph = new Paragraph();
        lastParagraph.add(instName);
        lastParagraph.add(postName);
        Document document = new Document(pdfDocument);
        document.setBackgroundColor(Color.PINK);
        document.setMargins(70, 70, 70, 70);
        document.add(image1);
        document.add(pForID);
        document.add(paragraph);
        document.add(p2);
        document.add(firstParagraph);
        document.add(secondParagraph);
        document.add(dateParagraph);
        document.add(signature);
        document.add(lastParagraph);
        document.close();

        System.out.println("Certificate generated successfully: " + path);
    }
}
