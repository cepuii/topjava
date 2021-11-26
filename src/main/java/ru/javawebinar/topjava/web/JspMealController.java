package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {
    private final MealService mealService;

    public JspMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping()
    public String get(HttpServletRequest request) {
        request.setAttribute("meals", MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY));
        return "meals";
    }

    @GetMapping("/edit")
    public String set(HttpServletRequest request, @RequestParam int id) {
        request.setAttribute("meal", mealService.get(id, SecurityUtil.authUserId()));
        return "mealForm";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam int id) {
        mealService.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @PostMapping({"/edit", "/create"})
    public String createOrUpdate(@ModelAttribute("meal") Meal meal) {
        if (meal.isNew()) {
            mealService.create(meal, SecurityUtil.authUserId());
        } else {
            mealService.update(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

}
