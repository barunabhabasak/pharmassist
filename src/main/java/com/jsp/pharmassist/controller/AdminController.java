package com.jsp.pharmassist.controller;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.service.AdminService;
import com.jsp.pharmassist.util.AppResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    private AdminService adminService;
    private AppResponseBuilder builder;
    
    public AdminController(AdminService adminService, AppResponseBuilder builder) {
        this.adminService = adminService;
        this.builder = builder;
    }

	@PostMapping("/admins")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.save(admin);
    }
    
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable String id) {
        return adminService.findById(id);
    }
    
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        admin.setAdminId(id);
        return adminService.save(admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminService.deleteById(id);
    }
}
