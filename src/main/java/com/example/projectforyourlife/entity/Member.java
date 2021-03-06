package com.example.projectforyourlife.entity;

import com.example.projectforyourlife.enumclass.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //공부하기
@AllArgsConstructor
public class Member extends BaseTimeEntity{

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    //JPA에 Enum을 String으로 저장
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateName(String name){

        this.name = name;
    }

    public void updateNickname(String nickname){

        this.nickname = nickname;
    }
}
