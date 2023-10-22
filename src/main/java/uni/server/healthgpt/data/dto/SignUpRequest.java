package uni.server.healthgpt.data.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SignUpRequest {
    String email;
    String password;
    String name;
    float height;
    float weight;
    String birth;
    Map<Integer,String > etcs;
}
