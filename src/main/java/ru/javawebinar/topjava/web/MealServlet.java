package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.Repository;
import ru.javawebinar.topjava.repository.RepositoryMeals;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private static final int CALORIES_PER_DAY = 2000;
    private Repository mealsRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        mealsRepository = new RepositoryMeals();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id;
        switch (action == null ? "listMeals" : action) {
            case "edit":
                id = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealsRepository.getById(id);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("editMeal.jsp").forward(request, response);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("mealId"));
                mealsRepository.delete(id);
                response.sendRedirect("http://localhost:8080/topjava/meals");
                break;
            default:
                request.setAttribute("meals", MealsUtil.filteredByStreamsWithoutTime(mealsRepository.getAll(), CALORIES_PER_DAY));
                request.getRequestDispatcher("meals.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        if ("submit".equals(request.getParameter("action"))) {
            Meal meal = new Meal(LocalDateTime.parse(request.getParameter("date")),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            String id = request.getParameter("mealId");
            if ("0".equals(id) || id == null || id.isEmpty()) {
                mealsRepository.add(meal);
            } else {
                meal.setId(Integer.parseInt(id));
                mealsRepository.update(meal);
            }
        }
        response.sendRedirect("http://localhost:8080/topjava/meals");
    }
}


