package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class UserController {

    private UserService userService;

    // USER MANAGEMENT PAGE
    @GetMapping("/users-management")
    public String listUsersView(Model model) {
        try {
            List<UserDto> allUsers = userService.getAllUser();
            List<UserDto> listUsers = allUsers.stream()
                    .filter(user -> "USER".equals(user.getRole())).toList();
            model.addAttribute("listUsers", listUsers);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/user/listUserManager";
    }

    // DETAILS USER PAGE
    @GetMapping("/details-user/{id}")
    public String detailsUser(@PathVariable("id") Long id, Model model) {
        try {
            UserDto userDto = userService.getUserById(id);
            model.addAttribute("user", userDto);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/user/detailsUser";
    }

    // DELETE USER ACTIONS
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("message",
                "Xóa thông tin tài khoản người dùng có id là " + id.toString() + " thành công!");
        redirectAttributes.addFlashAttribute("typeMessage", "danger");
        return "redirect:/admin/users-management";
    }

}
