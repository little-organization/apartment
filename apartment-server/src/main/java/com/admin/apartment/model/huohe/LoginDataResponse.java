package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class LoginDataResponse implements Serializable {

    private static final long serialVersionUID = -6957361951748382513L;

    private String access_token;

    private int expires_second;

    private int expires_times;
}
