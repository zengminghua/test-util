package com.zmh.util.vo.request;

import java.io.Serializable;

/**
 * Base Request
 *
 * @author zengminghua
 * @version 0.0.1
 * @update 20180724
 */
public class BaseRequest implements Serializable {



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
