package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryMealsInMemory implements RepositoryMeals {
    private final AtomicInteger id;
    private final List<Meal> meals;

    public RepositoryMealsInMemory() {
        this.id = new AtomicInteger(0);
        this.meals = new CopyOnWriteArrayList<>();
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        this.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public Meal add(Meal meal) {
        meals.add(new Meal(id.getAndIncrement(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        return meals.get(meals.size() - 1);
    }

    public void delete(int id) {
        meals.remove(getById(id));
    }

    public Meal update(Meal meal) {
        return meals.set(meals.indexOf(meal), meal);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(meals);
    }

    public Meal getById(int id) {
        Meal meal = new Meal();
        meal.setId(id);
        return meals.get(meals.indexOf(meal));
    }

}
