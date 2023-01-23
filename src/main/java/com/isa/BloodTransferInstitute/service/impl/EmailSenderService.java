package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.exception.MailException;
import com.isa.BloodTransferInstitute.util.QRCodeGenerator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("psw.hospital.contact@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void sendEmailWithQRCode(String toEmail, String subject, String body, Long appointmentId, Long patientId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);
            message.setFrom(new InternetAddress("psw.hospital.contact@gmail.com"));
            message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(body, "text/plain; charset=UTF-8");

            Multipart multiPart = new MimeMultipart("alternative");

            String qrCodeText = "http://localhost:8080/api/Appointment/scheduled-message/" + appointmentId.toString() + "/" + patientId.toString();
            BufferedImage bufferedImage = QRCodeGenerator.generateQRCodeImage(qrCodeText);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", stream);
            stream.flush();
            byte[] imageBytes= stream.toByteArray();
            stream.close();

            BodyPart imagePart = new MimeBodyPart();
            ByteArrayDataSource imageDataSource = new ByteArrayDataSource(imageBytes,"image/png");

            imagePart.setDataHandler(new DataHandler(imageDataSource));
            imagePart.setHeader("Content-ID", "<qrImage>");
            imagePart.setFileName("QR_Code.png");

            multiPart.addBodyPart(imagePart);
            multiPart.addBodyPart(textPart);

            message.setContent(multiPart);
            mailSender.send(message);

        } catch (Exception e) {
            throw new MailException();
        }
    }
    public void sendEmailAppointmentStart(String toEmail, String subject, String body, Long appointmentId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);
            message.setFrom(new InternetAddress("psw.hospital.contact@gmail.com"));
            message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(body, "text/plain; charset=UTF-8");

            Multipart multiPart = new MimeMultipart("alternative");

            String qrCodeText = "http://localhost:3000/appointment-realization/" + appointmentId.toString();
            BufferedImage bufferedImage = QRCodeGenerator.generateQRCodeImage(qrCodeText);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", stream);
            stream.flush();
            byte[] imageBytes= stream.toByteArray();
            stream.close();

            BodyPart imagePart = new MimeBodyPart();
            ByteArrayDataSource imageDataSource = new ByteArrayDataSource(imageBytes,"image/png");

            imagePart.setDataHandler(new DataHandler(imageDataSource));
            imagePart.setHeader("Content-ID", "<qrImage>");
            imagePart.setFileName("QR_Code.png");

            multiPart.addBodyPart(imagePart);
            multiPart.addBodyPart(textPart);

            message.setContent(multiPart);
            mailSender.send(message);

        } catch (Exception e) {
            throw new MailException();
        }
    }

}
