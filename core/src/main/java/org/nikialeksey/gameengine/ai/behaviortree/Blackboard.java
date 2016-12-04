/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexey Nikitin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.nikialeksey.gameengine.ai.behaviortree;

import java.util.*;

/**
 * Класс представляет структуру памяти для наших персонажей, которую будут использовать вершины дерева поведения.
 * Информация, хранящаяся в {@code Blackboard} структурирована следующим образом: глобальная информация(доступная
 * из любого места), информация о дереве (досутпная для всех вершин одного дерева), информация о вершине (доступная
 * только вершине).
 *
 * Память для простототы можно изобразить в виде JSON документа:
 * <pre>
 * {
 *     'global key 1': globalObject1,                    #
 *     'global key 2': globalObject2,                    #  глобальная информация
 *     ...                                               #
 *
 *     'tree1UUID': {
 *         'tree key 1': treeObject1,                    #
 *         'tree key 2': treeObject2,                    #  информация, доступная для всех вершин одного дерева
 *         ...                                           #
 *
 *         'nodeMemory': {
 *             'node key 1': nodeObject1,                #
 *             'node key 2': nodeObject2,                #  информация, доступная для одной вершины
 *             ...                                       #
 *         },
 *         'openNodes': [
 *             ...
 *         ]
 *     },
 *     'tree2UUID': {
 *          ...
 *     },
 *     ...
 * }
 * </pre>
 *
 * @author Alexey Nikitin
 */
public class Blackboard {
    private BlackboardMemory baseMemory;
    private Map<String, TreeMemory> treeMemory;

    /**
     * Конструктор.
     * Инициализирует память.
     */
    public Blackboard() {
        baseMemory = new BlackboardMemory();
        treeMemory = new HashMap<String, TreeMemory>();
    }

    private BlackboardMemory getMemory() {
        return baseMemory;
    }

    private TreeMemory getMemory(String treeUUID) {
        if (!treeMemory.containsKey(treeUUID))
            treeMemory.put(treeUUID, new TreeMemory());
        return treeMemory.get(treeUUID);
    }

    private BlackboardMemory getMemory(String treeUUID, String nodeUUID) {
        return getMemory(treeUUID).getMemory(nodeUUID);
    }

    /**
     * Возвращает объект из глобальной памяти.
     * @param key ключ
     * @return объект из глобальной памяти
     */
    public Object get(String key) {
        return this.getMemory().get(key);
    }

    /**
     * Возвращает объект из памяти дерева с идентификатором {@code treeUUID}.
     * @param key ключ
     * @param treeUUID уникальный идентификатор дерева
     * @return объект из памяти дерева с идентификатором {@code treeUUID}.
     */
    public Object get(String key, String treeUUID) {
        return getMemory(treeUUID).get(key);
    }

    /**
     * Возвращает объект из вершины дерева.
     * @param key ключ
     * @param treeUUID уникальный идентификатор дерева
     * @param nodeUUID уникальный идентификатор вершины
     * @return объект из вершины дерева
     */
    public Object get(String key, String treeUUID, String nodeUUID) {
        return getMemory(treeUUID, nodeUUID).get(key);
    }

    /**
     * Кладет объект в глобальную память.
     * @param key ключ
     * @param value объект
     */
    public void put(String key, Object value) {
        getMemory().put(key, value);
    }

    /**
     * Кладет объект в память для дерева.
     * @param key ключ
     * @param value объект
     * @param treeUUID уникальный идентмификатор дерева
     */
    public void put(String key, Object value, String treeUUID) {
        getMemory(treeUUID).put(key, value);
    }

    /**
     * Кладет объект в память вершины.
     * @param key ключ
     * @param value объект
     * @param treeUUID уникальный идентификатор дерева
     * @param nodeUUID уникальный идентификатор вершины
     */
    public void put(String key, Object value, String treeUUID, String nodeUUID) {
        getMemory(treeUUID, nodeUUID).put(key, value);
    }

    /**
     * Класс представляет память дерева.
     */
    public static class TreeMemory {
        private BlackboardMemory localMemory;
        private Map<String, BlackboardMemory> nodeMemory;
        private ArrayList<Object> openNodes;

        public TreeMemory() {
            localMemory = new BlackboardMemory();
            nodeMemory = new HashMap<String, BlackboardMemory>();
            openNodes = new ArrayList<Object>();
        }

        private BlackboardMemory getMemory() {
            return localMemory;
        }

        private BlackboardMemory getMemory(String nodeUUID) {
            if (!nodeMemory.containsKey(nodeUUID))
                nodeMemory.put(nodeUUID, new BlackboardMemory());
            return nodeMemory.get(nodeUUID);
        }

        /**
         * Возвращает объект дерева.
         * @param key ключ
         * @return объект дерева
         */
        public Object get(String key) {
            return getMemory().get(key);
        }

        /**
         * Возвращает объект из вершины данного дерева.
         * @param key ключ
         * @param nodeUUID уникальный идентификатор вершины
         * @return объект из вершины данного дерева
         */
        public Object get(String key, String nodeUUID) {
            return getMemory(nodeUUID).get(key);
        }

        /**
         * Кладет объект в память дерева.
         * @param key ключ
         * @param value объект
         */
        public void put(String key, Object value) {
            getMemory().put(key, value);
        }

        /**
         * Кладет объект в память вершины.
         * @param key ключ
         * @param value объект
         * @param nodeUUID уникальный идентификатор вершины
         */
        public void put(String key, Object value, String nodeUUID) {
            getMemory(nodeUUID).put(key, value);
        }
    }

    /**
     * Класс представляет память.
     */
    public static class BlackboardMemory implements Map<String, Object> {
        private HashMap<String, Object> memory;

        public BlackboardMemory() {
            memory = new HashMap<String, Object>();
        }

        @Override
        public int size() {
            return memory.size();
        }

        @Override
        public boolean isEmpty() {
            return memory.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return memory.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return memory.containsValue(value);
        }

        @Override
        public Object get(Object key) {
            return memory.get(key);
        }

        @Override
        public Object put(String key, Object value) {
            return memory.put(key, value);
        }

        @Override
        public Object remove(Object key) {
            return memory.remove(key);
        }

        @Override
        public void putAll(Map<? extends String, ?> m) {
            memory.putAll(m);
        }

        @Override
        public void clear() {
            memory.clear();
        }

        @Override
        public Set<String> keySet() {
            return memory.keySet();
        }

        @Override
        public Collection<Object> values() {
            return memory.values();
        }

        @Override
        public Set<Entry<String, Object>> entrySet() {
            return memory.entrySet();
        }
    }

}
