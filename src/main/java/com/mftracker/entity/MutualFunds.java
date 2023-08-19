package com.mftracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "mutual_funds")
@Entity
@Data
public class MutualFunds {

    @Id
    public String schemeCode;
    public String schemeName;
}
