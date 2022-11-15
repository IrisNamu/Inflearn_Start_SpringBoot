package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/*
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository {
    //저장을 하는 곳이 필요.Map 사용, 키는 회원의 아이디이므로 Long, 값은 Member
    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제가 있을 수 있자만 예제니까
    private static long sequence = 0L; //실무에서는 동시성 문제 고려해서 어쿼롱이라씀


    @Override

    public Member save(Member member) {
        member.setId(++sequence);//아이디 세팅하고
        store.put(member.getId(), member); //store에 저장. Map에 저장됨

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store에서 꺼낸다.

        //근데 값이 없으면 null이 나옴. 그렇기 때문에 Optional.ofNullable() 사용
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //member.getName()과 파라미터 name과 같은지 비교
        //같으면 필터링! 찾으면 반환해준다!!
        // 결과가 없다면? Optional에 null이 포함돼서 반환
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny();//Optional로 결과 찾음
    }

    @Override
    public List<Member> findAll() {
        //Map인데 반환은 List로 되어있음
        //실무에서는 List를 많이 사용
        return new ArrayList<>(store.values()); //member들을 반환해줌
    }

    //Test가 끝나면 sotre을 clear 해줌
    public void clearStore(){
        store.clear();
    }
}
