package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by TOM
 * On 2020/4/9 15:54
 */
public class BusinessThreadUtil {

  public static ExecutorService ImBusinessExec;

  static {
    ImBusinessExec = new ThreadPoolExecutor(10, 1000, 60L, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), r -> new Thread(r,"ImBusinessThread"), new CallerRunsPolicy());
  }
}
