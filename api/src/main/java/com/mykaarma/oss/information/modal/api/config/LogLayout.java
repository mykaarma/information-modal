/**
 * Information Modal
 * Copyright (C) 2020 myKaarma.
 * opensource@mykaarma.com
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mykaarma.oss.information.modal.api.config;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Custom Log Layout to configure log messge format, content , referenced in logback.xml
 * Logs all MDC properties
 */
public class LogLayout extends LayoutBase<ILoggingEvent> {

    private static final String LOG_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private final DateFormat logDateFormant = new SimpleDateFormat(LOG_DATE_FORMAT_PATTERN);

    private final List<String> stackOptionList = Collections.singletonList("20");

    @Override
    public String doLayout(ILoggingEvent event) {

        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(logDateFormant.format(new Date(event.getTimeStamp()))).append(" ; ");

        stringBuilder.append(event.getLevel()).append(" ; ");
        stringBuilder.append(event.getThreadName()).append(" --- ; ");

        StackTraceElement[] stackTraceElements = event.getCallerData();
        stringBuilder.append("class_name=").append(ClassUtils.getAbbreviatedName(stackTraceElements[0].getClassName(), 36)).append(" ; ");
        stringBuilder.append("method_name=").append(stackTraceElements[0].getMethodName()).append(" ; ");
        stringBuilder.append("line_number=").append(stackTraceElements[0].getLineNumber()).append(" ; ");

        // Log all MDC properties ad KeyValues Pairs
        Map<String, String> mdcMap = event.getMDCPropertyMap();
        for (Map.Entry<String, String> entry : mdcMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                stringBuilder.append(" ").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        if (StringUtils.isNotBlank(event.getFormattedMessage()) && !event.getFormattedMessage().equalsIgnoreCase("{}")) {
            stringBuilder.append(" ; message=").append(event.getFormattedMessage());
        }
        IThrowableProxy proxy = event.getThrowableProxy();
        if (proxy != null) {
            ThrowableProxyConverter converter = new ThrowableProxyConverter();
            converter.setOptionList(stackOptionList);
            converter.start();
            stringBuilder.append(", exception_msg=").append(proxy.getMessage()).append(",");
            stringBuilder.append(" stack_trace='");
            stringBuilder.append(converter.convert(event));
        }
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        return stringBuilder.toString();
    }
}
