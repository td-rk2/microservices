package com.Tz.BookingServices;

import com.Tz.Models.Booking;
import com.Tz.Models.SalonReport;
import com.Tz.domain.BookingStatus;
import com.Tz.dto.BookingRequest;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;
import com.Tz.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking,
                          UserDTO userDTO,
                          SalonDTO salonDTO,
                          Set<ServiceDTO> serviceDTOSet) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long bookingId);
    Booking updateBooking(Long bookingId, BookingStatus bookingStatus);
    List<Booking> getBookingByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);

}
