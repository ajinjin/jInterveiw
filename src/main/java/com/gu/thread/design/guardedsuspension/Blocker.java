package com.gu.thread.design.guardedsuspension;

import java.util.concurrent.Callable;

public interface Blocker {
    //在保护条件成立时执行目标动作；否则阻塞当前线程，知道保护条件成立
    <V> V callWithGuard(GuardedAction<V>guardedAction) throws Exception;

    //执行 stateOperation所制定的操作后，决定是否唤醒本Blocker
    void signalAfter(Callable<Boolean>stateOperation) throws Exception;

    void signal() throws InterruptedException;

    //执行stateOperation所制定的操作后，决定是否唤醒本Blocker
    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;

}
