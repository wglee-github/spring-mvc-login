package hello.login.web.session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SessionManager {

	private static String SESSION_COOKIE_NAME = "mySessionId";
	private static Map<String, Object> sessionStore = new HashMap<>();
	
	/**
	 * 세션 저장 
	 */
	public void createSession(Object value, HttpServletResponse response) {
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, value);
		
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		response.addCookie(cookie);
	}
	
	/**
	 * 세션 조회
	 */
	public Object getSession(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		
		if(sessionCookie == null)
			return null;
		
		return sessionStore.get(sessionCookie.getValue());
	}
	
	/**
	 * 세션 만료 
	 */
	public void expire(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		
		if(sessionCookie != null) {
			sessionStore.remove(sessionCookie.getValue());
		}
	}
	
	
	public Cookie findCookie(HttpServletRequest request, String cookieName) {
		if(request.getCookies() == null) {
			return null;
		}
		
		return Arrays.stream(request.getCookies())
				.filter(cookie -> cookie.getName().equals(cookieName))
				.findAny()
				.orElse(null);
	}
}
