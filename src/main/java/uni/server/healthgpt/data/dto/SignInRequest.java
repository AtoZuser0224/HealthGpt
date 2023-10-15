package uni.server.healthgpt.data.dto;

import lombok.Data;

@Data
public class SignInRequest {
    String email;
    String password;
}
