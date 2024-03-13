package com.pragma.OnClass.adapters.driving.http.controller;
import com.pragma.OnClass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.OnClass.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.OnClass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.OnClass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
public class TechnologyRestControllerApdapter {
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;

    private final ITechnologyResponseMapper technologyResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void>  addTechnology(@RequestBody AddTechnologyRequest request){
        technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologies(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.ok(technologyResponseMapper.
                toTechnologyResponseList(technologyServicePort.getAllTechnology(page, size)));
    }

}
