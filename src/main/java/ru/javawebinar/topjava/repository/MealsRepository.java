package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;

public interface MealsRepository {

    public void add(Meal meal);
    public void delete(int mealId);
    public void update(int id, Meal meal);
    public Map<Integer, Meal> getAll();
    public Meal getById(int mealId);
}
