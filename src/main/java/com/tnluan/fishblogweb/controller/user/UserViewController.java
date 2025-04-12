package com.tnluan.fishblogweb.controller.user;

import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.exception.ResourceInternalServerErrorException;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.service.KindFishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
public class UserViewController {

    private KindFishService kindFishService;

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
            System.err.println("❌ [ERROR] Error when called kindFishService.getAllKindFish(): " + e.getMessage());
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
    public String detailsKindFishAndListBlog(@PathVariable("id") Long id, Model model) {
        KindFishDto kindFishDto = kindFishService.getKindFishById(id);
        model.addAttribute("kindFish", kindFishDto);
        return "user/kindFishViewAndListBlog";
    }

    @GetMapping("/kind-fishes")
    public String listKindFishView(Model model) {
        try {
            List<KindFishDto> kindFishDtoList = kindFishService.getAllKindFish();
            // Sort by createdDate decrease
            kindFishDtoList.sort(Comparator.comparing(KindFishDto::getCreatedDate).reversed());
            model.addAttribute("listKindFish", kindFishDtoList);
        } catch (Exception e) {
            throw new ResourceInternalServerErrorException(e.getMessage());
        }
        return "user/listKindFishView";
    }
}
