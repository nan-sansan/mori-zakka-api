package idv.nj.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account", length = 24, nullable = false, updatable = false, unique = true)
    private String account;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "nick_name", length = 20, nullable = false)
    private String nickName;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name="birthday")
    private LocalDate birthday;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<Order> orders = new ArrayList<>();
}
