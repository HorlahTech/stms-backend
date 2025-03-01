package com.lukman.stms.stms.application.dto.request;

import java.util.List;

import com.lukman.stms.stms.application.constant.Applicability;
import com.lukman.stms.stms.application.constant.AvailabilityEnum;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeesDto {
    @NotBlank(message = "Session Name is required")
    @NotEmpty(message = "Session Name is required")
    @NotNull(message = "Session Name is required")
    private String sessionName;
    @Min(value = 1, message = "Invalid Term Value")
    @Max(value = 3, message = "Invalid Term Value")
    @NotNull(message = "Invalid Term Value")
    private int term;
    @NotBlank(message = "Fee Name is required")
    @NotEmpty(message = "Fee Name is required")
    @NotNull(message = "Fee Name is required")
    private String feeName;
    @NotNull(message = "Amount is required")
    @Min(0)
    private int amount;
    private Applicability applicability;
    private AvailabilityEnum availabilityStatus;

    private String schoolCode;
    // @NotBlank(message = "Classes Name is required")
    @NotEmpty(message = "Classes Name is required")
    @NotNull(message = "Classes Name is required")
    private List<String> classesNames;
}
