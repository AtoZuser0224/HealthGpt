package uni.server.healthgpt.service;

import uni.server.healthgpt.data.entity.EtcFood;
import uni.server.healthgpt.data.entity.Food;

public interface LLMService {
    String getChat(String param);

    Food getFood(String param);


    EtcFood getEtcFood(String param);
}
