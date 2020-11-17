/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.controllers;

import com.ksm.tts.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kelvi
 */
@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    
    @GetMapping("/read/")
    public String getAllNews(){
        return "news/allnews";
    }
    
    @GetMapping("/read/single")
    public String performGetSingle(){
        return "news/readsingle";
    }
}
