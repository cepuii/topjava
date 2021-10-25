package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID = START_SEQ + 3;

    public static final Meal mealAdmin = new Meal(MEAL_ID - 1, LocalDateTime.of(2020, 12, 19, 10, 7, 4), "Admin Breakfast", 416);
    public static final Meal mealUser = new Meal(MEAL_ID, LocalDateTime.of(2021, 7, 11, 6, 51, 10), "Breakfast", 2128);
    public static final Meal mealUser2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, 11, 12, 5, 31, 33), "Breakfast", 1084);

    public static List<Meal> userMeals = new ArrayList<>();

    static {
        userMeals.add(mealUser);
        userMeals.add(mealUser2);
    }

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.now(), "new meal", 1111);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(mealUser);
        updated.setDateTime(LocalDateTime.of(1999, 12, 19, 10, 7, 4));
        updated.setDescription("update meal");
        updated.setCalories(999);
        return updated;
    }
}
