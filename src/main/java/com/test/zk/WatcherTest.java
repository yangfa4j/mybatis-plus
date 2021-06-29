package com.test.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Date 2020/11/11
 * @Author yangfa
 * @Description
 */
public class WatcherTest implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Zk event type is：" + watchedEvent.getType());
        switch (watchedEvent.getType()) {
            case NodeCreated:
            case NodeDeleted:
            case NodeChildrenChanged:
            case NodeDataChanged:
            case None:
            default:
                break;
        }
        System.out.println("触发watcher，节点路径为：" + watchedEvent.getPath());
        System.out.println("触发watcher，节点状态为：" + watchedEvent.getState());
    }
}