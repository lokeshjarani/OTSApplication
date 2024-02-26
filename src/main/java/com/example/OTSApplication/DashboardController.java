package com.example.OTSApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private OTSRepository otsRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<OTS> otsList = otsRepository.findAll();
        model.addAttribute("otsList", otsList);
        return "dashboard";
    }


}
