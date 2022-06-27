package ru.ikolesnikov.testtask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ikolesnikov.testtask.model.Banner;
import ru.ikolesnikov.testtask.services.BannerService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
@RequestMapping("/banner")
@AllArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<Banner>> getBanners(@RequestParam(required = false) String name) {
        if (name != null)
            return ResponseEntity.status(OK)
                    .body(bannerService.getBannersByName(name));

        return ResponseEntity.status(OK)
                .body(bannerService.getAllBanners());
    }

    @GetMapping("/{bannerId}")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long bannerId) {
        return ResponseEntity.status(OK)
                .body(bannerService.getBannerById(bannerId));
    }

    @PostMapping("/addBanner")
    public ResponseEntity<Banner> createBanner(@Valid @RequestBody Banner banner) {
        return ResponseEntity.status(OK)
                .body(bannerService.createBanner(banner));
    }

    @PutMapping("/{bannerId}")
    public ResponseEntity<Banner> updateBanner(@Valid @RequestBody Banner banner) {
        return ResponseEntity.status(OK).body(bannerService.updateBanner(banner));
    }

    @DeleteMapping("/{bannerId}")
    public ResponseEntity<Banner> deleteBanner(@PathVariable Long bannerId) {
        bannerService.deleteBanner(bannerId);
        return ResponseEntity.status(OK).build();
    }
}