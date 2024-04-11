package com.pragma.onclass.adapters.driving.http.controller;
import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootCampResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;

import com.pragma.onclass.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
@Validated
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootCampServicePort;
    private final IBootcampRequestMapper bootCampRequestMapper;

    private final IBootcampResponseMapper bootCampResponseMapper;


    @PostMapping("/")
    public ResponseEntity<Void> addBootCamp(@Valid @RequestBody AddBootcampRequest request){
        bootCampServicePort.saveBootCamp(bootCampRequestMapper.addRequestToBootCamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BootCampResponse>> getAllBootCamps(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(defaultValue = "true")  boolean isAscName,
                                                                  @RequestParam(defaultValue = "true")  boolean isAscCapacity) {
        return ResponseEntity.ok(bootCampResponseMapper.toBootCampResponseList(bootCampServicePort.getAllBootCamps(page, size, isAscName, isAscCapacity)));
    }



}
