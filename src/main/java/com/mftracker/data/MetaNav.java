package com.mftracker.data;

import com.mftracker.entity.MutualFundNav;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class MetaNav {
    private Meta meta;
    private List<MutualFundNav> data;
    private String status;
}
