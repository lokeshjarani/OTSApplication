package com.example.OTSApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class EditController {

    @Autowired
    private OTSRepository otsRepository;

    @GetMapping("/edit")
    public String editOTS(Model model) {
        List<OTS> otsList = otsRepository.findAll();
        model.addAttribute("otsList", otsList);
        return "edit";
    }

    @PostMapping("/edit")
    public String editOTS(@RequestParam("id") Long id, @RequestParam("accounts") String accounts,
                          @RequestParam("customerName") String customerName, @RequestParam("otsAmount") BigDecimal otsAmount,
                          @RequestParam("sanctionDate") LocalDate sanctionDate, @RequestParam("expiryDate") LocalDate expiryDate,
                          RedirectAttributes redirectAttributes) {
        Optional<OTS> optionalOTS = otsRepository.findById(id);
        if (optionalOTS.isPresent()) {
            OTS ots = optionalOTS.get();

            if (sanctionDate.isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("error", "Sanction Date cannot be previous to today's date.");
                return "redirect:/edit";
            }
            if (expiryDate.isAfter(sanctionDate.plusDays(90))) {
                redirectAttributes.addFlashAttribute("error", "Expiry Date cannot be more than 90 days from Sanction Date.");
                return "redirect:/edit";
            }

            ots.setAccounts(accounts);
            ots.setCustomerName(customerName);
            ots.setOtsAmount(otsAmount);
            ots.setSanctionDate(sanctionDate);
            ots.setExpiryDate(expiryDate);

            otsRepository.save(ots);
            redirectAttributes.addFlashAttribute("success", "OTS entry updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "OTS entry not found");
        }
        return "redirect:/dashboard";
    }


    @GetMapping("/delete/{id}")
    public String deleteOTS(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<OTS> optionalOTS = otsRepository.findById(id);
        if (optionalOTS.isPresent()) {
            OTS ots = optionalOTS.get();
            otsRepository.delete(ots);
            redirectAttributes.addFlashAttribute("success", "OTS entry deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "OTS entry not found");
        }
        return "redirect:/dashboard";
    }
}
