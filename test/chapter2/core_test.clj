
(ns chapter2.core-test
  (:require [clojure.test :refer :all]
            [chapter2.core :refer :all]))

(defn clear-tasks-fixture [f] (swap! tasks empty) (f))
(use-fixtures :each clear-tasks-fixture)

(deftest test-get-task-lists
  (testing "No task lists."
    (is (empty? (get-task-lists))))
  (testing "Creating task lists."
    (add-task "list1" "task1")
    (is (= ["list1"] (get-task-lists)))
    (add-task "list2" "task2")
    (is (= ["list1" "list2"] (get-task-lists)))))

(deftest test-get-tasks
  (testing "Empty task list"
    (is (empty? (get-tasks "list1"))))
  (testing "Non-empty task lists."
    (add-task "list1" "task1")
    (is (= 1 (count (get-tasks "list1"))))
    (is (= "task1" (:task (second (first (get-tasks "list1"))))))
    (add-task "list1" "task2")
    (is (= 2 (count (get-tasks "list1")))))
  (testing "Duplicate tasks are not allowed"
    (add-task "list2" "task1")
    (is (= 1 (count (get-tasks "list2"))))
    (add-task "list2" "task1")
    (is (= 1 (count (get-tasks "list2"))))
    (add-task "list2" "task1")
    (is (= 1 (count (get-tasks "list2"))))))
