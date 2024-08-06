package com.foundations.common.model;

import lombok.Data;
import lombok.Setter;

@Data
public class AuthRequest {
    public String username;
    public String password;
}
