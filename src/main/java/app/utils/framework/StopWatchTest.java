package app.utils.framework;

import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @Author: bai
 * @Date: 2021/11/25 15:30
 *
 * springboot 启动用来计时的工具
 */
public class StopWatchTest {

    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("task01");
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();

        stopWatch.start("task02");
        TimeUnit.SECONDS.sleep(1);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeMillis());

       for(StopWatch.TaskInfo taskInfo : stopWatch.getTaskInfo()){
           System.out.println(taskInfo.getTaskName() + ","+taskInfo.getTimeMillis());
       }

    }

}
