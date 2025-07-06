package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
import com.tnluan.fishblogweb.util.Constant;
import com.tnluan.fishblogweb.util.UploadService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class KindFishController {

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

    private UploadService uploadService;

    // KIND FISH MANAGEMENT PAGE
    @GetMapping("/kindFish-management")
    public String listKindFishView(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "12") int size,
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
        return "admin/kindFish/listKindFishManager";
    }

    // DETAILS KIND FISH PAGE
    @GetMapping("/details-kindFish/{id}")
    public String detailsKindFish(@PathVariable("id") Long id, Model model) {
        try {
            KindFishDto kindFishDto = kindFishService.getKindFishById(id);

            model.addAttribute("kindFish", kindFishDto);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/kindFish/detailsKindFish";
    }


    // CREATE - UPDATE KIND FISH PAGE
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

    // SAVE KIND FISH ACTIONS
    @PostMapping("/save-kindFish")
    public String createNewKindFish(@ModelAttribute KindFishDto kindFishDto,
                                    @RequestParam("image") MultipartFile imageFile,
                                    RedirectAttributes redirectAttributes) {

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
            KindFishDto createdKindFish = kindFishService.createKindFish(kindFishDto);

            redirectAttributes.addFlashAttribute("message",
                    "Thêm thông tin " + createdKindFish.getKindFishName() + " thành công!");
            redirectAttributes.addFlashAttribute("typeMessage", "success");
        } else {
            KindFishDto updatedKindFish = kindFishService.updateKindFishById(kindFishDto.getId(), kindFishDto);

            redirectAttributes.addFlashAttribute("message",
                    "Cập nhật thông tin " + updatedKindFish.getKindFishName() + " thành công!");
            redirectAttributes.addFlashAttribute("typeMessage", "warning");
        }

        return "redirect:/admin/kindFish-management";
    }

    // DELETE KIND FISH
    @DeleteMapping("/delete-kindFish/{id}")
    public String deleteKindFish(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        kindFishService.deleteKindFishById(id);

        redirectAttributes.addFlashAttribute("message",
                "Xóa thông tin loại cá cảnh có id là " + id.toString() + " thành công!");
        redirectAttributes.addFlashAttribute("typeMessage", "danger");
        return "redirect:/admin/kindFish-management";
    }
}
