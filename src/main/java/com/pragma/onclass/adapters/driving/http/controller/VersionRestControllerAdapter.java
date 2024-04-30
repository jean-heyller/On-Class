package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionResponseMapper;
import com.pragma.onclass.domain.api.IVersionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
@Validated
public class VersionRestControllerAdapter {
    private final IVersionServicePort versionServicePort;

    private final IVersionRequestMapper versionRequestMapper;

    private final IVersionResponseMapper versionResponseMapper;


    @PostMapping("/")
    public ResponseEntity<Void> addVersion(@Valid @RequestBody AddVersionRequest request){
        versionServicePort.saveVersion(versionRequestMapper.addRequestToVersion(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<VersionResponse>> getAllVersions(@RequestParam Integer page, @RequestParam Integer size,
                                                            @RequestParam(defaultValue = "") String isAscName,
                                                            @RequestParam(defaultValue = "") String isAscDate,
                                                            @RequestParam(defaultValue = "") String isAscQuota,
                                                            @RequestParam(required = false) Long bootcampId){
        return ResponseEntity.ok(versionResponseMapper.toVersionResponseList(
                versionServicePort.getAllVersions(page, size, isAscName, isAscDate,
                        isAscQuota, bootcampId)));

    }


}
