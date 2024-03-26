package com.pragma.OnClass.adapters.driving.http.controller;
import com.pragma.OnClass.adapters.driving.http.dto.request.AddBootCampRequest;
import com.pragma.OnClass.adapters.driving.http.mapper.IBootCampRequestMapper;

import com.pragma.OnClass.domain.api.IBootCampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
@Validated
public class BootCampRestControllerAdapter {
    private final IBootCampServicePort bootCampServicePort;
    private final IBootCampRequestMapper bootCampRequestMapper;


    @PostMapping("/")
    public ResponseEntity<Void> addBootCamp(@Valid @RequestBody AddBootCampRequest request){
        bootCampServicePort.saveBootCamp(bootCampRequestMapper.addRequestToBootCamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
