package com.TZ.Controllers;

import com.TZ.Mapper.SalonMapper;
import com.TZ.Models.Salon;
import com.TZ.SalonService.SalonService;
import com.TZ.payload.dto.SalonDTO;
import com.TZ.payload.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salons")
public class SalonController {
    private final SalonService salonService;

    //http://localhost:5002/api/salons
    //{
    //"name" : "Elite Beauty Salon",
    //"address" : "123 Main Street, Downtown",
    //"phone" : "+91 - 9203809238",
    //"email": "contact@elitebeautysalon.com",
    //"city" : "Mumbai",
    //"openTime" : "09:00:00",
    //"closeTime" : "20:00:00",
    //"images" : ["https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fpeace%2F&psig=AOvVaw1Snk69aq8KZ7QlzbwQqJa5&ust=1739460295216000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOilneC4vosDFQAAAAAdAAAAABAE"]
    //}
    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDTO, userDTO);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    //http://localhost:5002/api/salons/2
    //{
    //"name" : "Elite Beauty Salon",
    //"address" : "123 Main Street, Downtown",
    //"phone" : "+91 - 9203809238",
    //"email": "contact@elitebeautysalon.com",
    //"city" : "Mumbai",
    //"openTime" : "09:00:00",
    //"closeTime" : "20:00:00",
    //"images" : ["https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fpeace%2F&psig=AOvVaw1Snk69aq8KZ7QlzbwQqJa5&ust=1739460295216000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOilneC4vosDFQAAAAAdAAAAABAE"]
    //}
    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(
            @PathVariable("id") Long salonId,
            @RequestBody SalonDTO salonDTO) throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.updateSalon(salonDTO, userDTO, salonId);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    //http://localhost:5002/api/salons/
    @GetMapping()
    public ResponseEntity<List<SalonDTO>> getsalons() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        List<Salon> salons = salonService.getAllSalons();
        List<SalonDTO> salonDTOS = salons.stream().map((salon) ->
        {
            SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
            return salonDTO;
        }
        ).toList();
        return ResponseEntity.ok(salonDTOS);
    }

    //http://localhost:5002/api/salons/5
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getsalonById(@PathVariable Long salonId) throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.getSalonById(salonId);
        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

    //http://localhost:5002/api/salons/search?city=mumbai
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(
            @RequestParam("city") String city) throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        List<Salon> salons = salonService.searchSalonByCity(city);
        List<SalonDTO> salonDTOS = salons.stream().map((salon) ->
        {
            SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
            return salonDTO;
        }
        ).toList();
        return ResponseEntity.ok(salonDTOS);
    }

    //http://localhost:5002/api/salons/owner
    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(
            @PathVariable Long salonId) throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }
}
