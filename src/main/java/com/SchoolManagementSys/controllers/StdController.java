package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.services.StdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/std")
public class StdController
{
    private final StdService stdService;

    public StdController(StdService stdService) {
        this.stdService = stdService;
    }

    @PostMapping
    public StdEntity createStudent(@RequestBody StdEntity stdEntity)
    {
        return stdService.createStd(stdEntity);
    }

    @GetMapping
    public List<StdEntity> getAll()
    {
        return stdService.getAll();
    }


    @PutMapping(path = "/{stdId}/stdarc/{arcId}")
    public StdEntity updateStdAdmissionRecord(@PathVariable Long stdId,@PathVariable Long arcId)
    {
        return stdService.updateStdAdmissionRecord(stdId,arcId);
    }

    @PutMapping(path = "/{stdId}/stdprof/{profId}")
    public StdEntity updateStdProf(@PathVariable Long stdId,@PathVariable Long profId)
    {
        return stdService.updateStdProf(stdId,profId);
    }



}
