package com.example.OTSApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Controller
public class AddController {

    private final OTSRepository otsRepository;

    public AddController(OTSRepository otsRepository) {
        this.otsRepository = otsRepository;
    }

    @GetMapping("/add")
    public String addOTSForm() {
        return "add";
    }

    @PostMapping("/add")
    public String addOTS(@RequestParam("accounts") String accounts,
                         @RequestParam("customerName") String customerName, @RequestParam("otsAmount") BigDecimal otsAmount,
                         @RequestParam("sanctionDate") LocalDate sanctionDate, @RequestParam("expiryDate") LocalDate expiryDate,
                         Model model) {


        if (sanctionDate.isBefore(LocalDate.now())) {
            model.addAttribute("error", "Sanction Date cannot be previous to today's date.");
            return "add";
        }

        if (expiryDate.isAfter(sanctionDate.plusDays(90))) {
            model.addAttribute("error", "Expiry Date cannot be more than 90 days from Sanction Date.");
            return "add";
        }

        OTS ots = new OTS();
        ots.setAccounts(accounts);
        ots.setCustomerName(customerName);
        ots.setOtsAmount(otsAmount);
        ots.setSanctionDate(sanctionDate);
        ots.setExpiryDate(expiryDate);
        ots.setUpdatedBy("admin");
        ots.setCreationTime(new Timestamp(System.currentTimeMillis()));

        otsRepository.save(ots);
        return "redirect:/dashboard";
    }
}

