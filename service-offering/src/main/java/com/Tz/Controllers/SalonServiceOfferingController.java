package com.Tz.Controllers;

import com.Tz.Models.ServiceOffering;
import com.Tz.ServiceOfferingService.ServiceOfferingService;
import com.Tz.dto.CategoryDTO;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    //http://localhost:5004/api/service-offering/salon-owner
    //{
    //    "name" : "Aromatherapy Massage",
    //    "description" : "A relaxing massage using essential oils.",
    //    "price" : 1299.99,
    //    "duration" : 90,
    //    "category" : 1,
    //    "image" : "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fpeace%2F&psig=AOvVaw1Snk69aq8KZ7QlzbwQqJa5&ust=1739460295216000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOilneC4vosDFQAAAAAdAAAAABAE"
    //}

    @PostMapping()
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO){

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());

        ServiceOffering serviceOffering1 =  serviceOfferingService.createService(salonDTO, serviceDTO, categoryDTO);

        return ResponseEntity.ok(serviceOffering1);
    }

    //http://localhost:5004/api/service-offering/salon-owner/1
    //{
    //       "name" : "Oil Massage",
    //       "description" : "A horrified one",
    //       "price" : 1399.99,
    //       "duration" : 130
    //    }

    @PostMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long serviceId,
                                                         @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering serviceOffering1 =  serviceOfferingService.updateService(serviceId, serviceOffering);

        return ResponseEntity.ok(serviceOffering1);
    }
}
