package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public boolean checkAccessToMeal(int id, int userId) {
        return repository.get(id).getUserId() == userId;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void update(Meal meal) {
        if (checkAccessToMeal(meal.getId(), meal.getUserId())) {
            checkNotFoundWithId(repository.save(meal), meal.getId());
        } else {
            throw new NotFoundException("no access for this meal");
        }
    }

    public void delete(int id, int userId) {
        if (checkAccessToMeal(id, userId)) {
            checkNotFoundWithId(repository.delete(id), id);
        } else {
            throw new NotFoundException("no access for this meal");
        }
    }

    public Meal get(int id, int userId) {
        if (checkAccessToMeal(id, userId)) {
            return checkNotFoundWithId(repository.get(id), id);
        } else {
            throw new NotFoundException("no access for this meal");
        }
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }
}