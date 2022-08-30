package study.datajpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    protected Member() {} // JPA이기 때문에 기본 생성자 필요 protected는 아무곳에서나 접근하지 못하게 하기 위해 JPA 스펙상 필요 (private은 안됨.)

    public Member(String username) {
        this.username = username;
    }
}
