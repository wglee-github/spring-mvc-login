package hello.login.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired private MemberRepository memberRepository;
	
	@BeforeEach
	void beforeEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void updateMember() {
		Member member = new Member();
		member.setLoginId("admin");
		member.setName("관리자");
		member.setPassword("0000");
		
		Member saveMember = memberRepository.save(member);
		
		assertThat(saveMember.getName()).isEqualTo("관리자");
		assertThat(saveMember.getPassword()).isEqualTo("0000");
	}

}
