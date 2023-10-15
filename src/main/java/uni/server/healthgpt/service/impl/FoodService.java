package uni.server.healthgpt.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uni.server.healthgpt.data.entity.EtcFood;
import uni.server.healthgpt.data.entity.Food;
import uni.server.healthgpt.data.entity.Today;
import uni.server.healthgpt.data.entity.User;
import uni.server.healthgpt.repo.EtcJpaRepo;
import uni.server.healthgpt.repo.FoodJpaRepo;
import uni.server.healthgpt.repo.TodayJpaRepo;
import uni.server.healthgpt.repo.UserJpaRepo;
import uni.server.healthgpt.service.LLMService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodJpaRepo foodJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final TodayJpaRepo todayJpaRepo;
    private final EtcJpaRepo etcJpaRepo;
    private final LLMService llmService;
    public Optional<User> addToday(User user, String day) {
        Today existingToday = todayJpaRepo.findByDayAndUser(day, user);
        if (existingToday != null) {
            return Optional.empty();
        }
        userJpaRepo.save(user);
        Today today = Today.builder()
                .day(day)
                .user(user)
                .build();

        todayJpaRepo.save(today);
        user.getTodays().add(today);
        userJpaRepo.save(user);

        return userJpaRepo.findByUid(user.getUid());
    }

    public Optional<User> setBreakfast(User user, String day, String param){
        Today today = todayJpaRepo.findByDayAndUser(day, user);
        if (today == null) {
            addToday(user, day);

        }
        today = todayJpaRepo.findByDayAndUser(day, user);
        userJpaRepo.save(user);
        Food food;
        if (foodJpaRepo.findByName(param) == null){
            food = getFoodWithEtcFood(llmService.getFood(param), List.of("코카인"));
            food.setDay_selected(food.getDay_selected()+1);
            food.setTotal_selected(food.getTotal_selected()+1);
        }else if(today.getBreakFast() == null){
            food = foodJpaRepo.findByName(param);
            food.setDay_selected(food.getDay_selected()+1);
            food.setTotal_selected(food.getTotal_selected()+1);
        }else{
            food = foodJpaRepo.findByName(param);
        }
        foodJpaRepo.save(food);
        today.setBreakFast(food);
        todayJpaRepo.save(today);

        return userJpaRepo.findByUid(user.getUid());
    }
    public Optional<User> setLunch(User user, String day, String param){
        Today today = todayJpaRepo.findByDayAndUser(day, user);
        if (today == null) {
            addToday(user, day);
        }
        today = todayJpaRepo.findByDayAndUser(day, user);
        userJpaRepo.save(user);
        Food food;
        if (foodJpaRepo.findByName(param) == null){
            food = getFoodWithEtcFood(llmService.getFood(param), List.of("코카인"));
            food.setDay_selected(food.getDay_selected()+1);
            food.setTotal_selected(food.getTotal_selected()+1);
        }else if(today.getLunch() == null){
            food = foodJpaRepo.findByName(param);
            food.setDay_selected(food.getDay_selected()+1);
            food.setTotal_selected(food.getTotal_selected()+1);
        }else{
            food = foodJpaRepo.findByName(param);
        }
        foodJpaRepo.save(food);
        today.setLunch(food);
        todayJpaRepo.save(today);

        return userJpaRepo.findByUid(user.getUid());
    }
    public Optional<User> setDinner(User user, String day, String param){
        Today today = todayJpaRepo.findByDayAndUser(day, user);
        if (today == null) {
            addToday(user, day);
        }
        today = todayJpaRepo.findByDayAndUser(day, user);
        userJpaRepo.save(user);
        Food food;
        if (foodJpaRepo.findByName(param) == null){
            food = getFoodWithEtcFood(llmService.getFood(param), List.of("코카인"));
            food.setDay_selected(food.getDay_selected()+1);
            food.setTotal_selected(food.getTotal_selected()+1);
        }else if(today.getDinner() == null){
            food = foodJpaRepo.findByName(param);
            food.setDay_selected(food.getDay_selected()+1);
            food.setTotal_selected(food.getTotal_selected()+1);
        }else{
            food = foodJpaRepo.findByName(param);
        }
        foodJpaRepo.save(food);
        today.setDinner(food);
        todayJpaRepo.save(today);

        return userJpaRepo.findByUid(user.getUid());
    }

    public Food getFoodWithEtcFood(Food food, List<String> etcFoodNames) {

        etcFoodNames.forEach((it)->{
            EtcFood etcFood = llmService.getEtcFood(it);
            etcJpaRepo.save(etcFood);
            food.getEtc_Foods().add(etcFood);
            foodJpaRepo.save(food);
        });
        return foodJpaRepo.getReferenceById(food.getId());
    }
    public Optional<User> removeDinner(User user, String day){
        Today today = todayJpaRepo.findByDayAndUser(day, user);
        if (today == null) {
            return Optional.empty();
        }
        Food food = today.getDinner();
        if(food == null){
            return Optional.of(user);
        }
        food.setDay_selected(food.getDay_selected()-1);
        food.setTotal_selected(food.getTotal_selected()-1);
        foodJpaRepo.save(food);
        userJpaRepo.save(user);
        today.setDinner(null);
        todayJpaRepo.save(today);

        return userJpaRepo.findByUid(user.getUid());
    }
    public Optional<User> removeBreakfast(User user, String day){
        Today today = todayJpaRepo.findByDayAndUser(day, user);
        if (today == null) {
            return Optional.empty();
        }
        Food food = today.getBreakFast();
        if(food == null){
            return Optional.of(user);
        }
        food.setDay_selected(food.getDay_selected()-1);
        food.setTotal_selected(food.getTotal_selected()-1);
        foodJpaRepo.save(food);
        userJpaRepo.save(user);
        today.setBreakFast(null);
        todayJpaRepo.save(today);

        return userJpaRepo.findByUid(user.getUid());
    }
    public Optional<User> removeLunch(User user, String day){
        Today today = todayJpaRepo.findByDayAndUser(day, user);
        if (today == null) {
            return Optional.empty();
        }
        Food food = today.getLunch();
        if(food == null){
            return Optional.of(user);
        }
        food.setDay_selected(food.getDay_selected()-1);
        food.setTotal_selected(food.getTotal_selected()-1);
        foodJpaRepo.save(food);
        userJpaRepo.save(user);
        today.setLunch(null);
        todayJpaRepo.save(today);

        return userJpaRepo.findByUid(user.getUid());
    }
    public List<Food> getFoodLists(){
        return foodJpaRepo.findAll();
    }
}
