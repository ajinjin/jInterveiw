package com.gu.thread.design.guardedsuspension;


import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * 保护性暂挂模式 例子
 *
 * 告警服务
 * 1.检测socket连上服务器
 * 2.第二个线程心跳检测 确认连接
 * 3.确认保护条件成立,发送告警
 *
 * @Author xuan.gu
 */
@Slf4j
public class AlarmAgent {

    private volatile boolean connectedToServer = false;

    //模式角色保护者 Predicate 设定保护条件
    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    //模式角色 Blocker  保护成立执行动作 ，否则阻塞线程
    private final Blocker blocker = new ConditionVarBlocker();

    //心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    //发送告警信息
    //guarderAction 关联保护条件和保护动作
    public void sendAlarm(final String alarm) throws Exception {
        //抽象目标动作 dosendalarm是一个动作 Call方法将它关联到保护条件
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected){
            public Void call()throws Exception{
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    //通过网络链接讲告警信息发送给告警服务器
    private void doSendAlarm(String alarm){
        //省略其他与设计模式无关的代码
        log.info("sending alarm"+alarm);
        //模拟发送告警值服务器的耗时
        try{
            Thread.sleep(50);
        }catch(Exception e){

        }
    }

    public void init(){
        //省略其他与设计模式无关的代码
        //告警服务器连接线程
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        heartbeatTimer.schedule(new HeartbeatTask(), 60000,2000);
    }

    public void disconnect(){
        //省略其他与设计模式无关的代码
        log.info("disconnected from alarm server.");
        connectedToServer = false;
    }

    protected void onConnected(){
        try{
            blocker.signalAfter(new Callable<Boolean>(){
                @Override
                public Boolean call(){
                    connectedToServer = true;
                    log.info("connnected to server");
                    return Boolean.TRUE;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    protected void onDisconnected(){
        connectedToServer = false;
    }

    //负责与告警服务器建立网络链接
    private class ConnectingTask implements Runnable{
        @Override
        public void run(){
            //省略其他与设计模式无关的代码
            //模拟连接操作耗时
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){

            }
        }
    }

    //心跳定时任务：定时检车与该井服务器的链接是否正常，发现链接异常后自动重新链接
    private class HeartbeatTask extends TimerTask {
        @Override
        public void run(){
            //省略其他与设计模式无关的代码
            if(!testConnection()){
                onDisconnected();
                reconnect();
            }
        }
        private boolean testConnection(){
            //省略其他与设计模式无关的代码
            return true;
        }

        private void reconnect(){
            ConnectingTask connectingThread = new ConnectingTask();
            //直接在心跳定时器线程中执行
            connectingThread.run();
        }
    }

}
