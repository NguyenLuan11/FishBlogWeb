package com.tnluan.fishblogweb.controller.admin;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
import com.tnluan.fishblogweb.util.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
            model.addAttribute("fishBlog", fishBlogDto);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/fishBlog/detailsFishBlog";
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
            model.addAttribute("fishBlog", fishBlogDto);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "admin/fishBlog/fishBlogComponent";
    }

    // SAVE FISH BLOGS
    @PostMapping("/save-fishBlog")
    public String saveFishBlog(@ModelAttribute FishBlogDto fishBlogDto,
                               RedirectAttributes redirectAttributes) {

        try {
            if (fishBlogDto.getId() == null) {
                FishBlogDto createdFishBlog = fishBlogService.createFishBlog(fishBlogDto);

                redirectAttributes.addFlashAttribute("message",
                        "Thêm thông tin " + createdFishBlog.getFishName() + " thành công!");
                redirectAttributes.addFlashAttribute("typeMessage", "success");
            } else {
                FishBlogDto updatedFishBlog = fishBlogService.updateFishBlogById(fishBlogDto.getId(), fishBlogDto);

                redirectAttributes.addFlashAttribute("message",
                        "Cập nhật thông tin " + updatedFishBlog.getFishName() + " thành công!");
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
