package com.admin.apartment.model.huohe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class LockListRequest implements Serializable {

    private static final long serialVersionUID = -6957361951748382517L;

    /**
     * 每页行数
     * */
    private String page_size;

    /**
     * 页码
     * */
    private String current_page;

    /**
     * 网关编码
     * */
    private String node_no;

    /**
     * 门锁编码
     * */
    private String lock_no;

    /**
     * 房源编码
     * */
    private String house_code;

    /**
     * 房间编码
     * */
    private String room_code;

    public void setNode_no(String node_no) {
        if(node_no == null || node_no.isEmpty()){
            this.node_no = null;
        } else {
            this.node_no = node_no;
        }
    }

    public void setLock_no(String lock_no) {
        if(lock_no == null || lock_no.isEmpty()){
            this.lock_no = null;
        } else {
            this.lock_no = lock_no;
        }
    }

    public void setHouse_code(String house_code) {
        if(house_code == null || house_code.isEmpty()){
            this.house_code = null;
        } else {
            this.house_code = house_code;
        }
    }

    public void setRoom_code(String room_code) {
        if(room_code == null || room_code.isEmpty()){
            this.room_code = null;
        } else {
            this.room_code = room_code;
        }
    }
}
