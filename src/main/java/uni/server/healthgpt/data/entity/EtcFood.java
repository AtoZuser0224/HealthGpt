package uni.server.healthgpt.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtcFood {
    @Id //primaryKey 임을 알린다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // primaryKey 생성 전략을 DB에 위임한다는 뜻.
    // primaryKey 필드를 auto_increment로 설정해 놓은 경우와 같다.
    private long id;

    private String name;

    private float value;

    private String description;
}
