package org.chock.shop.disruptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.GroupLog;

/**
 * @auther: zhuohuahe
 * @date: 2019/7/10 17:23
 * @description: 日志事件实体
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogEvent {
    private GroupLog log;
}
