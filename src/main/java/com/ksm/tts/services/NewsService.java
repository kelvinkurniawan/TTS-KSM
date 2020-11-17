/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.tts.services;

import com.ksm.tts.entities.News;
import com.ksm.tts.entities.NewsDetail;
import com.ksm.tts.entities.NewsDetailParent;
import com.ksm.tts.entities.NewsParent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service

public class NewsService {
    
    NewsParent newsParent;
    News news;
    NewsDetailParent newsDetailParent;
    NewsDetail newsDetail;
   
    @Autowired
    RestTemplate restTemplate;
    
    public NewsParent getAllNews(){
        NewsParent result;
        
        ResponseEntity<NewsParent> responseEntity = restTemplate.exchange(
                "https://www.news.developeridn.com/", 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<NewsParent>() {
        });
        
        result = responseEntity.getBody();
        return result;
    }
    
    public List getNews(NewsParent output){
        return output.getData();
    }
    
    public NewsDetailParent getBodyNews(String url){
        NewsDetailParent result;
        
        ResponseEntity<NewsDetailParent> responseEntity = restTemplate.exchange(
                "https://www.news.developeridn.com/detail/?url=" + url, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<NewsDetailParent>() {
        });
        
        result = responseEntity.getBody();
        return result;
    }
    
    public List getNewsDetail(NewsDetailParent output){
        return output.getData();
    }
    
}
