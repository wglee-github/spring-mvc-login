package hello.login.web.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		log.info("supportsParamter 실행");
		// Login 어노테이션이 파라미터에 선언되어 있는지 확인
		boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
		
		// 파라미터 타입이 멤버 타입인지 확인
		boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
		
		// return 이 true 이면 resolveArgument가 실행된다.
		return hasLoginAnnotation && hasMemberType;
	}

	/**
	 * 	컨트롤러 호출 직전에 호출 되어서 필요한 파라미터 정보를 생성해준다. 여기서는 
		세션에 있는 로그인 회원 정보인 member 객체를 찾아서 반환해준다. 이후 스프링MVC는 컨트롤러의 
		메서드를 호출하면서 여기에서 반환된 member 객체를 파라미터에 전달해준다.
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		log.info("resolveArgument 실행");
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return null;
		}
		
		return session.getAttribute(SessionConst.LOGIN_MEMBER);
	}

}
