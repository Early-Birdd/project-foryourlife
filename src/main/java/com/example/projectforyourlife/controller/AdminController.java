package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.response.MemberListResponseDto;
import com.example.projectforyourlife.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberListResponseDto>> getAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        return new ResponseEntity(adminService.getAllMember(pageable).getContent(), HttpStatus.OK);
    }
}
