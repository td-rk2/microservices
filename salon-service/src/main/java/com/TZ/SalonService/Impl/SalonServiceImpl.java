package com.TZ.SalonService.Impl;

import com.TZ.Models.Salon;
import com.TZ.Repositories.SalonRepository;
import com.TZ.SalonService.SalonService;
import com.TZ.payload.dto.SalonDTO;
import com.TZ.payload.dto.UserDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {
    private final SalonRepository salonRepository;
    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setImages(req.getImages());
        salon.setAddress(req.getAddress());
        salon.setPhoneNumber(req.getPhone());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setOwnerId(user.getId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception{
        Salon existingsalon = salonRepository.findById(salonId).orElse(null);
        if(existingsalon != null && salon.getOwnerId().equals(user.getId())){
            existingsalon.setCity(salon.getCity());
            existingsalon.setAddress(salon.getAddress());
            existingsalon.setImages(salon.getImages());
            existingsalon.setName(salon.getName());
            existingsalon.setPhoneNumber(salon.getPhone());
            existingsalon.setEmail(salon.getEmail());
            existingsalon.setOpenTime(salon.getOpenTime());
            existingsalon.setOwnerId(user.getId());
            existingsalon.setCloseTime(salon.getCloseTime());
            return salonRepository.save(existingsalon);

        }
        throw new Exception("Salon not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception{
        Salon salon =  salonRepository.findById(salonId).orElse(null);
        if(salon == null){
            throw new Exception("User not exists");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
