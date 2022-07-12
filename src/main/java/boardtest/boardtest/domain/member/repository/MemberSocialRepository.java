package boardtest.boardtest.domain.member.repository;

import boardtest.boardtest.domain.member.entity.MemberSocial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSocialRepository extends JpaRepository<MemberSocial, Long> {
    Optional<MemberSocial> findByEmail(String Email);
}
