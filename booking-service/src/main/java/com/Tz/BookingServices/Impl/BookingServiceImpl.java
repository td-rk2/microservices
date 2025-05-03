package com.Tz.BookingServices.Impl;

import com.Tz.BookingServices.BookingService;
import com.Tz.Models.Booking;
import com.Tz.Models.SalonReport;
import com.Tz.Repositories.BookingRepository;
import com.Tz.domain.BookingStatus;
import com.Tz.dto.BookingRequest;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;
import com.Tz.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking,
                                 UserDTO userDTO,
                                 SalonDTO salonDTO,
                                 Set<ServiceDTO> serviceDTOSet) throws Exception {

        int totalDuration = serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getDuration)
                .sum();

        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(salonDTO, bookingStartTime, bookingEndTime);

        int totalPrice = serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getPrice)
                .sum();

        Set<Long> idList = serviceDTOSet.stream()
                .map(ServiceDTO::getId)
                .collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDTO.getId());
        newBooking.setSalonId(salonDTO.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {

        List<Booking> existingBookings = getBookingsBySalon(salonDTO.getId());
        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        if(bookingStartTime.isBefore(salonOpenTime)
                || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time must be within salon's working hours");

        }

        for(Booking existingBooking: existingBookings){
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime)
                    && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("Slot not available, please choose another time");
            }

            if(bookingStartTime.isEqual(existingBookingStartTime)
                    || bookingEndTime.isEqual(existingBookingEndTime)) {
                throw new Exception("Slot not available, please choose another time");
            }
        }
        return true;
    }


    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {

        return bookingRepository.findByCustomerId(customerId);
    }


    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {

        return bookingRepository.findBySalonId(salonId);
    }


    @Override
    public Booking getBookingById(Long bookingId) {

        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }


    @Override
    public Booking updateBooking(Long bookingId, BookingStatus bookingStatus) {

        Booking booking1 = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking1.setStatus(bookingStatus);
        return bookingRepository.save(booking1);
    }


    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {

        List<Booking> allBookings = getBookingsBySalon(salonId);
        if(date==null){
            return allBookings;
        }

        return allBookings.stream()
                .filter(booking-> isSameDate(booking.getStartTime(), date) ||
                        isSameDate(booking.getEndTime(), date)).collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {

        return dateTime.toLocalDate().isEqual(date);
    }


    @Override
    public SalonReport getSalonReport(Long salonId) {

        List<Booking> booking1 = getBookingsBySalon(salonId);

        int totalEarnings = booking1.stream()
                .mapToInt(Booking::getTotalPrice)
                .sum();

        Integer totalBookings = booking1.size();

        List<Booking> cancelledBookings = booking1.stream()
                .filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED))
                .collect(Collectors.toList());

        Double totalRefund = cancelledBookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        SalonReport salonReport = new SalonReport();
        salonReport.setSalonId(salonId);
        salonReport.setCancelledBookings(cancelledBookings.size());
        salonReport.setTotalBookings(totalBookings);
        salonReport.setTotalEarnings(totalEarnings);
        salonReport.setTotalRefund(totalRefund);

        return salonReport;
    }
}
