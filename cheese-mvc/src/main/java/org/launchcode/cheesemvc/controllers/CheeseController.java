package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {

    @RequestMapping(value="")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeesForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String description) {
        Cheese newCheese = new Cheese(cheeseName, description);
        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String showRemoveForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method=RequestMethod.POST)
    public String processRemoveForm(@RequestParam int[] cheeseIds) {
        for(int cheeseId : cheeseIds) {
         CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }

}
