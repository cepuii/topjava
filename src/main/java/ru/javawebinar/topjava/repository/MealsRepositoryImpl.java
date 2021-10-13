package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsRepositoryImpl implements MealsRepository {
    private final AtomicInteger id;
    private final List<Meal> meals;

    public MealsRepositoryImpl() {
        this.id = new AtomicInteger(1);
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
        Meal mealToUpdate = getById(meal.getId());
        if (meal.getDateTime() != null) {
            mealToUpdate.setDateTime(meal.getDateTime());
        }
        if (meal.getDescription() != null && !meal.getDescription().isEmpty()) {
            mealToUpdate.setDescription(meal.getDescription());
        }
        if (meal.getCalories() != 0) {
            mealToUpdate.setCalories(meal.getCalories());
        }
        int indexOfMeal = meals.indexOf(mealToUpdate);
        meals.set(indexOfMeal, mealToUpdate);
        return mealToUpdate;
    }

    public List<Meal> getAll() {
        return this.meals;
    }

    public Meal getById(int id) {
        Meal meal = new Meal();
        meal.setId(id);
        return meals.get(meals.indexOf(meal));
    }

}
