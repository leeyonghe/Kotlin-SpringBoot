package back.end.assignment.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CREATE TABLE customer (
        id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '고객 ID (고유 키)',
        name VARCHAR(50) NOT NULL UNIQUE COMMENT '고객 이름',
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
    );
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("고객 ID (고유 키)")
    private Long id;
    
    @Column(nullable = false)
    @Comment("고객 이름")
    private String name;
    
    @OneToMany(mappedBy = "customer")
    private List<Basket> basketList = new ArrayList<>();

    @CreatedDate
    @UpdateTimestamp
    @Comment("생성 시간")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

}


