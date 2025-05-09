package com.tnluan.fishblogweb.controller.user;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.service.FishBlogService;
import com.tnluan.fishblogweb.service.KindFishService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
public class UserViewController {

    private KindFishService kindFishService;

    private FishBlogService fishBlogService;

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

    @GetMapping("/contact")
    public String contractPage() {
        return "user/contactPage";
    }

    @GetMapping("/introduce")
    public String introducePage() {
        return "user/introducePage";
    }

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

    @GetMapping("/kind-fishes")
    public String listKindFishView(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "8") int size,
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
        return "user/allKindFishView";
    }
}
