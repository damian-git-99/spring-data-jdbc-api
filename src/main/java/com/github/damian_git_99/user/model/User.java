package com.github.damian_git_99.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String email;
    private String phone;

}
