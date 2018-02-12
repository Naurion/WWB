package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Bill;
import web.model.BillRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BillsController {

    @Autowired
    BillRepository repository;
    private static List<Bill> bills = new ArrayList<Bill>();

    @RequestMapping(value = {"/billList"}, method = RequestMethod.GET)
    public String billListPage(@RequestParam(value = "personId", required = true) long personId, Model model) {
        bills = (List<Bill>) repository.findByOwnerId(personId);
        model.addAttribute("bills", bills);
        model.addAttribute("ownerId", personId);
        return "billList";
    }

    @RequestMapping(value = "/addBill", method = RequestMethod.GET)
    public String addBillPage(@RequestParam(value = "personId", required = true) long personId, Model model) {
        Bill bill = new Bill();
        bill.setOwnerId(personId);
        model.addAttribute("bill", bill);
        return "createBill";
    }

    @RequestMapping(value = "/addBill", method = RequestMethod.POST)
    public String createBill(@RequestParam(value = "personId", required = true) long personId,
                             Model model, @ModelAttribute Bill bill) {
        long ownerId = personId;
        double money = bill.getMoney();
        Bill newBill = new Bill(ownerId, money);
        repository.save(newBill);
        bills.add(newBill);

        return "redirect:/personList";
    }


}
