package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese) {
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

    @RequestMapping(value="edit/{cheeseId}")
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese c = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", c);
        return "cheese/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String processEditForm(Model model, String name, String description, @PathVariable int cheeseId) {
        Cheese c = CheeseData.getById(cheeseId);
        c.setName(name);
        return "redirect:";
    }

}
