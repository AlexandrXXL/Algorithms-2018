package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.Element;
import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;
        Node<T> right = null;
        Node<T> parent = null;
        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;
    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
            newNode.parent = closest;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
            newNode.parent = closest;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        throw new NotImplementedError();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        // затраты по времени o(log(n)) в среднем O(n) в худшем случае
        // затраты по ресурсам O(1)
        private Node<T> findNext() {
            Node<T> cur = current;
            if (cur == null)
                return find(first());
            if (cur == root && cur.right == null)
                return null;
            if (cur.right != null) {
                cur = cur.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                return cur;
            }
            if (cur == cur.parent.left)
                return cur.parent;
            else {
                while (cur == cur.parent.right) {
                    if (cur.parent == root)
                        return null;
                    else cur = cur.parent;
                }
            }
            return cur.parent;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        // затраты по времени o(log(n)) в среднем и O(n) в худшем варианте
        // затраты по ресурсам O(1)
        public void remove() {
            Node<T> n = null;
            if (current == null)
                return;
            if (current.right == null && current.left == null) {
                if (current == root) {
                    root = null;
                    current = null;
                    size = 0;
                    return;
                }
                else {
                    n = current.parent;
                    if (current.parent.right == current)
                        current.parent.right = null;
                    if (current.parent.left == current)
                        current.parent.left = null;
                    current.parent = null;
                    size--;
                    current = n;
                    return;
                }
            }
            if (current.right == null && current.left != null) {
                if (current == root) {
                    n = current.left;
                    n.parent = null;
                    root = n;
                    current.left = null;
                    size--;
                    current = n;
                    return;
                }
                else {
                    n = current.left;
                    if (current.parent.right == current)
                        current.parent.right = n;
                    if (current.parent.left == current)
                        current.parent.left = n;
                    n.parent = current.parent;
                    current.parent = null;
                    current.left = null;
                    size--;
                    current = n;
                    return;
                }
            }
            if (current.right != null && current.right.left == null) {
                if (current == root) {
                    n = current.right;
                    n.parent = null;
                    n.left = current.left;
                    if (current.left != null)
                        current.left.parent = n;
                    root = n;
                    current.left = null;
                    current.right = null;
                    size--;
                    current = n;
                    return;
                } else {
                    n = current.right;
                    if (current.parent.right == current)
                        current.parent.right = n;
                    if (current.parent.left == current)
                        current.parent.left = n;
                    if (current.left != null) {
                        current.left.parent = n;
                    }
                    n.parent = current.parent;
                    n.left = current.left;
                    current.left = null;
                    current.parent = null;
                    current.right = null;
                    size--;
                    current = n;
                    return;
                }
            }
            if (current.right != null && current.right.left != null) {
                n = current.right;
                while (n.left != null) {
                    n = n.left;
                }
                if (n.right != null) {
                    n.parent.left = n.right;
                    n.right.parent = n.parent;
                }
                else n.parent.left = null;
                if (current == root) {
                    n.parent = null;
                    n.right = root.right;
                    n.left = root.left;
                    n.right.parent = n;
                    if (n.left != null)
                        n.left.parent = n;
                    root = n;
                    current = root;
                    size--;
                    return;
                }
                else {
                    if (current.parent.right == current)
                        current.parent.right = n;
                    else current.parent.left = n;
                    n.parent = current.parent;
                    n.left = current.left;
                    n.right = current.right;
                    n.right.parent = n;
                    if (n.left != null)
                        n.left.parent = n;
                    current = n;
                    size--;
                }
            }
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
