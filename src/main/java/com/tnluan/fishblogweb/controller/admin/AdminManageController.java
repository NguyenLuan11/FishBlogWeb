package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
import com.tnluan.fishblogweb.util.Constant;
import com.tnluan.fishblogweb.util.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminManageController {

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

    private UploadService uploadService;

    // KIND FISH MANAGEMENT
    @GetMapping("/create-kindFish")
    public String createNewKindFishView(Model model) {
        model.addAttribute("kindFish", new KindFishDto());
        return "admin/kindFish/kindFishComponent";
    }

    @PostMapping("/create-kindFish")
    public String createNewKindFish(@ModelAttribute KindFishDto kindFishDto,
                                    @RequestParam("image") MultipartFile imageFile) {
        String imageUrl = uploadService.UploadImage(imageFile, Constant.uploadImageKindFishDir);
        kindFishDto.setImageUrl(imageUrl);

        kindFishService.createKindFish(kindFishDto);
        return "admin/kindFish/listKindFishManager";
    }

    // FISH BLOG MANAGEMENT


    // USER MANAGEMENT



}
