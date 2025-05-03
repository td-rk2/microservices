package com.Tz.ServiceOfferingService.Impl;

import com.Tz.Models.ServiceOffering;
import com.Tz.Repositories.ServiceOfferingRepository;
import com.Tz.ServiceOfferingService.ServiceOfferingService;
import com.Tz.dto.CategoryDTO;
import com.Tz.dto.SalonDTO;
import com.Tz.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDTO.getImage());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering) throws Exception{

        ServiceOffering serviceOffering1 = serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering1==null)
        {
            throw new Exception("Service not found with id:" +serviceId);
        }

        serviceOffering1.setImage(serviceOffering.getImage());
        serviceOffering1.setName(serviceOffering.getName());
        serviceOffering1.setDescription(serviceOffering.getDescription());
        serviceOffering1.setPrice(serviceOffering.getPrice());
        serviceOffering1.setDuration(serviceOffering.getDuration());

        return serviceOfferingRepository.save(serviceOffering1);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> serviceOffering1 = serviceOfferingRepository.findBySalonId(salonId);

        if(categoryId!=null)
        {
            serviceOffering1 = serviceOffering1.stream().filter((service)->service.getCategoryId()!=null &&
        service.getCategoryId()==categoryId).collect(Collectors.toSet());
        }

        return serviceOffering1;
    }

    @Override
    public Set<ServiceOffering> getAllServicesByIds(Set<Long> ids) {
        List<ServiceOffering> serviceOffering1 =  serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(serviceOffering1);
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) throws Exception {

        ServiceOffering serviceOffering1 = serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering1==null)
        {
            throw new Exception("Service not found with id:" +serviceId);
        }
        return serviceOffering1;
    }
}
