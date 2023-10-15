package uni.server.healthgpt.data.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    String email;
    String password;
    String name;
    float height;
    float weight;
    String birth;
}
