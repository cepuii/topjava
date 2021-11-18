package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = get(id, userId);
        if (meal != null){
            crudRepository.delete(meal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> meal = crudRepository.findById(id);
        return meal.isPresent() && meal.get().getUser().getId() == userId ? meal.get() : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll().stream()
                .filter( m -> m.getUser().getId() != null  && m.getUser().getId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
