package com.gu.thread.design.guardedsuspension;

import java.util.concurrent.Callable;

public abstract class GuardedAction<V> implements Callable<V> {
    protected final Predicate guard;

    public GuardedAction(Predicate guard){
        this.guard = guard;
    }
//    @Override
//    public V call() throws Exception {
//        return ;
//    }
}
