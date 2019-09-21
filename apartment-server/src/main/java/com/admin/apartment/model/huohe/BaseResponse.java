package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liangming
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -6957361951748382518L;

    private String rlt_code;

    private String rlt_msg;

    private T data;

}
