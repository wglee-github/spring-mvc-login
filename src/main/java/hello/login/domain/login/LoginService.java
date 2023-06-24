package hello.login.domain.login;

import org.springframework.stereotype.Service;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;
	
	/**
	 * 
	 * @return null 로그인 실패
	 */
	public Member login(String loginId, String password) {
		
//		Optional<Member> findMemberOpional = memberRepository.findByLoginId(loginId);
//		Member findMember = findMemberOpional.get();
//		if(findMember.getPassword().equals(password)) {
//			return findMember;
//		}else {
//			return null;
//		}
		
		return memberRepository.findByLoginId(loginId)
				.filter(m -> m.getPassword().equals(password))
				.orElse(null);
	}

}
