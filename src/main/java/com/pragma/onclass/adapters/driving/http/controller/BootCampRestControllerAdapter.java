package com.pragma.onclass.adapters.driving.http.controller;
import com.pragma.onclass.adapters.driving.http.dto.request.AddBootCampRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootCampResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IBootCampRequestMapper;

import com.pragma.onclass.adapters.driving.http.mapper.IBootCampResponseMapper;
import com.pragma.onclass.domain.api.IBootCampServicePort;
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
public class BootCampRestControllerAdapter {
    private final IBootCampServicePort bootCampServicePort;
    private final IBootCampRequestMapper bootCampRequestMapper;

    private final IBootCampResponseMapper bootCampResponseMapper;


    @PostMapping("/")
    public ResponseEntity<Void> addBootCamp(@Valid @RequestBody AddBootCampRequest request){
        bootCampServicePort.saveBootCamp(bootCampRequestMapper.addRequestToBootCamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BootCampResponse>> getAllBootCamps(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(defaultValue = "true")  boolean isAscName,
                                                                  @RequestParam(defaultValue = "true")  boolean isAscCapacity) {
        return ResponseEntity.ok(bootCampResponseMapper.toBootCampResponseList(bootCampServicePort.getAllBootCamps(page, size, isAscName, isAscCapacity)));
    }



}
