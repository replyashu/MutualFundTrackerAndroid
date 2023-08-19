package com.mftracker.controller;

import com.mftracker.entity.MutualFunds;
import com.mftracker.service.funds.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class FundController {

    @Autowired
    MutualFundService mutualFundService;

    @RequestMapping("/get-mutual-funds")
    @ResponseBody
    public List<MutualFunds> fetchMutualFundsList() {
        String uri = "https://api.mfapi.in/mf";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MutualFunds[]> response =
                restTemplate.getForEntity(
                        uri,
                        MutualFunds[].class);
        MutualFunds[] funds = response.getBody();

        List<MutualFunds> mutualFunds = Arrays.stream(funds).toList().subList(0, 11);
        mutualFundService.saveAllMutualFunds(mutualFunds);

        return mutualFunds;
    }

    @Cacheable("mutual-funds")
    @RequestMapping("/find-mutual-funds")
    @ResponseBody
    public Optional<MutualFunds> fetchMutualFund(@RequestParam(name="id") String schemaId) {
        return mutualFundService.findByUMutualFundSchemeId(schemaId);
    }

    @CachePut(value = "mutual-funds", key = "#schemeCode")
    @PostMapping("/update")
    public List<MutualFunds> updateMutualFunds() {
        String uri = "https://api.mfapi.in/mf";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MutualFunds[]> response =
                restTemplate.getForEntity(
                        uri,
                        MutualFunds[].class);
        MutualFunds[] funds = response.getBody();

//        for (MutualFunds fund: funds) {
//            mutualFundService.saveMutualFunds(fund);
//        }
        List<MutualFunds> mutualFunds = Arrays.stream(funds).toList();
        mutualFundService.saveAllMutualFunds(mutualFunds);

        return mutualFunds;
    }

    @RequestMapping("/search-mutual-funds")
    @ResponseBody
    public List<MutualFunds> searchMutualFunds(@RequestParam(name = "keyWord")String keyWords) {
        String uri = "https://api.mfapi.in/mf/search?q=" + keyWords;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MutualFunds[]> response =
                restTemplate.getForEntity(
                        uri,
                        MutualFunds[].class);
        MutualFunds[] funds = response.getBody();

        return Arrays.stream(funds).toList();
    }
}
