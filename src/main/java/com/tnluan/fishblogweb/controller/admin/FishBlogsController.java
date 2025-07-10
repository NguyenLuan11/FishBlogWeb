package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.FishBlogDto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class FishBlogsController {

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

    private UploadService uploadService;

    // FISH BLOGS MANAGEMENT PAGE
    @GetMapping("/fishBlogs-management")
    public String listFishBlogsView(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "12") int size,
                                    Model model) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
            Page<FishBlogDto> fishBlogDtoPage = fishBlogService.getAllFishBlogPage(pageable);
            List<FishBlogDto> allFishBlogs = fishBlogService.getAllFishBlog();

            model.addAttribute("listFishBlogsPage", fishBlogDtoPage.getContent());
            model.addAttribute("listFishBlogs", allFishBlogs);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", fishBlogDtoPage.getTotalPages());
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }

        return "admin/fishBlog/listFishBlogManager";
    }

    // DETAILS FISH BLOGS
    @GetMapping("/details-fishBlog/{id}")
    public String detailsFishBlogView(@PathVariable("id") Long id, Model model) {
        try {
            FishBlogDto fishBlogDto = fishBlogService.getFishBlogById(id);
            KindFishDto kindFishDto = kindFishService.getKindFishById(fishBlogDto.getKindFishId());
            model.addAttribute("fishBlog", fishBlogDto);
            model.addAttribute("kindFishName", kindFishDto.getKindFishName());
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/fishBlog/detailsFishBlog";
    }

    // UPLOAD FISH BLOG IMAGE
    @PostMapping("/upload-fishBlog-image")
    @ResponseBody
    public Map<String, String> uploadFishBlogImage(@RequestParam("image") MultipartFile image) {
        Map<String, String> result = new HashMap<>();
        try {
            String imageUrl = uploadService.UploadImage(image, Constant.uploadImageFishBlogDir);
            result.put("location", imageUrl); // TinyMCE expects this key
            return result;
        } catch (Exception e) {
            result.put("error", "Upload failed: " + e.getMessage());
            return result;
        }
    }

    // CREATE - UPDATE FISH BLOGS PAGE
    @GetMapping({"/create-fishBlog", "/update-fishBlog/{id}"})
    public String showFishBlogForm(@PathVariable(value = "id", required = false) Long id,
                                   Model model) {
        try {
            FishBlogDto fishBlogDto;
            if (id != null) {
                fishBlogDto = fishBlogService.getFishBlogById(id);
            } else {
                fishBlogDto = new FishBlogDto();
            }

            List<KindFishDto> listKindFish = kindFishService.getAllKindFish();

            model.addAttribute("fishBlog", fishBlogDto);
            model.addAttribute("listKindFish", listKindFish);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/fishBlog/fishBlogComponent";
    }

    // SAVE FISH BLOGS
    @PostMapping("/save-fishBlog")
    public String saveFishBlog(@ModelAttribute FishBlogDto fishBlogDto,
                               @RequestParam("image") MultipartFile imageFile,
                               RedirectAttributes redirectAttributes) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = uploadService.UploadImage(imageFile, Constant.uploadThumbnailDir);
                fishBlogDto.setThumbnailUrl(imageUrl);
            } else {
                FishBlogDto existingFishBlog = fishBlogService.getFishBlogById(fishBlogDto.getId());
                if (existingFishBlog != null) {
                    fishBlogDto.setThumbnailUrl(existingFishBlog.getThumbnailUrl());
                }
            }
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException("Upload Image Error: " + e.getMessage());
        }

        try {
            if (fishBlogDto.getId() == null) {
                FishBlogDto createdFishBlog = fishBlogService.createFishBlog(fishBlogDto);

                redirectAttributes.addFlashAttribute("message",
                        "Thêm thông tin blog " + createdFishBlog.getFishName() + " thành công!");
                redirectAttributes.addFlashAttribute("typeMessage", "success");
            } else {
                FishBlogDto updatedFishBlog = fishBlogService.updateFishBlogById(fishBlogDto.getId(), fishBlogDto);

                redirectAttributes.addFlashAttribute("message",
                        "Cập nhật thông tin blog " + updatedFishBlog.getFishName() + " thành công!");
                redirectAttributes.addFlashAttribute("typeMessage", "warning");
            }
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }

        return "redirect:/admin/fishBlogs-management";
    }

    // DELETE FISH BLOGS
    @DeleteMapping("/delete-fishBlog/{id}")
    public String deleteFishBlog(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            fishBlogService.deleteFishBlogById(id);

            redirectAttributes.addFlashAttribute("message",
                    "Xóa thông tin blog cá cảnh có id là " + id.toString() + " thành công!");
            redirectAttributes.addFlashAttribute("typeMessage", "danger");
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "redirect:/admin/fishBlogs-management";
    }

}
