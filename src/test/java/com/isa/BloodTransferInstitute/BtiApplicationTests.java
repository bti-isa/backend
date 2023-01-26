package com.isa.BloodTransferInstitute;

import static org.junit.Assert.assertEquals;

import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.complaint.AnswerDTO;
import com.isa.BloodTransferInstitute.dto.complaint.NewComplaintDTO;
import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.AppointmentMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.Complaint;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.AppointmentRepository;
import com.isa.BloodTransferInstitute.repository.ComplaintRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.persistence.OptimisticLockException;

import com.isa.BloodTransferInstitute.service.ComplaintService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BtiApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private ComplaintRepository complaintRepository;
	@Autowired
	private AppointmentService appointmentService;

	@Test
	public void multipleAppointmentScheduling() throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					Appointment appointment = appointmentRepository.findById(8L).orElseThrow(NotFoundException::new);
					User patient = userRepository.findById(1L).orElseThrow(NotFoundException::new);
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					appointment.setStatus(AppointmentStatus.SCHEDULED);
					appointment.setPatient(patient);
					try {
						appointmentRepository.save(appointment);
					} catch (Exception e) {
						Assertions.assertEquals(e.getCause().toString(), "OptimisticLockException");
					}
				}
			}
		);

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Appointment appointment = appointmentRepository.findById(8L).orElseThrow(NotFoundException::new);
				User patient = userRepository.findById(1L).orElseThrow(NotFoundException::new);
				appointment.setStatus(AppointmentStatus.SCHEDULED);
				appointment.setPatient(patient);
				appointmentRepository.save(appointment);
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
	@Test
	public void Stops_concurrent_complaint_answer() throws InterruptedException {
		Complaint complaint = complaintService.add(new NewComplaintDTO("Test", 7L));
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					complaintService.update(new AnswerDTO("Answer1", complaint.getId()));
				} catch (OptimisticLockException exception) {
					Assertions.assertEquals(exception.getCause().toString(), "OptimisticLockException");
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				complaintService.update(new AnswerDTO("Answer2", complaint.getId()));
			}
		});

		t1.run();
		t2.run();
		t1.join();
		t2.join();

		complaintService.delete(complaint.getId());
	}

	@Test
	public void Appointment_validaion() throws  InterruptedException {
		Appointment appointment = appointmentRepository.findById(5L).get();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					//2023-01-31 03:30:00.000000
					LocalDateTime date = LocalDateTime.of(2023, Month.JANUARY, 31, 03, 30, 00);
					appointmentService.create(new NewAppointmentDTO(date, "sys@gmail.com"));
				} catch (OptimisticLockException exception) {
					Assertions.assertEquals(exception.getCause().toString(), "OptimisticLockException");
				}
			}
		});

		t1.start();
		t1.join();
	}

}
