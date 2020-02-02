package org.chock.shop.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import org.chock.shop.entity.GroupLog;

/**
 * @auther: zhuohuahe
 * @date: 2019/7/10 17:42
 * @description:
 */
@AllArgsConstructor
public class LogEventProducer {

    private EventTranslatorOneArg translator;

    private RingBuffer<LogEvent> ringBuffer;

    public void recordLog(GroupLog log){
        this.ringBuffer.publishEvent(translator, log);
    }
}
