package com.mftracker.data;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Meta {
    private String fundHouse;
    private String schemeType;
    private String schemeCategory;
    private Integer schemeCode;
    private String schemeName;
}
