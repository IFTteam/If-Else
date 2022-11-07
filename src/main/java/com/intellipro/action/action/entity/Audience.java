package com.intellipro.action.action.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Audience {
    @Id
    @SequenceGenerator(
            name = "audience_sequence",
            sequenceName = "audience_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "audience_sequence"
    )
    private long audienceId;

    private String email;
    private String first_name;
    private String last_name;

    private String address;
    private String phone;
    private LocalDate birthday;
    private String source;
    private LocalDate date_added;
    private LocalDate last_updated_time;
}

