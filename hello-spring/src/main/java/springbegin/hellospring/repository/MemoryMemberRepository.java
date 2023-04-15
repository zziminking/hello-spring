package springbegin.hellospring.repository;

import springbegin.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> storeMap = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        storeMap.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(storeMap.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return storeMap.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(storeMap.values());
    }

    public void clearStore () {
        storeMap.clear();
    }

}
