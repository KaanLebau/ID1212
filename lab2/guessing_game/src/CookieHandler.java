import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CookieHandler {
    public CookieHandler(){
        
    }

        // Method to parse cookies from the request header
    public Map<String, String> parseCookies(String cookieHeader) {
        Map<String, String> cookies = new HashMap<>();
        if (cookieHeader != null && !cookieHeader.isEmpty()) {
            Arrays.stream(cookieHeader.split(";"))
                    .map(cookie -> cookie.split("=", 2))
                    .forEach(parts -> cookies.put(parts[0].trim(), parts.length > 1 ? parts[1].trim() : ""));
        }
        return cookies;
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    public String createCookieHeader(String name, String value, int maxAgeSeconds, boolean httpOnly, boolean secure) {
        // Building the cookie string
        StringBuilder cookieBuilder = new StringBuilder();
        cookieBuilder.append(name).append("=").append(value);
        cookieBuilder.append("; Max-Age=").append(maxAgeSeconds);
        cookieBuilder.append("; Path=/");
        if (httpOnly) {
            cookieBuilder.append("; HttpOnly");
        }
        if (secure) {
            cookieBuilder.append("; Secure");
        }
        return cookieBuilder.toString();
    }

    public String getCookieValue(Map<String, String> cookies, String name) {
        return cookies.getOrDefault(name, "");
    }

    public String createSessionCookie(String sessionId, int maxAgeSeconds) {
        return createCookieHeader("sessionID", sessionId, maxAgeSeconds, true, true);
    }
}
