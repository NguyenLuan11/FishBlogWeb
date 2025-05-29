package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.service.UserService;
import com.tnluan.fishblogweb.util.UploadService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminManageController {

    private UserService userService;

    private UploadService uploadService;

    // HOME PAGE
    @GetMapping("/home")
    public String adminHome(Model model, HttpSession session) {
        UserDto admin = (UserDto) session.getAttribute("admin");
        if (admin == null || !"ADMIN".equalsIgnoreCase(admin.getRole())) {
            return "redirect:/admin/login";
        }

        model.addAttribute("admin", admin);
        return "admin/adminHomePage";
    }

    // LOGIN ACTIONS
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("adminLogin", new UserDto());
        return "admin/loginAdmin";
    }

    @PostMapping("/login")
    public String LoginAdmin(@ModelAttribute("adminLogin") UserDto adminLogin,
                                   HttpSession session,
                                   Model model) {
        try {
//            System.out.println("Admin name: " + adminLogin.getUserName());
//            System.out.println("Admin pass: " + adminLogin.getPassword());

            UserDto loginAdmin = userService.loginAccountUser(adminLogin.getUserName(), adminLogin.getPassword());

//            System.out.println("==> loginAdmin: " + loginAdmin);
//            System.out.println("==> loginAdmin.getRole(): " + loginAdmin.getRole());

            if (!"ADMIN".equalsIgnoreCase(loginAdmin.getRole())) {
                model.addAttribute("message", "You are not authorized to access the admin panel!");
                return "admin/loginAdmin";
            }

            session.setAttribute("admin", loginAdmin);
            return "redirect:/admin/home";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "admin/loginAdmin";
        }
    }

    // LOGOUT ACTIONS
    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
