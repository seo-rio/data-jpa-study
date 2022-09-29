package study.datajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA이기 때문에 기본 생성자 필요 protected는 아무곳에서나 접근하지 못하게 하기 위해 JPA 스펙상 필요 (private은 안됨.)
@ToString(of = {"id", "username", "age"})
@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team")) // EntityGraph를 엔티티에서 사용하는 예시 (참고용, 많이 사용 안함)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) // 다:1 관계 (하나의 회원은 팀 하나에 속함)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null) { // team이 null이 아닌 경우에만 팀 변경
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
