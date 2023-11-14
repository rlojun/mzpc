package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}
