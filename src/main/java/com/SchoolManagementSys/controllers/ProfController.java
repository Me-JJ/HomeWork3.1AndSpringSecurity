package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.entity.ProfEntity;
import com.SchoolManagementSys.services.ProfService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/prof")
public class ProfController
{
    private final ProfService profService;

    public ProfController(ProfService profService) {
        this.profService = profService;
    }

    @PostMapping
    public ProfEntity createProf(@RequestBody ProfEntity profEntity)
    {
//        System.out.println("TITLE --->"+profEntity.getTitle());
        return profService.createProf(profEntity);
    }

    @GetMapping
    public List<ProfEntity> getAll()
    {
        return profService.getAll();
    }

    @PutMapping(path = "/{profId}/profsub/{subId}")
    public ProfEntity updateProfSub(@PathVariable Long profId,@PathVariable Long subId)
    {
        return profService.updateProfSub(profId,subId);

    }

}
