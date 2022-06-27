package ru.ikolesnikov.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikolesnikov.testtask.model.Banner;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    Optional<Banner> findByName(String name);

    Optional<Banner> findByBannerIdAndDeleted(Long id, Boolean deleted);

    List<Banner> findAllByDeleted(Boolean deleted);

    List<Banner> findAllByCategory_CategoryIdAndDeleted(Long categoryId, Boolean deleted);

    Optional<Banner> findFirstByBannerIdNotInAndCategory_RequestAndDeletedOrderByPriceDesc(
            Collection<Long> bannerIdStopList, String request, Boolean deleted);

    List<Banner> findByNameContainsIgnoreCase(String name);

}