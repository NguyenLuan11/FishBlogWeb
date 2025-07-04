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

import java.util.List;

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
            model.addAttribute("listUsers", allUsers);
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
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users-management";
    }

}
