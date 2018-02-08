package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Bill;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BillsController {
    private List<Bill> bills = new ArrayList<Bill>();

    @RequestMapping(value = "/addBill", method = RequestMethod.GET)
    public String addBillPage(@RequestParam(value = "personId", required = true) long personId, Model model) {
        Bill bill = new Bill();
        bill.setOwnerId(personId);
        model.addAttribute("bill", bill);
        return "createBill";
    }

    @RequestMapping(value = "/addBill", method = RequestMethod.POST)
    public String createBill(@RequestParam(value = "personId", required = true) long person,
                             Model model, @ModelAttribute Bill bill) {
        long ownerId = bill.getOwnerId();
        double money = bill.getMoney();
        Bill newBill = new Bill(ownerId, money);
        bills.add(newBill);

        return "redirect:/personList";
    }


}
