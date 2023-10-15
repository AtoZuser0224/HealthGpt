package uni.server.healthgpt.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Today {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String day;
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    @JsonBackReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "breakfast_id")
    private Food breakFast;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lunch_id")
    private Food lunch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dinner_id")
    private Food dinner;


}
