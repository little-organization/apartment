package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class LockViewRequest implements Serializable {

    private static final long serialVersionUID = -6957361951748382515L;

    /**
     * 门锁编码
     * */
    private String lock_no;
}
