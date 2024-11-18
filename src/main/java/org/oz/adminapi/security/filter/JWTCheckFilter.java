package org.oz.adminapi.security.filter;

import com.google.gson.Gson;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.adminlogin.exception.AdminLoginException;
import org.oz.adminapi.security.auth.AdminLoginPrincipal;
import org.oz.adminapi.security.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class JWTCheckFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        log.info("----Should NOT Filter----");

        String uri = request.getRequestURI();

        if(uri.equals("/api/adminlogin/makeToken")){
            return true;
        }
        if(uri.equals("/api/adminlogin/refreshToken")){
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("doFilterInternal");

        log.info(request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        String token = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
        }else {
            makeError(response, Map.of("status",401, "msg","No Access Token") );
            return;
        }

        //JWT validate
        try{

            Map<String, Object> claims = jwtUtil.validateToken(token);
            log.info(claims);

            String adminId = (String) claims.get("adminId");

            Principal userPrincipal = new AdminLoginPrincipal(adminId);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userPrincipal, null,
                    List.of());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        }catch(JwtException e){
            AdminLoginException exception = AdminLoginException.ACCESSTOKEN_EXPIRED;
            String errorMessage = exception.getMessage();
            int statusCode = exception.getStatus();
            makeError(response, Map.of("status", statusCode, "msg", errorMessage));

            e.printStackTrace();
        }
    }

    private void makeError(HttpServletResponse response, Map<String, Object> map) {

        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);

        response.setContentType("application/json");
        response.setStatus((int)map.get("status"));
        try{
            PrintWriter out = response.getWriter();
            out.println(jsonStr);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
