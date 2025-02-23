package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.dto.ProfDto;
import com.SchoolManagementSys.entity.ProfEntity;
import com.SchoolManagementSys.services.ProfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/prof")
@RequiredArgsConstructor
public class ProfController
{
    private final ProfService profService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ProfDto> createProf(@RequestBody ProfDto profDto)
    {
//        System.out.println("TITLE --->"+profEntity.getTitle());
        return ResponseEntity.ok(profService.createProf(profDto));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PROF_VIEW')")
    public ResponseEntity<List<ProfDto>> getAll()
    {
        return ResponseEntity.ok(profService.getAll());
    }

//    @PutMapping(path = "/{profId}/profsub/{subId}")
//    public ResponseEntity<ProfDto> updateProfSub(@PathVariable Long profId,@PathVariable Long subId)
//    {
//        return ResponseEntity.ok(profService.updateProfSub(profId,subId));
//
//    }
    @PutMapping(path = "/{profId}/profstd/{stdId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProfDto> updateProfStudents(@PathVariable Long profId,@PathVariable Long stdId)
    {
        return ResponseEntity.ok(profService.updateProfStds(profId,stdId));

    }


}
