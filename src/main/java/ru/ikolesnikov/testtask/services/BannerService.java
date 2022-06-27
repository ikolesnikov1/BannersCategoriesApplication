package ru.ikolesnikov.testtask.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ikolesnikov.testtask.model.Banner;
import ru.ikolesnikov.testtask.model.Category;
import ru.ikolesnikov.testtask.repository.BannerRepository;
import ru.ikolesnikov.testtask.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BannerService  {

    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;

    public List<Banner> getAllBanners() {
        return bannerRepository.findAllByDeleted(false);
    }

    public List<Banner> getBannersByName(String name) {
        return bannerRepository.findByNameContainsIgnoreCase(name);
    }

    public Banner getBannerById(Long typeId) {
        return bannerRepository.findByBannerIdAndDeleted(typeId, false)
                .orElseThrow(() -> new RuntimeException("Can`t find banner by bannerId, maybe it deleted"));
    }

    public Banner createBanner(Banner banner) {
        if (checkForDuplicate(banner))
            return banner;

        Banner bannerToSave = new Banner();
        Category category = categoryRepository.findByName(banner.getCategory().getName()).get();
        bannerToSave.setCategory(category);
        bannerToSave.setName(banner.getName());
        bannerToSave.setPrice(banner.getPrice());
        bannerToSave.setContent(banner.getContent());
        bannerToSave.setDeleted(false);
        return bannerRepository.save(bannerToSave);
    }

    public boolean checkForDuplicate(Banner banner) {
        boolean duplicate = false;
        Optional<Banner> byName = bannerRepository.findByName(banner.getName());
        if (byName.isPresent() && byName.get().getBannerId() != banner.getBannerId()) {
            banner.setName(null);
            duplicate = true;
        }

        return duplicate;
    }

    public Banner updateBanner(Banner banner) {
        if (checkForDuplicate(banner))
            return banner;

        Banner bannerToSave = bannerRepository.findByBannerIdAndDeleted(banner.getBannerId(), false)
                .orElseThrow(() -> new RuntimeException("Can`t find banner by bannerId to update"));
        Category category = categoryRepository.findByName(banner.getCategory().getName()).get();
        bannerToSave.setCategory(category);
        bannerToSave.setName(banner.getName());
        bannerToSave.setPrice(banner.getPrice());
        bannerToSave.setContent(banner.getContent());
        return bannerRepository.save(bannerToSave);
    }

    public void deleteBanner(Long bannerId) {
        Banner bannerToDelete = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Can`t find banner by bannerId to delete"));
        bannerToDelete.setDeleted(true);
        bannerRepository.save(bannerToDelete);
    }
}
