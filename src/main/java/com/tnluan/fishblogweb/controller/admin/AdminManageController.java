package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
import com.tnluan.fishblogweb.service.UserService;
import com.tnluan.fishblogweb.util.Constant;
import com.tnluan.fishblogweb.util.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminManageController {

    private UserService userService;

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

    private UploadService uploadService;

    // ADMIN MANAGEMENT
    @GetMapping("/login-admin")
    public String loginPage(Model model) {
        model.addAttribute("admin", new UserDto());
        return "admin/loginAdmin";
    }

    @PostMapping("/login-admin")
    public String loginAdmin(@ModelAttribute UserDto userDto) {
        return "admin/adminHomePage";
    }

    // KIND FISH MANAGEMENT
    @GetMapping("/kindFish-management")
    public String listKindFishView(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "12") int size,
                                   Model model) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
            Page<KindFishDto> kindFishDtoPage = kindFishService.getKindFishPage(pageable);

            model.addAttribute("listKindFish", kindFishDtoPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", kindFishDtoPage.getTotalPages());
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/kindFish/listKindFishManager";
    }

    @GetMapping("/create-kindFish")
    public String createNewKindFishView(Model model) {
        model.addAttribute("kindFish", new KindFishDto());
        return "admin/kindFish/kindFishComponent";
    }

    @PostMapping("/create-kindFish")
    public String createNewKindFish(@ModelAttribute KindFishDto kindFishDto,
                                    @RequestParam("image") MultipartFile imageFile) {
//        System.out.println("Received kind fish name: " + kindFishDto.getKindFishName());
//        System.out.println("Description: " + kindFishDto.getDescription());
//        System.out.println("File name: " + imageFile.getOriginalFilename());

        String imageUrl = uploadService.UploadImage(imageFile, Constant.uploadImageKindFishDir);
        kindFishDto.setImageUrl(imageUrl);

        kindFishService.createKindFish(kindFishDto);
        return "admin/kindFish/listKindFishManager";
    }

    // FISH BLOG MANAGEMENT


    // USER MANAGEMENT



}
