package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        assertEquals(mealUser, mealService.get(MEAL_ID, USER_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getNoAccess() {
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        mealService.delete(MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNoAccess() {
        assertThrows(NotFoundException.class, () -> mealService.delete(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getAll() {
        List<Meal> meals = mealService.getAll(USER_ID);
        assertArrayEquals(meals.toArray(), userMeals.toArray());
    }

    @Test
    public void update() {
        Meal update = getUpdatedMeal();
        mealService.update(update, USER_ID);
        assertEquals(update, mealService.get(MEAL_ID, USER_ID));
    }

    @Test
    public void updateNoAccess() {
        Meal update = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> mealService.update(update, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal createMeal = mealService.create(getNewMeal(), USER_ID);
        int newId = createMeal.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(newId);
        assertEquals(createMeal, newMeal);
        assertEquals(mealService.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () -> mealService.create(
                new Meal(LocalDateTime.of(2021, 7, 11, 6, 51, 10),
                        "Breakfast", 2128), USER_ID));
    }
}