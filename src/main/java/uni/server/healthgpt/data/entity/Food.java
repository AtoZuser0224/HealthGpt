package uni.server.healthgpt.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter
@Setter
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "foods")
public class Food {

    @Id //primaryKey 임을 알린다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // primaryKey 생성 전략을 DB에 위임한다는 뜻.
    // primaryKey 필드를 auto_increment로 설정해 놓은 경우와 같다.
    private long id;

    private String name;

    private long total_selected;

    private int day_selected;

    private float kcal;

    private float sugar;

    private float protein;

    private float fat;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "etc_foods_id")
    private List<EtcFood> etc_Foods = new ArrayList<>();
}
