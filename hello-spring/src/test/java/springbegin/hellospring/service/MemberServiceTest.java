package springbegin.hellospring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springbegin.hellospring.domain.Member;
import springbegin.hellospring.repository.MemberRepository;
import springbegin.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService meeMemberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        meeMemberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void  afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        // givne
        Member member = new Member();
        member.setName("1111");

        // when
        Long saveId = meeMemberService.join(member);
        // then
        Member findMember =  meeMemberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void validateMemberId() {
        // given
        Member member1 = new Member();
        member1.setName("1111");
        Member member2 = new Member();
        member2.setName("1111");

        // when
        meeMemberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> meeMemberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }

    @Test
    void findOne() {
    }
}