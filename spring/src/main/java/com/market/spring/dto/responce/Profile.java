package com.market.spring.dto.responce;

import com.market.spring.models.customer.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String name;
    private String username;
    private Role role;
}
