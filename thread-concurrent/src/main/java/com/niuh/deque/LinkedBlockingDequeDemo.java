package com.niuh.deque;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>
 * LinkedBlockingDeque示例
 * </p>
 */
public class LinkedBlockingDequeDemo {

    public static void main(String[] args) {
        /**
         * 1.1、LinkedBlockingDeque():
         *           创建一个容量为 Integer.MAX_VALUE 的 LinkedBlockingDeque。
         * 1.2、LinkedBlockingDeque(int capacity):
         *           创建一个具有给定（固定）容量的 LinkedBlockingDeque。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();
        /**
         * 1、add(E e):在不违反容量限制的情况下，将指定的元素插入此双端队列的末尾，返回值为Boolean。
         */
        Boolean addBoolean = linkedBlockingDeque.add(5);
        System.out.println("是否添加成功：" + addBoolean);


        /**
         *  2、addFirst(E e)：如果立即可行且不违反容量限制，则将指定的元素插入此双端队列的开头；
         *                   如果当前没有空间可用，则抛出 IllegalStateException。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque1 = new LinkedBlockingDeque<>();
        linkedBlockingDeque1.addFirst(1);
        linkedBlockingDeque1.addFirst(2);
        linkedBlockingDeque1.addFirst(3);


        /**
         * 3、iterator()：返回在此双端队列元素上以恰当顺序进行迭代的迭代器。
         */
        Iterator<Integer> iterator = linkedBlockingDeque1.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator的addFirst结果：" + iterator.next());
        }


        /**
         * 4、addLast(E e) ：如果立即可行且不违反容量限制，则将指定的元素插入此双端队列的末尾；
         *                   如果当前没有空间可用，则抛出 IllegalStateException
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque2 = new LinkedBlockingDeque<>();
        linkedBlockingDeque2.addLast(1);
        linkedBlockingDeque2.addLast(2);
        linkedBlockingDeque2.addLast(3);
        Iterator<Integer> iterator1 = linkedBlockingDeque2.iterator();
        while (iterator1.hasNext()) {
            System.out.println("Iterator的addLast结果：" + iterator1.next());
        }


        /**
         * 5、clear()：以原子方式 (atomically) 从此双端队列移除所有元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque3 = new LinkedBlockingDeque<>();
        linkedBlockingDeque3.add(1);
        linkedBlockingDeque3.add(2);
        linkedBlockingDeque3.add(3);
        linkedBlockingDeque3.clear();
        System.out.println("================");
        Iterator<Integer> iterator2 = linkedBlockingDeque3.iterator();
        while (iterator2.hasNext()) {
            System.out.println("Iterator的clear结果：" + iterator2.next());
        }
        System.out.println("================");

        /**
         * 6、contains(Object o) ：如果此双端队列包含指定的元素，则返回 true
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque4 = new LinkedBlockingDeque<>();
        linkedBlockingDeque4.add(1);
        linkedBlockingDeque4.add(2);
        linkedBlockingDeque4.add(3);
        Boolean contains3Boolean = linkedBlockingDeque4.contains(3);
        Boolean contains4Boolean = linkedBlockingDeque4.contains(4);
        System.out.println("是否包含3：" + contains3Boolean + " 是否包含4：" + contains4Boolean);

        /**
         * 7、element()：获取但不移除此双端队列表示的队列的头部
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque5 = new LinkedBlockingDeque<>();
        linkedBlockingDeque5.add(1);
        linkedBlockingDeque5.add(2);
        linkedBlockingDeque5.add(3);
        Integer elementResult = linkedBlockingDeque5.element();
        System.out.println("队列的头部: " + elementResult);

        /**
         * 8、getFirst() ：获取，但不移除此双端队列的第一个元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque6 = new LinkedBlockingDeque<>();
        linkedBlockingDeque6.add(1);
        linkedBlockingDeque6.add(2);
        linkedBlockingDeque6.add(3);
        Integer firstResult = linkedBlockingDeque6.getFirst();
        System.out.println("双端队列的第一个元素: " + firstResult);


        /**
         * 9、	getLast() :获取，但不移除此双端队列的最后一个元素
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque7 = new LinkedBlockingDeque<>();
        linkedBlockingDeque7.add(3);
        linkedBlockingDeque7.add(4);
        linkedBlockingDeque7.add(5);
        Integer lastResult = linkedBlockingDeque7.getLast();
        System.out.println("双端队列的最后一个元素: " + lastResult);


        /**
         * 10.1、offer(E e) :如果立即可行且不违反容量限制，
         *                  则将指定的元素插入此双端队列表示的队列中（即此双端队列的尾部），
         *                  并在成功时返回 true；如果当前没有空间可用，则返回 false
         *
         * 10.2、offer(E e, long timeout, TimeUnit unit) :
         *                  将指定的元素插入此双端队列表示的队列中（即此双端队列的尾部），
         *                  必要时将在指定的等待时间内一直等待可用空间,返回值为Boolean。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque8 = new LinkedBlockingDeque<>();
        linkedBlockingDeque8.offer(1);
        linkedBlockingDeque8.offer(2);
        linkedBlockingDeque8.offer(3);
        Iterator<Integer> iterator3 = linkedBlockingDeque8.iterator();
        while (iterator3.hasNext()) {
            System.out.println("Iterator的offer结果：" + iterator3.next());
        }


        /**
         * 11.1、offerFirst(E e) ：
         *           如果立即可行且不违反容量限制，则将指定的元素插入此双端队列的开头，
         *           并在成功时返回 true；如果当前没有空间可用，则返回 false。
         * 11.2、fferFirst(E e, long timeout, TimeUnit unit)：
         *           将指定的元素插入此双端队列的开头，必要时将在指定的等待时间内等待可用空间。
         *           返回值为Boolean。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque9 = new LinkedBlockingDeque<>();
        linkedBlockingDeque9.offerFirst(1);
        linkedBlockingDeque9.offerFirst(2);
        linkedBlockingDeque9.offerFirst(3);
        Iterator<Integer> iterator4 = linkedBlockingDeque9.iterator();
        while (iterator4.hasNext()) {
            System.out.println("Iterator的offerFirst结果：" + iterator4.next());
        }


        /**
         * 12.1、offerLast(E e):
         *           如果立即可行且不违反容量限制，则将指定的元素插入此双端队列的末尾，并在成功时返回 true；如果当前没有空间可用，则返回 false。
         * 12.2、offerLast(E e, long timeout, TimeUnit unit):
         *           将指定的元素插入此双端队列的末尾，必要时将在指定的等待时间内等待可用空间。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque10 = new LinkedBlockingDeque<>();
        linkedBlockingDeque10.offerLast(1);
        linkedBlockingDeque10.offerLast(2);
        linkedBlockingDeque10.offerLast(3);
        Iterator<Integer> iterator5 = linkedBlockingDeque10.iterator();
        while (iterator5.hasNext()) {
            System.out.println("Iterator的offerLast结果：" + iterator5.next());
        }


        /**
         * 13、peek():获取但不移除此双端队列表示的队列的头部（即此双端队列的第一个元素）；
         *            如果此双端队列为空，则返回 null
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque11 = new LinkedBlockingDeque<>();
        linkedBlockingDeque11.add(1);
        linkedBlockingDeque11.add(2);
        linkedBlockingDeque11.add(3);
        Integer peekResult = linkedBlockingDeque11.peek();
        System.out.println("peekResult的结果：" + peekResult);

        /**
         * 14、peekFirst():获取，但不移除此双端队列的第一个元素；如果此双端队列为空，则返回 null。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque12 = new LinkedBlockingDeque<>();
        linkedBlockingDeque12.add(3);
        linkedBlockingDeque12.add(4);
        linkedBlockingDeque12.add(5);
        Integer peekFirstResult = linkedBlockingDeque12.peekFirst();
        System.out.println("peekFirstResult的结果：" + peekFirstResult);


        /**
         * 15、peekLast() ：获取，但不移除此双端队列的最后一个元素；如果此双端队列为空，则返回 null。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque13 = new LinkedBlockingDeque<>();
        linkedBlockingDeque13.add(6);
        linkedBlockingDeque13.add(7);
        linkedBlockingDeque13.add(8);
        Integer peekLastResult = linkedBlockingDeque13.peekLast();
        System.out.println("peekLastResult的结果：" + peekLastResult);

        /**
         * 16.1、poll() :获取并移除此双端队列表示的队列的头部（即此双端队列的第一个元素）；
         *            如果此双端队列为空，则返回 null。
         * 16.2、poll(long timeout, TimeUnit unit)：
         *           获取并移除此双端队列表示的队列的头部（即此双端队列的第一个元素），
         *           如有必要将在指定的等待时间内等待可用元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque14 = new LinkedBlockingDeque<>();
        linkedBlockingDeque14.add(9);
        linkedBlockingDeque14.add(10);
        linkedBlockingDeque14.add(11);
        Integer pollResult = linkedBlockingDeque14.poll();
        System.out.println("peekLastResult的结果：" + pollResult);
        System.out.println("linkedBlockingDeque14是否还包含9：" + linkedBlockingDeque14.contains(9));


        /**
         * 17.1、pollFirst() ：
         *           获取并移除此双端队列的第一个元素；如果此双端队列为空，则返回 null。
         * 17.2、pollFirst(long timeout, TimeUnit unit) :
         *           获取并移除此双端队列的第一个元素，必要时将在指定的等待时间等待可用元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque15 = new LinkedBlockingDeque<>();
        linkedBlockingDeque15.addFirst(9);
        linkedBlockingDeque15.addFirst(10);
        linkedBlockingDeque15.addFirst(11);
        Integer pollFirstResult = linkedBlockingDeque15.pollFirst();
        System.out.println("pollFirstResult的结果：" + pollFirstResult);
        System.out.println("linkedBlockingDeque15是否还包含11：" + linkedBlockingDeque15.contains(11));

        /**
         * 18.1、pollLast()
         *           获取并移除此双端队列的最后一个元素；如果此双端队列为空，则返回 null。
         * 18.2、pollLast(long timeout, TimeUnit unit)
         *           获取并移除此双端队列的最后一个元素，必要时将在指定的等待时间内等待可用元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque16 = new LinkedBlockingDeque<>();
        linkedBlockingDeque16.add(9);
        linkedBlockingDeque16.add(10);
        linkedBlockingDeque16.add(11);
        Integer pollLastResult = linkedBlockingDeque16.pollLast();
        System.out.println("pollLastResult的结果：" + pollLastResult);
        System.out.println("linkedBlockingDeque16是否还包含11：" + linkedBlockingDeque16.contains(11));

        /**
         * 19、	pop() :从此双端队列所表示的堆栈中弹出一个元素（移除效果）
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque17 = new LinkedBlockingDeque<>();
        linkedBlockingDeque17.addFirst(1);
        linkedBlockingDeque17.addFirst(2);
        linkedBlockingDeque17.addFirst(3);
        Integer pop1Result = linkedBlockingDeque17.pop();
        System.out.println("pop2Result的结果：" + pop1Result);
        Integer pop2Result = linkedBlockingDeque17.pop();
        System.out.println("pop2Result的结果：" + pop2Result);
        System.out.println("linkedBlockingDeque17是否还包含2：" + linkedBlockingDeque17.contains(2));

        /**
         * 20、push(E e) ：将元素推入此双端队列表示的栈。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque18 = new LinkedBlockingDeque<>();
        linkedBlockingDeque18.push(1);
        linkedBlockingDeque18.push(2);
        linkedBlockingDeque18.push(3);
        Iterator<Integer> iterator6 = linkedBlockingDeque18.iterator();
        while (iterator6.hasNext()) {
            System.out.println("Iterator的push结果：" + iterator6.next());
        }

        /**
         * 21、put(E e) :将指定的元素插入此双端队列表示的队列中（即此双端队列的尾部），
         *               必要时将一直等待可用空间。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque19 = new LinkedBlockingDeque<>();
        try {
            linkedBlockingDeque19.put(1);
            linkedBlockingDeque19.put(2);
            linkedBlockingDeque19.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Integer> iterator7 = linkedBlockingDeque19.iterator();
        while (iterator7.hasNext()) {
            System.out.println("Iterator的put结果：" + iterator7.next());
        }

        /**
         * 22、putFirst(E e) :将指定的元素插入此双端队列的开头，必要时将一直等待可用空间。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque20 = new LinkedBlockingDeque<>();
        try {
            linkedBlockingDeque20.putFirst(1);
            linkedBlockingDeque20.putFirst(2);
            linkedBlockingDeque20.putFirst(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Integer> iterator8 = linkedBlockingDeque20.iterator();
        while (iterator8.hasNext()) {
            System.out.println("Iterator的putFirst结果：" + iterator8.next());
        }

        /**
         * 23、putLast(E e) :将指定的元素插入此双端队列的末尾，必要时将一直等待可用空间。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque21 = new LinkedBlockingDeque<>();
        try {
            linkedBlockingDeque21.putLast(1);
            linkedBlockingDeque21.putLast(2);
            linkedBlockingDeque21.putLast(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Integer> iterator9 = linkedBlockingDeque21.iterator();
        while (iterator9.hasNext()) {
            System.out.println("Iterator的putLast结果：" + iterator9.next());
        }


        /**
         * 24、remove():获取并移除此双端队列表示的队列的头部。返回一个E
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque22 = new LinkedBlockingDeque<>();
        linkedBlockingDeque22.addFirst(1);
        linkedBlockingDeque22.addFirst(2);
        linkedBlockingDeque22.addFirst(3);
        Integer removeResult = linkedBlockingDeque22.remove();
        System.out.println("removeResult的结果：" + removeResult);
        System.out.println("linkedBlockingDeque22是否还包含3：" + linkedBlockingDeque22.contains(3));


        /**
         * 25、remove(Object o) :从此双端队列移除第一次出现的指定元素,返回值为Boolean。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque23 = new LinkedBlockingDeque<>();
        linkedBlockingDeque23.addFirst(1);
        linkedBlockingDeque23.addFirst(2);
        linkedBlockingDeque23.addFirst(3);
        Boolean removeBoolean = linkedBlockingDeque23.remove(3);
        System.out.println("是否remove了3 ：" + removeBoolean);

        /**
         * 26、removeFirst():获取并移除此双端队列第一个元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque24 = new LinkedBlockingDeque<>();
        linkedBlockingDeque24.addLast(1);
        linkedBlockingDeque24.addLast(2);
        linkedBlockingDeque24.addLast(3);
        Integer removeFirstResult = linkedBlockingDeque24.removeFirst();
        System.out.println("removeFirstResult：" + removeFirstResult);
        System.out.println("linkedBlockingDeque24是否还包含1：" + linkedBlockingDeque24.contains(1));


        /**
         * 27、	removeLast():获取并移除此双端队列的最后一个元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque25 = new LinkedBlockingDeque<>();
        linkedBlockingDeque25.addLast(4);
        linkedBlockingDeque25.addLast(5);
        linkedBlockingDeque25.addLast(6);
        Integer removeLastResult = linkedBlockingDeque25.removeLast();
        System.out.println("removeLastResult：" + removeLastResult);
        System.out.println("linkedBlockingDeque25是否还包含6：" + linkedBlockingDeque25.contains(6));


        /**
         * 28、take():获取并移除此双端队列表示的队列的头部（即此双端队列的第一个元素），
         *           必要时将一直等待可用元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque26 = new LinkedBlockingDeque<>();
        linkedBlockingDeque26.push(4);
        linkedBlockingDeque26.push(5);
        linkedBlockingDeque26.push(6);
        Integer takeResult = null;
        try {
            takeResult = linkedBlockingDeque26.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("takeResult：" + takeResult);
        System.out.println("linkedBlockingDeque26是否还包含6：" + linkedBlockingDeque26.contains(6));

        /**
         * 29、takeFirst() :获取并移除此双端队列的第一个元素，必要时将一直等待可用元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque27 = new LinkedBlockingDeque<>();
        linkedBlockingDeque27.push(7);
        linkedBlockingDeque27.push(8);
        linkedBlockingDeque27.push(9);
        Integer takeFirstResult = null;
        try {
            takeFirstResult = linkedBlockingDeque27.takeFirst();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("takeFirst：" + takeFirstResult);
        System.out.println("linkedBlockingDeque27是否还包含9：" + linkedBlockingDeque27.contains(9));


        /**
         * 30、takeLast():获取并移除此双端队列的最后一个元素，必要时将一直等待可用元素。
         */
        LinkedBlockingDeque<Integer> linkedBlockingDeque28 = new LinkedBlockingDeque<>();
        linkedBlockingDeque28.push(10);
        linkedBlockingDeque28.push(11);
        linkedBlockingDeque28.push(12);
        Integer takeLastResult = null;
        try {
            takeLastResult = linkedBlockingDeque28.takeLast();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("takeLastResult：" + takeLastResult);
        System.out.println("linkedBlockingDeque28是否还包含10：" + linkedBlockingDeque28.contains(10));


    }
}


