package com.Tz.ServiceOfferingService;

import com.Tz.Models.ServiceOffering;
import com.Tz.dto.CategoryDTO;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO,
                                  ServiceDTO serviceDTO,
                                  CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering) throws Exception;

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId,Long categoryId);

    Set<ServiceOffering> getAllServicesByIds(Set<Long> Ids);

    ServiceOffering getServiceById(Long serviceId) throws Exception;
}
