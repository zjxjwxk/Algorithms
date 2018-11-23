package bth004.lesson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 在一段时间内寻找数量最多的时间不重复的任务
 * @author zjxjwxk
 */
public class ChooseTask {

    public static List<Object[]> findMostTaskList(List<Object[]> taskList) {
        int len = taskList.size();
        List<Object[]> resultList = new ArrayList<>();

        Comparator<Object[]> comparator = Comparator.comparingDouble(o -> (Double) o[2]);
        taskList.sort(comparator);

        // find the earliest end task from the remaining tasks
        for (Object[] task :
                taskList) {
            if (resultList.size() == 0) {
                resultList.add(task);
            } else {
                if ((Double) resultList.get(resultList.size() - 1)[2] <= (Double) task[1]) {
                    resultList.add(task);
                }
            }
        }

        return resultList;
    }


    public static void printTaskList(List<Object[]> taskList) {
        for (Object[] task :
                taskList) {
            System.out.println(task[0] + " " + task[1] + " " + task[2]);
        }
    }

    public static void main(String[] args) {
        List<Object[]> taskList = new ArrayList<>();
        taskList.add(new Object[]{"a", 0.0, 2.0});
        taskList.add(new Object[]{"b", 3.0, 5.0});
        taskList.add(new Object[]{"c", 5.5, 7.0});
        taskList.add(new Object[]{"d", 1.0, 3.0});
        taskList.add(new Object[]{"e", 4.0, 6.0});
        taskList.add(new Object[]{"f", 6.5, 8.0});
        taskList.add(new Object[]{"g", 1.0, 3.0});
        taskList.add(new Object[]{"h", -1.0, 0.5});
        List<Object[]> resultList = findMostTaskList(taskList);
        printTaskList(resultList);
    }
}
