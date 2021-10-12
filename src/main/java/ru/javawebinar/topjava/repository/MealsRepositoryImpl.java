package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsRepositoryImpl implements MealsRepository{
    private AtomicInteger id;
    private Map<Integer, Meal> meals;

    public MealsRepositoryImpl() {
        this.id = new AtomicInteger(1);
        this.meals = new ConcurrentHashMap<>();
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
                meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public void add(Meal meal) {
        meals.put(id.get(),new Meal(id.getAndIncrement(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }
    public void delete(int mealId) {
        meals.remove(mealId);
    }
    public void update(int id, Meal meal) {

    }
    public Map<Integer, Meal> getAll() {
        return this.meals;
    }
    public Meal getById(int mealId) {
        return meals.get(mealId);
    }

}
