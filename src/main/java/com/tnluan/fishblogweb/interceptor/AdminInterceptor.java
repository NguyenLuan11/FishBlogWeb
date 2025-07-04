package com.tnluan.fishblogweb.interceptor;

import com.tnluan.fishblogweb.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * AdminInterceptor is used to restrict access to admin-only routes.
 * It checks whether the current user is logged in and has the "ADMIN" role.
 * If not, the request will be redirected to the admin login page.
 *
 * This interceptor is registered to apply to all routes under /admin/**
 * except for explicitly excluded paths (e.g., /admin/login).
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

    /**
     * Intercepts incoming HTTP requests before they reach the controller.
     * Checks if the user in session has ADMIN privileges.
     *
     * @param request  the current HTTP request
     * @param response the current HTTP response
     * @param handler  the chosen handler to execute
     * @return true if the user is authorized, false otherwise
     * @throws Exception if any error occurs
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null) {
            UserDto user = (UserDto) session.getAttribute("admin");

            if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
                return true; // ✅ Authorized: proceed to controller
            }
        }

        // ❌ Unauthorized: redirect to the admin login page
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }
}
