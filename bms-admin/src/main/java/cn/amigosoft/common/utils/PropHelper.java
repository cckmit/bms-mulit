package cn.amigosoft.common.utils;


import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author
 * @Description:
 * @CreateTime:
 */
public class PropHelper<T, E> {

    public interface Dao<E> {
        List<E> selectProp(List<Long> idList);
    }

    /**
     * @param list       主列表数据 （成果列表）
     * @param getId      列表对象的获取主键属性方法 （成果对象getId）
     * @param setList    列表对象中子设置子列表 (成果中属性子列表)
     * @param getGroupId 列表对象中子设置子列表的外键
     * @param dao        获取子列表执行的SQL
     */
    public void assemble(List<T> list, Function<T, Long> getId, BiConsumer<T, List> setList, Function<E, Long> getGroupId, Dao dao) {
        if (list == null || list.isEmpty()) {
            return;
        }
        // 提取id为子数组
        List<Long> idList = list.stream().map(getId).collect(Collectors.toList());
        System.out.println(idList);
        if (idList == null || idList.isEmpty()) {
            return;
        }
        // 查询子属性组
        List<E> sonAllList = dao.selectProp(idList);
        if (sonAllList == null || sonAllList.isEmpty()) {
            return;
        }
        // 进行分组
        Map<Long, List<E>> collect = groupBy(sonAllList, getGroupId);
        if (collect == null || collect.isEmpty()) {
            return;
        }
        for (T t : list) {
            Long id = getId(getId, t);
            List<E> sonList = collect.get(id);
            setList(setList, t, sonList);
        }

    }


    public Map<Long, List<E>> groupBy(List<E> list, Function<E, Long> f) {
        Map<Long, List<E>> collect = list.stream().collect(Collectors.groupingBy(f));
        return collect;
    }

    public Long getId(Function<T, Long> f, T t) {
        return f.apply(t);
    }

    public void setList(BiConsumer<T, List> f, T t, List u) {
        f.accept(t, u);
    }

//    public static void main(String[] args) {
//        PropHelper<Parent, Son> p = new PropHelper<>();
//
//        List<Parent> ps = new ArrayList<>();
//        Parent x1 = new Parent();
//        x1.setId(1L);
//        Parent x2 = new Parent();
//        x2.setId(2L);
//        ps.add(x1);
//        ps.add(x2);
//        p.assemble(ps, Parent::getId, Parent::setSons, Son::getParentId, new Dao<Son>() {
//            @Override
//            public List<Son> selectProp(List<Long> idList) {
//                System.out.println(ps.isEmpty());
//                List<Son> ss = new ArrayList<>();
//                Son s1 = new Son();
//                s1.setId(10L);
//                s1.setParentId(1L);
//                ss.add(s1);
//                Son s2 = new Son();
//                s2.setId(20L);
//                s2.setParentId(1L);
//                ss.add(s2);
//                Son s3 = new Son();
//                s3.setId(30L);
//                s3.setParentId(2L);
//                ss.add(s3);
//                Son s4 = new Son();
//                s4.setId(40L);
//                s4.setParentId(1L);
//                ss.add(s4);
//                return ss;
//            }
//        });
//
//        ps.stream().forEach(e -> {
//            System.out.println("pid" + e.getId());
//            if (e.getSons() != null) {
//                e.getSons().stream().forEach(o -> System.out.println("sid" + o.getId()));
//            }
//        });
//    }
}
