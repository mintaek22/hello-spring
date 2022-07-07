package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 8L;

    @Override
    public Member save(Member member) {
        // 새로운 값은 id를 +1 해서 세팅 해준다
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    //id를 통해서 찾는다
    @Override
    public Optional<Member> findById(Long id) {
        // Null 값이면 Optional로 감싸서 반환 시켜준다
        return Optional.ofNullable(store.get(id));
    }

    //name(member 안에 변수)을 통해서 찾는다
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
