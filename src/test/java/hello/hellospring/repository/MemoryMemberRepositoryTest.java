package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메서드 실행이 끝날때마다 실행.
    // Test가 끝나면 clear. 콜백함수
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test //import
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        //내가 넣은게 제대로 들어갔나 보기
        repository.findById(member.getId()).get();
        //반환 타입이 optional임. 값 꺼내려면 get으로 꺼냄(물론 get으로 바로꺼내는건 좋은건 아님)
        Member result = repository.findById(member.getId()).get();

        //검증!
        //실무에서는 빌드 툴이랑 엮어서 함
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {

        //Spring1 , Spring2 라는 회원이 가입됨
        Member member1 = new Member();
        member1.setName(("Spring1"));
        repository.save(member1);

        Member member2 = new Member();
        member2.setName(("Spring2"));
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
