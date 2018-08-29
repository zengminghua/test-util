package com.zmh.util.test;

import com.zmh.util.tools.IpValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * xxx
 *
 * @author zengminghua
 */
public class IpValidateTest {

    /**
     * sl4j
     */
    private static Logger logger = LoggerFactory.getLogger(IpValidateTest.class);

    public static void main(String[] args) {

        String testIp = "192.168.0.*";
        boolean validateResult = IpValidate.ipv4Validate(testIp);
        logger.info("validate result is :{}", validateResult);
    }
}
