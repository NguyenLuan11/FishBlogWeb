package com.tnluan.fishblogweb.controller.user;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.exception.ResourceUnprocessableEntityException;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
import com.tnluan.fishblogweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
public class UserViewController {

    private UserService userService;

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

    // HOME PAGE
    @GetMapping("/")
    public String homePage(Model model) {
        try {
            List<KindFishDto> kindFishDtoList = kindFishService.getAllKindFish();
            System.out.println("✅ KindFish List Size: " + kindFishDtoList.size());
            // Sort by createdDate decrease
            kindFishDtoList.sort(Comparator.comparing(KindFishDto::getCreatedDate).reversed());
            // Get 4 items first of list
            int size = kindFishDtoList.size();
            int toIndex = Math.min(4, size);
            List<KindFishDto> newestFourItems = kindFishDtoList.subList(0, toIndex);

            model.addAttribute("listKindFish", newestFourItems);
        } catch (Exception e) {
            System.err.println("❌ [ERROR]: " + e.getMessage());
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "user/homePage";
    }

    // CONTACT PAGE
    @GetMapping("/contact")
    public String contractPage() {
        return "user/contactPage";
    }

    // INTRODUCE PAGE
    @GetMapping("/introduce")
    public String introducePage() {
        return "user/introducePage";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("userLogin", new UserDto());
        return "user/loginUser";
    }

    // LOGIN ACTIONS
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userLogin") UserDto userLogin,
                            HttpSession session,
                            Model model) {
        try {
            UserDto loginUser = userService.loginAccountUser(userLogin.getUserName(), userLogin.getPassword());

            session.setAttribute("user", loginUser);
            return "redirect:/";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "user/loginUser";
        }
    }

    // LOGOUT ACTIONS
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // REGISTRATION PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userRegister", new UserDto());
        return "user/registerUser";
    }

    // REGISTER ACTIONS
    @PostMapping("/register")
    public String registrationUser(@ModelAttribute("userRegister") UserDto userRegister,
                                    HttpSession session,
                                    Model model) {
        try {
            UserDto registrationUser = userService.createUser(userRegister);

            session.setAttribute("user", registrationUser);
            return "redirect:/";
        } catch (ResourceUnprocessableEntityException e) {
            model.addAttribute("message", e.getMessage());
            return "user/registerUser";
        }
    }

    // USER'S PROFILE PAGE
    @GetMapping("/profile")
    public String profileUserPage(Model model, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/profileUser";
    }

    // UPDATE USER'S INFORMATION ACTIONS


    // DETAILS KIND FISH AND ALL BLOGS OF KIND FISH PAGE
    @GetMapping("/details-kindFish/{id}")
    public String detailsKindFishAndListBlog(@PathVariable("id") Long id,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "8") int size,
                                             Model model) {
        try {
            KindFishDto kindFishDto = kindFishService.getKindFishById(id);
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
            Page<FishBlogDto> fishBlogDtoPage = fishBlogService.getAllFishBlogByKindFishId(id, pageable);
            System.out.println("✅ FishBlog List's Size by kindFishId " + id + ": " + fishBlogDtoPage.getContent().size());

            model.addAttribute("kindFish", kindFishDto);
            model.addAttribute("listFishBlog", fishBlogDtoPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", fishBlogDtoPage.getTotalPages());
            model.addAttribute("kindFishId", id);

        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "user/kindFishViewAndListBlog";
    }

    // ALL KIND FISHES PAGE
    @GetMapping("/kind-fishes")
    public String listKindFishView(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "8") int size,
                                   Model model) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
            Page<KindFishDto> kindFishDtoPage = kindFishService.getKindFishPage(pageable);
            List<KindFishDto> allKindFish = kindFishService.getAllKindFish();

            model.addAttribute("listKindFishPage", kindFishDtoPage.getContent());
            model.addAttribute("listKindFish", allKindFish);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", kindFishDtoPage.getTotalPages());
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "user/allKindFishView";
    }
}
