package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.dto.SubDto;
import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.entity.SubEntity;
import com.SchoolManagementSys.services.SubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sub")
@RequiredArgsConstructor
public class SubController
{
    private final SubService subService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<SubDto> createSub(@RequestBody SubDto subDto)
    {
        return ResponseEntity.ok(subService.createSub(subDto));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SUBJECT_VIEW')")
    public ResponseEntity<List<SubDto>> getAll()
    {
        return ResponseEntity.ok(subService.getAll());
    }

    @PutMapping(path = "/{subId}/subprof/{profId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SubDto> updateSubProf(@PathVariable Long subId, @PathVariable Long profId)
    {
        return ResponseEntity.ok(subService.updateSubProf(profId,subId));
    }


}
