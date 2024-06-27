package com.moving.moving_app.crawler.controller;

import com.moving.moving_app.crawler.domain.CgvVO;
import com.moving.moving_app.crawler.repository.CgvDAO;
import com.moving.moving_app.crawler.service.CrawlingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CrawlingController {
    private final CrawlingService crawlingService;
    private final CgvDAO cgvDAO;

    public CrawlingController(CrawlingService crawlingService, CgvDAO cgvDAO) {
        this.crawlingService = crawlingService;
        this.cgvDAO = cgvDAO;
    }

    @GetMapping("/movies")
    public String crawlData(Model model) {
        crawlingService.sampleCrawling();
        List<CgvVO> movies = cgvDAO.findAll();
        model.addAttribute("movies", movies);
        return "movies";
    }
}