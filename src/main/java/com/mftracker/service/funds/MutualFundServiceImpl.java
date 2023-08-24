package com.mftracker.service.funds;


import com.mftracker.entity.MutualFund;
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
    public void saveMutualFunds(MutualFund mutualFund) {
        fundsRepository.save(mutualFund);
    }

    @Override
    public int saveAllMutualFunds(List<MutualFund> mutualFunds) {
        List<MutualFund> funds = fundsRepository.saveAll(mutualFunds);
        return funds.size();
    }

    @Override
    public User findUserByMutualFundSchemeName(String email) {
        return null;
    }

    @Override
    public MutualFund findByMutualFundSchemeId(String id) {
        System.out.println(id);
        System.out.println(Long.valueOf(id));
        Optional<MutualFund> mf = fundsRepository.findById(id);

        if (mf.get() != null) {
            return mf.get();
        } else {
            return new MutualFund();
        }
    }

    @Override
    public List<MutualFund> findAllMutualFunds() {
        return fundsRepository.findAll();
    }
}
