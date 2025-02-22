package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.dto.StdDto;
import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.services.StdService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/std")
@RequiredArgsConstructor
public class StdController
{
    private final StdService stdService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<StdDto> createStudent(@RequestBody StdDto stdDto)
    {
        return ResponseEntity.ok(modelMapper.map(stdService.createStd(stdDto),StdDto.class));
    }

    @GetMapping
    public ResponseEntity<List<StdDto>> getAll()
    {
        return ResponseEntity.ok(stdService.getAll());
    }


    @PutMapping(path = "/{stdId}/stdarc/{arcId}")
    public ResponseEntity<StdDto> updateStdAdmissionRecord(@PathVariable Long stdId,@PathVariable Long arcId)
    {
        return ResponseEntity.ok(stdService.updateStdAdmissionRecord(stdId,arcId));
    }

    @PutMapping(path = "/{stdId}/stdSub/{subId}")
    public ResponseEntity<StdDto> updateStudentSubjects(@PathVariable Long stdId,@PathVariable Long subId)
    {
        return ResponseEntity.ok(stdService.updateStudentSubjects(stdId,subId));
    }
//    @PutMapping(path = "/{stdId}/stdprof/{profId}")
//    public StdEntity updateStdProf(@PathVariable Long stdId,@PathVariable Long profId)
//    {
//        return stdService.updateStdProf(stdId,profId);
//    }



}
