package com.zmh.util.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * validate ip
 *
 * @author zengminghua
 */
public class IpValidate {

    /**
     * ipv4 regular expression
     */
    private static final String IPV4_REGULAR_EXPRESSION = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * ipv6 regular expression
     * TODO
     */
    private static final String IPV6_REGULAR_EXPRESSION = "";

    /**
     * validate ipv4 address
     *
     * @param ip
     * @return
     */
    public static boolean ipv4Validate(String ip) {

        Pattern pattern = Pattern.compile(IPV4_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(ip);

        return matcher.matches();
    }

    /**
     * validate ipv6 address
     *
     * @param ip
     * @return
     */
    public static boolean ipv6Validate(String ip) {

        Pattern pattern = Pattern.compile(IPV6_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(ip);

        return matcher.matches();
    }

}
