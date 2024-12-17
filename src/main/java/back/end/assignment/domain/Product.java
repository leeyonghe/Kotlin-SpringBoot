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
 * CREATE TABLE product (
        id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID (고유 키)',
        name VARCHAR(255) NOT NULL COMMENT '상품 이름',
        description VARCHAR(1000) COMMENT '상품 설명',
        price BIGINT NOT NULL COMMENT '상품 가격',
        stock INT NOT NULL COMMENT '재고 수량',
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
    );
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("상품 ID (고유 키)")
    private Long id;
    
    @Column(nullable = false)
    @Comment("상품 이름")
    private String name;

    @Column(nullable = true)
    @Comment("상품 설명")
    private String description;

    @Column(nullable = false)
    @Comment("상품 가격")
    private Long price;

    @Column(nullable = false)
    @Comment("재고 수량")
    private int stock;

    @OneToMany(mappedBy = "product")
    private List<BasketItems> basketItemsList = new ArrayList<>();

    @CreatedDate
    @UpdateTimestamp
    @Comment("생성 시간")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

}
