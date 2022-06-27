package ru.ikolesnikov.testtask.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ikolesnikov.testtask.model.Banner;
import ru.ikolesnikov.testtask.model.Category;
import ru.ikolesnikov.testtask.repository.BannerRepository;
import ru.ikolesnikov.testtask.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BannerRepository bannerRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByDeleted(false);
    }

    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByNameContainsIgnoreCase(name);
    }

    public Category getCategoryById(Long typeId) {
        return categoryRepository.findByCategoryIdAndDeleted(typeId, false)
                .orElseThrow(() -> new RuntimeException("Can`t find category by categoryId, maybe it deleted"));
    }

    public Category createCategory(Category category) {
        if (checkForDuplicate(category))
            return category;

        Category categoryToSave = new Category();
        categoryToSave.setName(category.getName());
        categoryToSave.setRequest(category.getRequest());
        categoryToSave.setDeleted(false);
        return categoryRepository.save(categoryToSave);
    }

    public Category updateCategory(Category category) {
        if (checkForDuplicate(category))
            return category;

        Category categoryToUpdate = categoryRepository.findByCategoryIdAndDeleted(category.getCategoryId(), false)
                .orElseThrow(() -> new RuntimeException("Can`t find category by categoryId to update"));
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setRequest(category.getRequest());
        return categoryRepository.save(categoryToUpdate);
    }

    public String deleteCategory(Long categoryId) {
        String response = checkForUseInBanners(categoryId);
        if (!response.isEmpty())
            return response;

        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Can`t find category by categoryId to delete"));
        categoryToDelete.setDeleted(true);
        categoryRepository.save(categoryToDelete);
        return "";
    }

    public boolean checkForDuplicate(Category category) {
        boolean duplicate = false;
        Optional<Category> byName = categoryRepository.findByName(category.getName());
        if (byName.isPresent() && !Objects.equals(byName.get().getCategoryId(), category.getCategoryId())) {
            category.setName(null);
            duplicate = true;
        }

        Optional<Category> byRequest = categoryRepository.findByRequest(category.getRequest());
        if (byRequest.isPresent() && !Objects.equals(byRequest.get().getCategoryId(), category.getCategoryId())) {
            category.setRequest(null);
            duplicate = true;
        }
        return duplicate;
    }

    public String checkForUseInBanners(Long categoryId) {
        String result = "";
        List<Banner> banners = bannerRepository.findAllByCategory_CategoryIdAndDeleted(categoryId, false);
        for (Banner banner : banners)
            result += banner.getBannerId() + " " + banner.getName() + ". ";

        return result;
    }
}
