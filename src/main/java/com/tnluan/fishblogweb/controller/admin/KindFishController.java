package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
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
public class KindFishController {

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

    private UploadService uploadService;

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

    @GetMapping({"/create-kindFish", "/update-kindFish/{id}"})
    public String showKindFishForm(@PathVariable(value = "id", required = false) Long id,
                                   Model model) {
        KindFishDto kindFishDto;
        if (id != null) {
            kindFishDto = kindFishService.getKindFishById(id);
        } else {
            kindFishDto = new KindFishDto();
        }
        model.addAttribute("kindFish", kindFishDto);
        return "admin/kindFish/kindFishComponent";
    }

    @PostMapping("/save-kindFish")
    public String createNewKindFish(@ModelAttribute KindFishDto kindFishDto,
                                    @RequestParam("image") MultipartFile imageFile) {

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = uploadService.UploadImage(imageFile, Constant.uploadImageKindFishDir);
            kindFishDto.setImageUrl(imageUrl);
        } else {
            KindFishDto existingKindFish = kindFishService.getKindFishById(kindFishDto.getId());
            if (existingKindFish != null) {
                kindFishDto.setImageUrl(existingKindFish.getImageUrl());
            }
        }

        if (kindFishDto.getId() == null) {
            kindFishService.createKindFish(kindFishDto);
        } else {
            kindFishService.updateKindFishById(kindFishDto.getId(), kindFishDto);
        }

        return "redirect:/admin/kindFish-management";
    }

}
