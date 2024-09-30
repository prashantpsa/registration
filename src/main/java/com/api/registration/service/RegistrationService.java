package com.api.registration.service;

import com.api.registration.entity.Registration;
import com.api.registration.exception.ResourceNotFound;
import com.api.registration.payload.RegistrationDto;
import com.api.registration.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper, ModelMapper modelMapper1) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper1;
    }

    public List<RegistrationDto> getRegistrations(){
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos=registrations.stream().map(r->maptoDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto CreateRegistration(RegistrationDto registrationDto) {
      Registration registration=maptoEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto=maptoDto(savedEntity);
        return dto;

    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r= registrationRepository.findById(id).get();
       r.setName(registration.getName());
       r.setEmail(registration.getEmail());
       r.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;
    }
    Registration maptoEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        return registration;

    }
    RegistrationDto maptoDto(Registration registration){
        RegistrationDto registrationDto = modelMapper.map(registration, RegistrationDto.class);
        return registrationDto;

    }

    public RegistrationDto getRegByid(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("record not found")
        );
        return maptoDto(registration);
    }
}
