package com.test.zk;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ZkClient {


    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 5000, 5000, retryPolicy);
        client.start();
//        addNode(client);
//        deleteNode(client);
//        getNodeData(client);
//        setNodeData(client);
//        checkNodeExists(client);
//        getChildren(client);
//        addWatcher(client);
//        nodeCache(client, "/yangfa");
//        pathChildrenCache(client, "/yangfa");
//        connectionStateListener(client);
        treeCache(client, "/yangfa");
        TimeUnit.SECONDS.sleep(5000000);
        client.close();


        List<String> strings = Arrays.asList("1");
        strings.add("2");

    }

    public static void addNode(CuratorFramework client) throws Exception {
        String s = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/yangfa/ls", "lisi".getBytes());
        System.out.println("s = " + s);
    }

    public static void getNodeData(CuratorFramework client) throws Exception {
        byte[] bytes = client.getData().forPath("/yangfa");
        System.out.println("bytes = " + new String(bytes, StandardCharsets.UTF_8));
    }

    public static void setNodeData(CuratorFramework client) throws Exception {
        Stat stat = client.setData().forPath("/yangfa", "双11".getBytes());
        System.out.println(stat);
    }

    public static void checkNodeExists(CuratorFramework client) throws Exception {
        Stat stat = client.checkExists().forPath("/yangfa");
        System.out.println(stat);
    }

    public static void deleteNode(CuratorFramework client) throws Exception {
        Void aVoid = client.delete().deletingChildrenIfNeeded().forPath("/yangfa/ls");
        System.out.println("aVoid = " + aVoid);
    }

    public static void getChildren(CuratorFramework client) throws Exception {
        List<String> strings = client.getChildren().forPath("/");
        strings.forEach(System.out::println);
    }

    public static void transaction(CuratorFramework client) throws Exception {
        client.inTransaction().check().forPath("/yangfa")
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/yangfa", "data".getBytes())
                .and()
                .setData().withVersion(10086).forPath("/yangfa", "data2".getBytes())
                .and()
                .commit();
    }


    // curator之usingWatcher
    // curator在注册watch事件上，提供了一个usingWatcher方法，使用这个方法注册的watch事件和默认watch事件一样，监听只会触发一次，监听完毕后就会销毁，也就是一次性的。
    // 而这个方法有两种参数可选，一个是zk原生API的Watcher接口的实现类，另一个是Curator提供的CuratorWatcher接口的实现类，不过在usingWatcher方法上使用哪一个效果都是一样的，都是一次性的。
    public static void addWatcher(CuratorFramework client) throws Exception {
        client.getData().usingWatcher(new CuratorWatcherTest()).forPath("/yangfa");
        client.getData().usingWatcher(new WatcherTest()).forPath("/yangfa");
        TimeUnit.SECONDS.sleep(50000);
    }

    // curator之nodeCache一次注册N次监听
    public static void nodeCache(CuratorFramework client, String path) throws Exception {
        NodeCache nodeCache = new NodeCache(client, path);
        nodeCache.start(true);
        ChildData currentData = nodeCache.getCurrentData();
        if (currentData != null) {
            System.out.println("当前节点路径为：" + currentData.getPath());
            System.out.println("当前节点数据为：" + new String(currentData.getData(), StandardCharsets.UTF_8));
            System.out.println("当前节点状态为：" + currentData.getStat());
        } else {
            System.out.println("当前节点数据为空！！！");
        }

        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if (nodeCache.getCurrentData() == null) {
                    System.out.println("获取节点数据异常，无法获取当前缓存的节点数据，可能该节点已被删除");
                    return;
                }
                // 获取节点最新的数据
                String data = new String(nodeCache.getCurrentData().getData(), StandardCharsets.UTF_8);
                System.out.println(nodeCache.getCurrentData().getPath() + " 节点的数据发生变化，最新的数据为：" + data);
            }
        });
    }


    // ConnectionStateListener 监听连接状态
    public static void connectionStateListener(CuratorFramework client) throws Exception {
        ConnectionStateListener listener = new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("Zk event type is: " + newState);
                switch (newState) {
                    case CONNECTED:
                        System.out.println("zk 连接上了！");
                        break;
                    case RECONNECTED:
                        System.out.println("zk 重连接了！");
                        break;
                    case LOST:
                        System.out.println("zk 连接断开了！");
                        break;
                    default:
                        break;
                }
            }
        };
        client.getConnectionStateListenable().addListener(listener);
    }


    // curator之PathChildrenCache子节点监听
    public static void pathChildrenCache(CuratorFramework client, String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        List<ChildData> currentData = pathChildrenCache.getCurrentData();
        if (CollectionUtil.isNotEmpty(currentData)) {
            currentData.forEach(i -> {
                System.out.println("当前节点路径为：" + i.getPath());
                System.out.println("当前节点数据为：" + new String(i.getData(), StandardCharsets.UTF_8));
                System.out.println("当前节点状态为：" + i.getStat());
            });
        }
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("Zk event type is: " + event.getType());
                switch (event.getType()) {
                    case INITIALIZED:
                        System.out.println("\n--------------\n");
                        System.out.println("子节点初始化成功");
                        System.out.println("该子节点的路径为：" + event.getData().getPath());
                        System.out.println("该子节点的数据为：" + new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case CHILD_ADDED:
                        System.out.println("\n--------------\n");
                        System.out.print("子节点：" + event.getData().getPath() + " 添加成功，");
                        System.out.println("该子节点的数据为：" + new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case CHILD_REMOVED:
                        System.out.println("\n--------------\n");
                        System.out.println("子节点：" + event.getData().getPath() + " 删除成功");
                        break;
                    case CHILD_UPDATED:
                        System.out.println("\n--------------\n");
                        System.out.print("子节点：" + event.getData().getPath() + " 数据更新成功，");
                        System.out.println("子节点：" + event.getData().getPath() + " 新的数据为：" + new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case CONNECTION_LOST:
                    case CONNECTION_SUSPENDED:
                    case CONNECTION_RECONNECTED:
                    default:
                        break;
                }
            }
        });
    }

    // curator之treeCache监听
    public static void treeCache(CuratorFramework client, String path) throws Exception {
        TreeCache treeCache = new TreeCache(client, path);
        treeCache.start();
        ChildData currentData = treeCache.getCurrentData(path);
        if (currentData != null) {
            System.out.println("当前节点路径为：" + currentData.getPath());
            System.out.println("当前节点数据为：" + new String(currentData.getData(), StandardCharsets.UTF_8));
            System.out.println("当前节点状态为：" + currentData.getStat());
        } else {
            System.out.println("当前节点数据为空！！！");
        }
        Map<String, ChildData> currentChildren = treeCache.getCurrentChildren(path);

        if (CollectionUtil.isNotEmpty(currentChildren)) {
            currentChildren.forEach((key, value) -> {
                System.out.println();
                System.out.print("节点名称 = " + key);
                System.out.print("节点值 = " + new String(value.getData(), StandardCharsets.UTF_8));
            });
        }
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("Zk event type is: " + event.getType());
                switch (event.getType()) {
                    case NODE_ADDED:
                        System.out.println("\n--------------\n");
                        System.out.print("节点：" + event.getData().getPath() + " 添加成功，");
                        System.out.println("该节点的数据为：" + new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case NODE_UPDATED:
                        System.out.println("\n--------------\n");
                        System.out.print("节点：" + event.getData().getPath() + " 数据更新成功，");
                        System.out.println("节点：" + event.getData().getPath() + " 新的数据为：" + new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case NODE_REMOVED:
                        System.out.println("\n--------------\n");
                        System.out.println("节点：" + event.getData().getPath() + " 删除成功");
                        break;
                    case CONNECTION_RECONNECTED:
                        System.out.println("zk 重连接了！");
                        break;
                    case INITIALIZED:
                        System.out.println("zk 已经初始化了！");
                        break;
                    case CONNECTION_SUSPENDED:
                        System.out.println("zk 连接暂停了！");
                        break;
                    case CONNECTION_LOST:
                        System.out.println("zk 连接断开了！");
                        break;
                    default:
                        break;
                }
            }
        });
    }


}