package ru.javawebinar.topjava.web;

import javafx.util.converter.LocalDateTimeStringConverter;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.repository.MealsRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final int CALORIES_PER_DAY = 2000;
    MealsRepository mealsRepository;

    public MealServlet() {
        super();
        this.mealsRepository = new MealsRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if ("delete".equalsIgnoreCase(action)) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealsRepository.delete(mealId);
            forward = "meals.jsp";
            request.setAttribute("meals", MealsUtil.filteredByStreams(mealsRepository.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY));
        } else if ("edit".equalsIgnoreCase(action)) {
            forward = "editMeal.jsp";
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealsRepository.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else {
            forward = "meals.jsp";
            request.setAttribute("meals", MealsUtil.filteredByStreams(mealsRepository.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY));
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
            int id = Integer.parseInt(mealid);
            mealsRepository.update(id, meal);
        }
        request.setAttribute("meals", MealsUtil.filteredByStreams(mealsRepository.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY));
        RequestDispatcher view = request.getRequestDispatcher("meals.jsp");
        view.forward(request, response);
    }
}


