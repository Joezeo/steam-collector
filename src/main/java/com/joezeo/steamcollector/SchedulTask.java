package com.joezeo.steamcollector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/16 11:26
 */
@Component
@Slf4j
@Lazy(false) /*取消延迟初始化，否则定时任务无法运行*/
public class SchedulTask {

}
