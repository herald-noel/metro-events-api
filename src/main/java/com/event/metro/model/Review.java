package com.event.metro.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Review {
    @Id
    String reviewId;
    @NotBlank
    String username;
    @NotBlank
    @Size(min=5, max = 100)
    String comment;
}
