package com.example.competitionmanagment.controller;


import com.example.competitionmanagment.service.serviceInterface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("")
    public ResponseEntity fetchAllAccount(){
        return ResponseEntity.ok(this.adminService.fechUsers());
    }



}
