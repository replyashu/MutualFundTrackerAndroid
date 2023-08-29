package com.mftracker.controller;

import com.mftracker.data.MetaNav;
import com.mftracker.entity.MutualFund;
import com.mftracker.entity.MutualFundNav;
import com.mftracker.entity.MutualFundWithoutNav;
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
    public List<MutualFund> fetchMutualFundsList() {
        String uri = "https://api.mfapi.in/mf";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MutualFund[]> response =
                restTemplate.getForEntity(
                        uri,
                        MutualFund[].class);

        MutualFund[] funds = response.getBody();

        ResponseEntity<MutualFundWithoutNav[]> responseWithoutNav =
                restTemplate.getForEntity(
                        uri,
                        MutualFundWithoutNav[].class);

        MutualFundWithoutNav[] mutualFundWithoutNavs = responseWithoutNav.getBody();

        List<MutualFund> mutualFunds = Arrays.stream(funds).toList();
        List<MutualFundWithoutNav> mutualFundWithoutNavs1 = Arrays.stream(mutualFundWithoutNavs).toList();
        mutualFundService.saveAllMutualFunds(mutualFunds);
        mutualFundService.saveAllMutualFundsWithoutNav(mutualFundWithoutNavs1);

        return mutualFunds;
    }

    @Cacheable("mutual-funds")
    @RequestMapping("/find-mutual-funds")
    @ResponseBody
    public MutualFund fetchMutualFund(@RequestParam(name="id") String schemaId) {
        return mutualFundService.findByMutualFundSchemeId(schemaId);
    }

    @CachePut(value = "mutual-funds", key = "#schemeCode")
    @PostMapping("/update")
    public List<MutualFund> updateMutualFunds() {
        String uri = "https://api.mfapi.in/mf";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MutualFund[]> response =
                restTemplate.getForEntity(
                        uri,
                        MutualFund[].class);
        MutualFund[] funds = response.getBody();

        List<MutualFund> mutualFunds = Arrays.stream(funds).toList();
        mutualFundService.saveAllMutualFunds(mutualFunds);

        return mutualFunds;
    }

    @RequestMapping("/search-mutual-funds")
    @ResponseBody
    public List<MutualFund> searchMutualFunds(@RequestParam(name = "keyWord") String keyWords) {
        String uri = "https://api.mfapi.in/mf/search?q=" + keyWords;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MutualFund[]> response =
                restTemplate.getForEntity(
                        uri,
                        MutualFund[].class);
        MutualFund[] funds = response.getBody();

        return Arrays.stream(funds).toList();
    }

    @RequestMapping("/find-mutual-fund-nav")
    @ResponseBody
    public Boolean findMutualFundNav(@RequestParam(name = "mutualFundSchemeId") String schemeId) {

        String uri = "https://api.mfapi.in/mf/" + schemeId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MetaNav> response =
                restTemplate.getForEntity(
                        uri,
                        MetaNav.class);
        List<MutualFundNav> navList = response.getBody().getData();

        // Find mutual Fund by scheme name
        MutualFund mutualFund = findMutualFund(schemeId);

        mutualFund.setMutualFundNavList(navList);
        mutualFundService.saveMutualFunds(mutualFund);
        return true;
    }

    @RequestMapping("/fetch-mutual-funds")
    @ResponseBody
    public List<MutualFund> fetchAllMutualFundsList() {
        return mutualFundService.findAllMutualFunds();
    }

    @RequestMapping("/fetch-mutual-funds-names")
    @ResponseBody
    public List<MutualFundWithoutNav> fetchAllMutualFundsListWithoutNav() {
        return mutualFundService.findAllMutualfundsWithoutNav();
    }

    private MutualFund findMutualFund(String schemeId) {
        return mutualFundService.findByMutualFundSchemeId(schemeId);
    }

}
