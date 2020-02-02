package org.chock.shop.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import org.chock.shop.entity.GroupLog;

/**
 * @auther: zhuohuahe
 * @date: 2019/7/10 17:29
 * @description: 日志事件转换器
 */
public class LogEventTranslator implements EventTranslatorOneArg<LogEvent, GroupLog> {
    @Override
    public void translateTo(LogEvent logEvent, long l, GroupLog log) {
        logEvent.setLog(log);
    }
}
