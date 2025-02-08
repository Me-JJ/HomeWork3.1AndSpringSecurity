package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.entity.SubEntity;
import com.SchoolManagementSys.services.SubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sub")
public class SubController
{
    private final SubService subService;

    public SubController(SubService subService) {
        this.subService = subService;

    }

    @PostMapping
    public SubEntity createSub(@RequestBody SubEntity subEntity)
    {
        return subService.createSub(subEntity);
    }

    @GetMapping
    public List<SubEntity> getAll()
    {
        return subService.getAll();
    }

    @PutMapping(path = "/{subId}/substd/{stdId}")
    public StdEntity updateStds(@PathVariable Long subId, @PathVariable Long stdId)
    {
        return subService.updateStds(subId,stdId);
    }
}
