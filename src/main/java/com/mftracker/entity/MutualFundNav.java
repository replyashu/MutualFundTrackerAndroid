package com.mftracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "mutual_fund_nav")
@Entity
@Data
public class MutualFundNav {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String date;
    private String nav;
}
