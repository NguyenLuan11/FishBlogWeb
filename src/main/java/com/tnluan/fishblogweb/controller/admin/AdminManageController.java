package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.service.UserService;
import com.tnluan.fishblogweb.util.Constant;
import com.tnluan.fishblogweb.util.UploadService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminManageController {

    private UserService userService;

    private UploadService uploadService;

    // HOME PAGE
    @GetMapping("/home")
    public String adminHome() {
        return "admin/adminHomePage";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("adminLogin", new UserDto());
        return "admin/loginAdmin";
    }

    // LOGIN ACTIONS
    @PostMapping("/login")
    public String LoginAdmin(@ModelAttribute("adminLogin") UserDto adminLogin,
                                   HttpSession session,
                                   Model model) {
        try {
            UserDto loginAdmin = userService.loginAccountUser(adminLogin.getUserName(), adminLogin.getPassword());

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

    // PROFILE ADMIN PAGE
    @GetMapping("/profile")
    public String profileAdminPage(Model model, HttpSession session) {
        UserDto admin = (UserDto) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "admin/profileAdmin";
    }

    // UPDATE INFORMATION OF ADMIN ACTIONS
    @PutMapping("/profile/update")
    public String updateProfileAdmin(@ModelAttribute UserDto admin,
                                     @RequestParam("imageAvt") MultipartFile imageAvtFile,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        // Update Image Avatar
        if (imageAvtFile != null && !imageAvtFile.isEmpty()) {
            String imageAvtUrl = uploadService.UploadImage(imageAvtFile, Constant.uploadAvtDir);
            admin.setAvatarUrl(imageAvtUrl);
        } else {
            UserDto existingAdmin = userService.getUserById(admin.getId());
            if (existingAdmin != null) {
                admin.setAvatarUrl(existingAdmin.getAvatarUrl());
            }
        }

        // Update Admin
        UserDto updatedAdmin = userService.updateUserById(admin.getId(), admin);
        if (updatedAdmin != null) {
            redirectAttributes.addFlashAttribute("message",
                    "Cập nhật thông tin quản trị viên " + updatedAdmin.getUserName() +" thành công!");
            redirectAttributes.addFlashAttribute("typeMessage", "warning");
        }

        // Update session
        session.setAttribute("admin", updatedAdmin);
        return "redirect:/admin/profile";
    }

    // UPDATE PASSWORD



}
