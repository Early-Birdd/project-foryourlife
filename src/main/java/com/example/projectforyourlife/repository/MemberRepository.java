package com.example.projectforyourlife.repository;

import com.example.projectforyourlife.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 상속받아 사용하면 빈 등록을 위한 @Repository 사용이 필요없다.
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
