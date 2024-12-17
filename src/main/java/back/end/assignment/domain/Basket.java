package back.end.assignment.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("장바구니 ID (고유 키)")    
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="c_id", referencedColumnName = "id")
    @Comment("고객 ID (외래 키)")    
    private Customer customer;

    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<BasketItems> basketItemsList = new ArrayList<>();

    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Orders> ordersList = new ArrayList<>();
    
    @Column(nullable = true)
    @ColumnDefault("0")
    private int sold;

    @CreatedDate
    @UpdateTimestamp
    @Comment("생성 시간")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

}
