package com.mftracker.controller;

import com.mftracker.data.MutualFunds;
import com.mftracker.service.funds.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

        for (MutualFunds fund: funds) {
            mutualFundService.saveMutualFunds(fund);
        }

        return Arrays.stream(funds).toList();
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

        System.out.println("result" + Arrays.stream(funds).toList());



        return Arrays.stream(funds).toList();
    }
}
