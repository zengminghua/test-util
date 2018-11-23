package com.zmh.util.tools;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串脱敏处理工具
 *
 * @author zengminghua
 * @since 20180914
 */
public class ConcealTool {

    /**
     * sl4j日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(ConcealTool.class);

    /**
     * 脱敏替换字符
     */
    private static final String CONCEAL_SYMBOL = "*";

    /**
     * 执行脱敏处理
     * <p>
     * 替换字符串中间指定长度内容为*号；
     * 当脱敏长度与字符串长度相等时，则只保留字符串头尾两个自负，其余均替换，如：a******b
     *
     * @param value  待脱敏的字符串
     * @param length 脱敏长度
     * @return
     */
    public static String doConceal(String value, int length) {

        if (StringUtils.isBlank(value)) {
            return value;
        }

        logger.info("收到字符串脱敏处理请求，待脱敏字符串：[{}]，脱敏长度：{}。", value, length);

        int valueLength = value.length();

        // 自动调整脱敏长度
        if (length > valueLength) {
            length = valueLength;
            logger.warn("待脱敏长度设置不正确，自动调整为：{}。", length);
        }

        int pamaone = valueLength / 2;
        int pamatwo = pamaone - 1;
        int pamathree = valueLength % 2;
        StringBuilder stringBuilder = new StringBuilder();
        if (valueLength <= 2) {
            if (pamathree == 1) {
                return CONCEAL_SYMBOL;
            }
            stringBuilder.append(CONCEAL_SYMBOL);
            stringBuilder.append(value.charAt(valueLength - 1));
        } else {
            if (pamatwo <= 0) {
                stringBuilder.append(value.substring(0, 1));
                stringBuilder.append(CONCEAL_SYMBOL);
                stringBuilder.append(value.substring(valueLength - 1, valueLength));

            } else if (pamatwo >= length / 2 && length + 1 != valueLength) {
                int pamafive = (valueLength - length) / 2;
                stringBuilder.append(value.substring(0, pamafive));
                for (int i = 0; i < length; i++) {
                    stringBuilder.append(CONCEAL_SYMBOL);
                }
                if ((pamathree == 0 && length / 2 == 0) || (pamathree != 0 && length % 2 != 0)) {
                    stringBuilder.append(value.substring(valueLength - pamafive, valueLength));
                } else {
                    stringBuilder.append(value.substring(valueLength - (pamafive + 1), valueLength));
                }
            } else {
                int pamafour = valueLength - 2;
                stringBuilder.append(value.substring(0, 1));
                for (int i = 0; i < pamafour; i++) {
                    stringBuilder.append(CONCEAL_SYMBOL);
                }
                stringBuilder.append(value.substring(valueLength - 1, valueLength));
            }
        }

        logger.info("脱敏后的字符串为：[{}]。", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
