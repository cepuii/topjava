package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.repository.MealsRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private static final int CALORIES_PER_DAY = 2000;
    MealsRepository mealsRepository;

    public MealServlet() {
        super();
        this.mealsRepository = new MealsRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        int id;
        switch (action == null ? "listMeals" : action) {
            case "edit":
                forward = "editMeal.jsp";
                id = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealsRepository.getById(id);
                request.setAttribute("meal", meal);
                break;
            case "delete":
                forward = "meals.jsp";
                id = Integer.parseInt(request.getParameter("mealId"));
                mealsRepository.delete(id);
                request.setAttribute("meals", MealsUtil.filteredByStreamsWithoutTime(mealsRepository.getAll(), CALORIES_PER_DAY));
                break;
            case "listMeals":
            default:
                forward = "meals.jsp";
                request.setAttribute("action", "listMeals");
                request.setAttribute("meals", MealsUtil.filteredByStreamsWithoutTime(mealsRepository.getAll(), CALORIES_PER_DAY));
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        meal.setDateTime(LocalDateTime.parse(request.getParameter("date")));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String mealid = request.getParameter("mealid");
        if (mealid == null || mealid.isEmpty()) {
            mealsRepository.add(meal);
        } else {
            mealsRepository.update(meal);
        }
        request.setAttribute("meals", MealsUtil.filteredByStreamsWithoutTime(mealsRepository.getAll(), CALORIES_PER_DAY));
        RequestDispatcher view = request.getRequestDispatcher("meals.jsp");
        view.forward(request, response);

    }
}


