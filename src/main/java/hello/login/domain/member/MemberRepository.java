package hello.login.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	/**
	 * 저장
	 */
	public Member save(Member member) {
		member.setId(++sequence);
		log.info("save: memeber={}", member);
		store.put(member.getId(), member);
		return member;
	}
	
	/**
	 * 조회
	 */
	public Member findById(Long id) {
		return store.get(id);
	}
	
	/**
	 * 로그인 아이디 조회
	 */
	public Optional<Member> findByLoginId(String loginId){
		List<Member> listMember = findAll();
		return listMember.stream()
				.filter(m -> m.getLoginId().equals(loginId))
				.findAny();
	}
	
	/**
	 * 전체조회
	 */
	public List<Member> findAll(){
		return new ArrayList<>(store.values());
	}
	
	/**
	 * 초기화
	 */
	public void clearStore() {
		store.clear();
	}
	
}
