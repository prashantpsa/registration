package com.api.registration.controller;

import com.api.registration.entity.Registration;
import com.api.registration.payload.RegistrationDto;
import com.api.registration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/registration1")
public class RegistrationController {

private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<RegistrationDto> getallRegistration(){
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return dtos;

    }
    @PostMapping
    public ResponseEntity<?> CreateRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
      if (result.hasErrors()){
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
      }
        RegistrationDto regdto = registrationService.CreateRegistration(registrationDto);
        System.out.println(regdto.getMobile());
        return new ResponseEntity<>(regdto, HttpStatus.CREATED);

    }
    @DeleteMapping
    public ResponseEntity<String> DeleteRegistrationByid(@RequestParam long id){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistrationbyId(
            @PathVariable("id") long id,
            @RequestBody Registration registration){
        Registration reg=registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(reg,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistratioById(
            @PathVariable long id
    ){
        RegistrationDto dto = registrationService.getRegByid(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);


    }


}
