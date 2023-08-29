package com.mftracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "mutual_funds_name")
@Entity
@Data
public class MutualFundWithoutNav {
    @Id
    private String schemeCode;
    private String schemeName;
}
