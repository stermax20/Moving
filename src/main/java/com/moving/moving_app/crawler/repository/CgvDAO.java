package com.moving.moving_app.crawler.repository;

import com.moving.moving_app.crawler.domain.CgvVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CgvDAO extends JpaRepository<CgvVO, Integer> {
    Optional<CgvVO> findByHash(String hash);
}
