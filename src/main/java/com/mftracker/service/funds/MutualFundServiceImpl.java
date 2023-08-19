package com.mftracker.service.funds;


import com.mftracker.entity.MutualFunds;
import com.mftracker.entity.User;
import com.mftracker.repository.FundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MutualFundServiceImpl implements MutualFundService {

    @Autowired
    private FundsRepository fundsRepository;

    @Override
    public void saveMutualFunds(MutualFunds mutualFunds) {
        fundsRepository.save(mutualFunds);
    }

    @Override
    public int saveAllMutualFunds(List<com.mftracker.entity.MutualFunds> mutualFunds) {
        List<com.mftracker.entity.MutualFunds> funds = fundsRepository.saveAll(mutualFunds);
        return funds.size();
    }

    @Override
    public User findUserByMutualFundSchemeName(String email) {
        return null;
    }

    @Override
    public Optional<MutualFunds> findByUMutualFundSchemeId(String id) {
        return fundsRepository.findById(Long.valueOf(id));
    }

    @Override
    public List<MutualFunds> findAllMutualFunds() {
        return fundsRepository.findAll();
    }
}
