package back.end.assignment.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("오더 ID (고유 키)")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)    
    @JoinColumn(name="b_id", insertable=true)
    @Comment("장바구니 ID (외래 키)")
    private Basket basket;
    
    @Column(nullable = true)
    @Comment("txn number")
    private String txn;

    @CreatedDate
    @UpdateTimestamp
    @Comment("생성 시간")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

}