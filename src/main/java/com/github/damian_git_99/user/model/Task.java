package com.github.damian_git_99.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String description;
    private Long userId;
}
