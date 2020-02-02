package org.chock.shop.disruptor;

import com.lmax.disruptor.WorkHandler;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.chock.shop.mapper.GroupLogMapper;

/**
 * @auther: zhuohuahe
 * @date: 2019/7/10 17:49
 * @description: 日志事件处理器
 */
@Setter
@AllArgsConstructor
public class LogEventHandler implements WorkHandler<LogEvent> {

    private GroupLogMapper groupLogMapper;

    @Override
    public void onEvent(LogEvent logEvent) throws Exception {
        groupLogMapper.insert(logEvent.getLog());
    }
}
