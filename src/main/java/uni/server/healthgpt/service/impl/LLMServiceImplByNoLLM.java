package uni.server.healthgpt.service.impl;

import org.springframework.stereotype.Service;
import uni.server.healthgpt.data.entity.EtcFood;
import uni.server.healthgpt.data.entity.Food;
import uni.server.healthgpt.repo.FoodJpaRepo;
import uni.server.healthgpt.service.LLMService;
@Service
public class LLMServiceImplByNoLLM implements LLMService {
    private final FoodJpaRepo foodJpaRepo;

    public LLMServiceImplByNoLLM(FoodJpaRepo foodJpaRepo) {
        this.foodJpaRepo = foodJpaRepo;
    }

    @Override
    public String getChat(String param) {
        return param;
    }

    @Override
    public Food getFood(String param) {
        Food food = new Food();
        food.setName(param);
        food.setKcal(100f);
        food.setFat(100f);
        food.setProtein(100f);
        food.setDescription(param + "입니다.");
        food.setSugar(100f);

        return food;
    }

    @Override
    public EtcFood getEtcFood(String param) {
        EtcFood etcFood = new EtcFood();
        etcFood.setName(param);
        etcFood.setDescription("설명");
        etcFood.setValue(1.0f);
        return etcFood;
    }
}
