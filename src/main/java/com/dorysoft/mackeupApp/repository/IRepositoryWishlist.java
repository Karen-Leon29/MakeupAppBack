package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IRepositoryWishlist extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUser_Id(Long userId);
}
