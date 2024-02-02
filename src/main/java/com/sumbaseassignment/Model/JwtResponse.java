package com.sumbaseassignment.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class JwtResponse {

    private String jwtToken;
    private String userName;
}
