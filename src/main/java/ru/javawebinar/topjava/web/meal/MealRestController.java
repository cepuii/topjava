package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final int userId = SecurityUtil.authUserId();
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create{}", meal);
        return service.create(meal);
    }

    public void update(Meal meal) {
        log.info("update{}", meal);
        service.update(meal);
    }

    public Meal get(int id) {
        log.info(" get with id{}, userId{}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        log.info("delete with id{} userId{}", id, userId);
        service.delete(id, userId);
    }

    public List<MealTo> getAll() {
        log.info("getAll for user{}", userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllByDateTime(LocalDate startDate, LocalDate endDate,
                                         LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getFilteredTos(MealsUtil.getFilteredByDays(
                        service.getAll(userId), startDate, endDate),
                SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }
}