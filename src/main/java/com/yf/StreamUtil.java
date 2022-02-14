package com.yf;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合操作工具类
 *
 * @author yangfa
 * @date 2022/02/14
 */
public final class StreamUtil {

    private StreamUtil() {
    }

    /**
     * 对List每个元素进行计算后，组织成另一个List 如 List idList= StreamUtil.toList(catList, Cat::getId)
     */
    public static <K, V> List<V> toList(Collection<K> list, Function<K, V> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 对List每个元素进行计算后，组织成另一个List
     */
    public static <K, V1, V2> List<V2> toList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 对List每个元素进行计算后，组织成另一个List
     */
    public static <K, V1, V2, V3> List<V3> toList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2, Function<V2, V3> mapFunc3) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .map(mapFunc3).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Collection 转 List
     */
    public static <K> List<K> toList(Collection<K> list) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return new ArrayList<>(list);
    }

    /**
     * 对两层list合并
     */
    public static <T> List<T> reduce(Collection<Collection<T>> list) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        Optional<Collection<T>> reduce = list.stream()
                .reduce((a, b) -> {
                    ArrayList<T> objects = Lists.newArrayList();
                    objects.addAll(a);
                    objects.addAll(b);
                    return objects;
                });
        return reduce.get().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 合并子list
     */
    public static <K, V> List<V> reduce(Collection<K> list, Function<K, List<V>> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }
        ArrayList<V> objects = Lists.newArrayList();
        list.forEach(s -> {
            List<V> applyList = mapFunc.apply(s);
            if (applyList != null && !applyList.isEmpty()) {
                objects.addAll(applyList);
            }
        });

        return objects.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 合并子list
     */
    public static <K, V> List<V> reduce(Collection<K> list, Predicate<K> kPredicate, Function<K, List<V>> mapFunc, Predicate<V> vPredicate) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }
        ArrayList<V> vList = Lists.newArrayList();

        list.stream()
                .filter(kPredicate)
                .forEach(s -> {
                    vList.addAll(mapFunc.apply(s));
                });

        return vList.stream()
                .filter(vPredicate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 合并子list
     */
    public static <K, V> List<V> reduce(Collection<K> list, Function<K, List<V>> mapFunc, Predicate<V> vPredicate) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }
        ArrayList<V> vList = Lists.newArrayList();

        list.forEach(s -> {
            vList.addAll(mapFunc.apply(s));
        });

        return vList.stream()
                .filter(vPredicate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 转化成List后去重
     */
    public static <K, V> List<V> toDistinctList(Collection<K> list, Function<K, V> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }

    /**
     * 转化成List后去重
     */
    public static <K, V1, V2> List<V2> toDistinctList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }

    /**
     * 转化成List后去重
     */
    public static <K, V1, V2, V3> List<V3> toDistinctList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2, Function<V2, V3> mapFunc3) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .map(mapFunc3).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }


    /**
     *
     */
    public static <K, V1> List<V1> flatToDistinctList(Collection<K> list, Function<K, List<V1>> mapFunc) {
        return flatToDistinctList(list, mapFunc, i -> i);
    }

    public static <K, V1, V2> List<V2> flatToDistinctList(Collection<K> list, Function<K, List<V1>> mapFunc1, Function<V1, V2> mapFunc2) {
        return flatToList(list, mapFunc1, mapFunc2, true);
    }

    public static <K, V1> List<V1> flatToList(Collection<K> list, Function<K, List<V1>> mapFunc) {
        return flatToList(list, mapFunc, i -> i);
    }


    public static <K, V1, V2> List<V2> flatToList(Collection<K> list, Function<K, List<V1>> mapFunc1, Function<V1, V2> mapFunc2) {
        return flatToList(list, mapFunc1, mapFunc2, false);
    }

    public static <K, V1, V2> List<V2> flatToList(Collection<K> list, Function<K, List<V1>> mapFunc1, Function<V1, V2> mapFunc2, boolean distinct) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        List<V2> result = Lists.newArrayList();
        list.forEach(data -> {
            if (data == null) {
                return;
            }
            List<V1> v1List = mapFunc1.apply(data);
            if (v1List == null || v1List.isEmpty()) {
                return;
            }
            v1List.forEach(v1 -> {
                V2 v2 = mapFunc2.apply(v1);
                if (v2 != null) {
                    result.add(v2);
                }
            });
        });


        return distinct ? distinct(result) : result;
    }


    /**
     * 去重
     */
    public static <K> List<K> distinct(Collection<K> list) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 对Collection每个元素过滤后计算，组织成一个List
     */
    public static <K, V> List<V> filterAndMapToList(Collection<K> list, Predicate<K> filterFunc, Function<K, V> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .filter(filterFunc)
                .map(mapFunc)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 对List每个元素过滤后组织成另一个List
     */
    public static <K> List<K> filterToList(Collection<K> list, Predicate<K> filterFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream().filter(filterFunc).collect(Collectors.toList());
    }

    /**
     * 对List每个元素过滤后组织成另一个List
     */
    public static <K, V> List<V> filterToList(Collection<K> list, Predicate<K> filterFunc, Function<K, V> func) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream().filter(filterFunc).map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map 如 Map<Integer, Cat> catIdMap = StreamUtil.toMap(catList, Cat::getId)
     */
    public static <R, K> Map<K, R> toLinkedMap(Collection<R> list, Function<R, K> keyMapFunc) {
        return toLinkedMap(list, keyMapFunc, value -> value);
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map 如 Map<Integer, Cat> catIdMap = StreamUtil.toMap(catList, Cat::getId)
     */
    public static <R, K, V> Map<K, V> toLinkedMap(Collection<R> list, Function<R, K> keyMapFunc, Function<R, V> valueMapFunc) {
        if (list == null || list.isEmpty()) {
            return Maps.newLinkedHashMap();
        }

        LinkedHashMap<K, V> result = Maps.newLinkedHashMap();
        list.forEach(r -> {
            K key = keyMapFunc.apply(r);
            V value = valueMapFunc.apply(r);
            result.putIfAbsent(key, value);
        });
        return result;
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map 如 Map<Integer, Cat> catIdMap = StreamUtil.toMap(catList, Cat::getId)
     */
    public static <K, V> Map<V, K> toMap(Collection<K> list, Function<K, V> keyMapFunc) {
        return toMap(list, keyMapFunc, v -> v);
    }

    public static <K, V, F> Map<V, F> toMap(Collection<K> list, Function<K, V> keyMapFunc, Function<K, F> valueMapFunc) {
        if (list == null || list.isEmpty()) {
            return Maps.newHashMap();
        }

        Map<V, F> result = new HashMap<>(list.size());
        list.forEach(k -> {
            V mapKey = keyMapFunc.apply(k);
            //不校验是否重复
            result.put(mapKey, valueMapFunc.apply(k));
        });

        return result;
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map 如 Map<String, List<Cat>> catNameMap = StreamUtil.groupToMap(catList, Cat::getName);
     */
    public static <K, V> Map<V, List<K>> groupToMap(Collection<K> list, Function<K, V> keyMapFunc) {
        return groupToMap(list, keyMapFunc, v -> v);
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map， 如 Map<Integer, List<String>> catNameMap = StreamUtil.groupToMap(catList, Cat::getId, Cat::getName);
     */
    public static <K, V, F> Map<V, List<F>> groupToMap(Collection<K> list,
                                                       Function<K, V> keyMapFunc,
                                                       Function<K, F> valueMapFunc) {
        if (list == null || list.isEmpty()) {
            return Maps.newHashMap();
        }

        Map<V, List<F>> result = new HashMap<>(list.size());
        list.forEach(k -> {
            V mapKey = keyMapFunc.apply(k);
            List<F> valueList = result.computeIfAbsent(mapKey, v -> new ArrayList<>(10));
            valueList.add(valueMapFunc.apply(k));
        });

        return result;
    }

    public static <K> K filterFirst(Collection<K> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.stream().findFirst().get();
    }

    public static <K> K filterFirst(Collection<K> list, Predicate<K> predicate) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (K key : list) {
            if (predicate.test(key)) {
                return key;
            }
        }

        return null;
    }


    public static <T> List<T> distinctByKey(Collection<T> list, Function<? super T, Object> key) {
        if (CollectionUtils.isEmpty(list))
            return Collections.emptyList();
        return list.stream()
                .filter(distinctByKey(key))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 分页控件
     *
     * @param originList
     * @param pageSize
     * @return
     */
    public static <T> Map<Integer, List<T>> subList(List<T> originList, int pageSize) {
        if (CollectionUtils.isEmpty(originList)) {
            return null;
        }
        Map<Integer, List<T>> result = new HashMap<>();
        int totalCount = originList.size();
        int pageCount = 0;
        int m = totalCount % pageSize;
        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }
        for (int i = 1; i <= pageCount; i++) {
            List<T> newList = new ArrayList<>();
            if (m == 0) {
                newList = originList.subList((i - 1) * pageSize, pageSize * (i));
            } else {
                if (i == pageCount) {
                    newList = originList.subList((i - 1) * pageSize, totalCount);
                } else {
                    newList = originList.subList((i - 1) * pageSize, pageSize * (i));
                }
            }
            result.put(i, newList);
        }

        return result;
    }

    public static <T, R> List<R> batchExec(List<T> req, Integer pageSize, Function<List<T>, List<R>> func) {
        List<R> result = Lists.newLinkedList();

        if (CollectionUtils.isEmpty(req)) {
            return result;
        }

        try {
            pageSize = pageSize == null || pageSize <= 0 || pageSize > 20 ? 20 : pageSize;
            //页数 向上取整
            double pageCount = Math.ceil((double) req.size() / pageSize);

            //当页数是1时，不使用线程池，减少cpu切换线程的上下文开销
            if (pageCount == 1) {
                return func.apply(req);
            }
            for (int i = 0; i < pageCount; i++) {
                List<T> subReq = req.stream().skip(i * pageSize).limit(pageSize).collect(Collectors.toList());
                result.addAll(toList(func.apply(subReq)));
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return result;
    }

    /**
     * @param list
     * @param pageSize
     * @param func
     * @param threadExecutorService 可以不指定，默认使用自带线程池
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> batchExec(List<T> list, Integer pageSize, Function<List<T>, List<R>> func, ExecutorService threadExecutorService) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        pageSize = pageSize == null || pageSize <= 0 || pageSize > 20 ? 20 : pageSize;

        if (threadExecutorService == null) {
            return batchExec(list, pageSize, func);
        }
        List<R> result = Lists.newLinkedList();
        //队列
        LinkedBlockingQueue<Future<List<R>>> futures = new LinkedBlockingQueue<>();
        //页数 向上取整
        double pageCount = Math.ceil((double) list.size() / pageSize);

        //当页数是1时，不使用线程池，减少cpu切换线程的上下文开销
        if (pageCount == 1) {
            return batchExec(list, pageSize, func);
        }

        for (int i = 0; i < pageCount; i++) {
            List<T> subList = list.stream().skip(i * pageSize).limit(pageSize).collect(Collectors.toList());
            //线程池
            Future<List<R>> subFutureTask = threadExecutorService.submit(() -> func.apply(subList));
            futures.add(subFutureTask);
        }

        futures.forEach(s -> {
            List<R> rs = Collections.emptyList();
            try {
                rs = s.get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
            result.addAll(rs);
        });
        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, R> Map<T, List<R>> batchExecReturnMapList(List<T> req, Integer pageSize, Function<List<T>, Map<T, List<R>>> func) {
        Map<T, List<R>> result = Maps.newHashMap();

        if (CollectionUtils.isEmpty(req)) {
            return result;
        }

        try {
            pageSize = pageSize == null || pageSize <= 0 || pageSize > 20 ? 20 : pageSize;
            //页数 向上取整
            double pageCount = Math.ceil((double) req.size() / pageSize);


            //当页数是1时，不使用线程池，减少cpu切换线程的上下文开销
            if (pageCount == 1) {
                return func.apply(req);
            }


            for (int i = 0; i < pageCount; i++) {
                List<T> subReq = req.stream().skip(i * pageSize).limit(pageSize).collect(Collectors.toList());
                result.putAll(func.apply(subReq));
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return result;
    }

    public static <T, R> Map<T, List<R>> batchExecReturnMapList(List<T> req, Integer pageSize, Function<List<T>, Map<T, List<R>>> func, ExecutorService threadExecutorService) {
        if (threadExecutorService == null) {
            return batchExecReturnMapList(req, pageSize, func);
        }

        Map<T, List<R>> result = Maps.newHashMap();

        if (CollectionUtils.isEmpty(req)) {
            return result;
        }

        try {
            final int pageSizeFinal = pageSize == null || pageSize <= 0 || pageSize > 20 ? 20 : pageSize;
            //页数 向上取整
            double pageCount = Math.ceil((double) req.size() / pageSizeFinal);

            //当页数是1时，不使用线程池，减少cpu切换线程的上下文开销
            if (pageCount == 1) {
                return func.apply(req);
            }
            LinkedBlockingQueue<Future<Map<T, List<R>>>> futures = new LinkedBlockingQueue<>();
            for (int i = 0; i < pageCount; i++) {
                List<T> subReq = req.stream().skip(i * pageSizeFinal).limit(pageSizeFinal).collect(Collectors.toList());
                Future<Map<T, List<R>>> submit = threadExecutorService.submit(() -> func.apply(subReq));
                futures.add(submit);
            }
            for (Future<Map<T, List<R>>> s : futures) {
                Map<T, List<R>> listMap = s.get(5, TimeUnit.SECONDS);
                result.putAll(listMap);
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return result;
    }
}
