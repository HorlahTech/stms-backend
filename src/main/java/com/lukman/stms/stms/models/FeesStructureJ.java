package com.lukman.stms.stms.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.lukman.stms.stms.application.constant.AvailabilityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeesStructureJ {
    @Id
    private String id;
    private String sessionName;
    private int term;
    private String feeName;
    private int amount;
    private FeesApplicabilityEnumY applicability;
    private AvailabilityEnum availabilityStatus;
    private String schoolCode;
    private List<String> classesNames;
}

enum FeesApplicabilityEnumY {
    required, optional
}