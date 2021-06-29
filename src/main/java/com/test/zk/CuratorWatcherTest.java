package com.test.zk;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * @Date 2020/11/11
 * @Author yangfa
 * @Description
 */
public class CuratorWatcherTest implements CuratorWatcher {

    @Override
    public void process(WatchedEvent event) throws Exception {
        System.out.println("Zk event type is: = " + event.getType());
        switch (event.getType()) {
            case NodeChildrenChanged:
            case NodeDeleted:
            case NodeCreated:
            case NodeDataChanged:
            case None:
            default:
                break;
        }
        System.out.println("触发watcher，节点路径为：" + event.getPath());
        System.out.println("触发watcher，节点包装为：" + event.getWrapper());
        System.out.println("触发watcher，触发类型是为：" + event.getType());
    }
}