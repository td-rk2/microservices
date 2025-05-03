package com.Tz.Controllers;

import com.Tz.Models.ServiceOffering;
import com.Tz.ServiceOfferingService.ServiceOfferingService;
import com.Tz.dto.CategoryDTO;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

//    @PostMapping("/create")
//    public ResponseEntity<ServiceOffering> createService(SalonDTO salonDTO,
//                                                         ServiceDTO serviceDTO,
//                                                         CategoryDTO categoryDTO){
//
//        ServiceOffering serviceOffering1 =  serviceOfferingService.createService(salonDTO, serviceDTO, categoryDTO);
//
//        return ResponseEntity.ok(serviceOffering1);
//    }

}
