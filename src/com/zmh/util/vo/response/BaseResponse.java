package com.zmh.util.vo.response;

import java.io.Serializable;

/**
 * Base response
 *
 * @author zengminghua
 * @version 0.0.1
 * @update 20180724
 */
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 7862352238863883804L;

    /**
     * requestId
     */
    protected Long requestId;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
