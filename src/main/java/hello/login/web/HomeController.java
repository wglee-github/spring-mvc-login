package hello.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final MemberRepository memberRepository;
	private final SessionManager sessionManager;
	
//    @GetMapping("/")
    public String home() {
        return "home";
    }
    
//    @GetMapping("/")
    public String loginHome(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
    	
    	if(memberId == null) {
    		return "home";
    	}
    		
    	Member loginMember = memberRepository.findById(memberId);
    	
    	if(loginMember == null) {
    		return "home";
    	}
    	
    	model.addAttribute("member", loginMember);
    	return "loginHome";
    }
    
//    @GetMapping("/")
    public String loginHomeV2(HttpServletRequest request, Model model) {
    	
    	//세션 관리자에 저장된 회원 정보 조회
    	Member member = (Member)sessionManager.getSession(request);
    	
    	if(member == null) {
    		return "home";
    	}
    	
    	model.addAttribute("member", member);
    	return "loginHome";
    }
    
//    @GetMapping("/")
    public String loginHomeV3(HttpServletRequest request, Model model) {
    	
    	//세션 관리자에 저장된 회원 정보 조회. 최초 접속 사용자의 경우 세션이 없기때문에 false.
    	HttpSession session = request.getSession(false);
    	
    	if(session == null) {
    		return "home";
    	}
    	
    	Member loginMember =  (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
    	
    	if(loginMember == null) {
    		return "home";
    	}
    	
    	model.addAttribute("member", loginMember);
    	return "loginHome";
    }
    
    @GetMapping("/")
    public String loginHomeV3Spring(
    		@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
    	
    	if(loginMember == null) {
    		return "home";
    	}
    	
    	model.addAttribute("member", loginMember);
    	return "loginHome";
    }

}