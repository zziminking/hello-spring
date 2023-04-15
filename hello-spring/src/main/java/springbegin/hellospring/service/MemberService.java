package springbegin.hellospring.service;

import springbegin.hellospring.domain.Member;
import springbegin.hellospring.repository.MemberRepository;
import springbegin.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();;

    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
    public long join (Member member) {
        // 같은 이름이 있는 중복 회원은 X
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
       });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
