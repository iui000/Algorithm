package GinkgoStack.P16_UnionFindSet;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class Problem_14_UnionFind {

    /**
     * 用这个范型类，
     * @param <V>
     */
	public static class Element<V> {
		public V value;

		public Element(V value) {
			this.value = value;
		}

	}

	public static class UnionFindSet<V> {

		public HashMap<V, Element<V>> elementMap;//所有元素的集合
		public HashMap<Element<V>, Element<V>> fatherMap;//记录所有节点的父节点
		public HashMap<Element<V>, Integer> rankMap;//秩，表示的是它所代表的集合的元素个数

		public UnionFindSet(List<V> list) {
			elementMap = new HashMap<>();
			fatherMap = new HashMap<>();
			rankMap = new HashMap<>();

			//初始化
			for (V value : list) {
				Element<V> element = new Element<V>(value);
				elementMap.put(value, element);
				fatherMap.put(element, element);
				rankMap.put(element, 1);
			}
		}

        /**
         * 找到一个节点所在集合的代表节点
         * @param element
         * @return
         */
		private Element<V> findHead(Element<V> element) {
			Stack<Element<V>> path = new Stack<>();//用来存此节点到代表节点的路径
			while (element != fatherMap.get(element)) {//如果父节点不是自己，那么它就不是代表节点
				path.push(element);///加入路径
				element = fatherMap.get(element);//继续往上找
			}
			while (!path.isEmpty()) {//重要的优化，将路径上的所有节点的父节点都设置成代表节点，形成菊花状的结构，像烟花绽放
				fatherMap.put(path.pop(), element);
			}
			return element;
		}

        /**
         * 判断两个元素是不是在同一集合中
         * @param a
         * @param b
         * @return
         */
		public boolean isSameSet(V a, V b) {
			if (elementMap.containsKey(a) && elementMap.containsKey(b)) {//首先要确定它们都处于总的元素集合
				return findHead(elementMap.get(a)) == findHead(elementMap.get(b));//它们俩的代表节点相同，则处于同一集合
			}
			return false;
		}

        /**
         * 合并两个集合
         * @param a 表示a所在的集合
         * @param b 表示b所在的集合
         */
		public void union(V a, V b) {
			if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
			    //找到他们俩的代表节点
				Element<V> aF = findHead(elementMap.get(a));
				Element<V> bF = findHead(elementMap.get(b));
				if (aF != bF) {//如果不是同一个代表
				    //将秩较小的那个集合 合并到 秩较大的那个集合
					Element<V> big = rankMap.get(aF) >= rankMap.get(bF) ? aF : bF;
					Element<V> small = big == aF ? bF : aF;
					fatherMap.put(small, big);//新的代表节点是 原来秩较大的那个代表节点
					rankMap.put(big, rankMap.get(aF) + rankMap.get(bF));//新的秩就是原来秩之和
					rankMap.remove(small);//原来较小秩的代表节点便不再是代表了
				}
			}
		}

	}

}
