package com.Tz.Controllers;

import com.Tz.Models.ServiceOffering;
import com.Tz.ServiceOfferingService.ServiceOfferingService;
import com.Tz.dto.CategoryDTO;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    //http://localhost:5004/api/service-offering/salon/1
    @GetMapping("salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> serviceOffering1 = serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(serviceOffering1);
    }

    //http://localhost:5004/api/service-offering/1
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServicesById(
            @PathVariable Long id) throws Exception {
        ServiceOffering serviceOffering1 = serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(serviceOffering1);
    }

    //http://localhost:5004/api/service-offering/list/1,2,3
    @GetMapping("list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> ids) {
        Set<ServiceOffering> serviceOffering1 = serviceOfferingService.getAllServicesByIds(ids);
        return ResponseEntity.ok(serviceOffering1);
    }

}
