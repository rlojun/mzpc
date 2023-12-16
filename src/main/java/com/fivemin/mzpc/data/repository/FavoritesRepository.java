package com.fivemin.mzpc.data.repository;

import com.fivemin.mzpc.data.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query("SELECT favorites FROM Favorites favorites WHERE favorites.members.idx = ?1 ")
    List<Favorites> findFavoritesByMemberIdx(Long memberIdx);

    Boolean existsByFoodIdxAndMembersIdx(Long foodIdx, Long memberIdx);

    @Modifying
    @Query("DELETE FROM Favorites f WHERE f.idx = :favoriteIdx")
    void removeByIdx(Long favoriteIdx);

}
