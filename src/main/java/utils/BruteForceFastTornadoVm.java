package utils;

import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static utils.Library.*;
import static utils.Library.random;

public class BruteForceFastTornadoVm {

//    public void run() {
//        String[] args = new String[1];
//        // When using the kernel-parallel API, we need to create a Grid and a Worker
//
//        WorkerGrid workerGrid = new WorkerGrid2D(100, 100);    // Create a 2D Worker
//        GridScheduler gridScheduler = new GridScheduler("s0.t0", workerGrid);  // Attach the worker to the Grid
//        KernelContext context = new KernelContext();             // Create a context
//        workerGrid.setLocalWork(32, 32, 1);                      // Set the local-group size
//
//        TaskGraph taskGraph = new TaskGraph("s0")
//                .transferToDevice(DataTransferMode.FIRST_EXECUTION) // Transfer data from host to device only in the first execution
//                .task("t0", BruteForceFast::main, args)   // Each task points to an existing Java method
//                .transferToHost(DataTransferMode.EVERY_EXECUTION);     // Transfer data from device to host
//
//        // Create an immutable task-graph
//        ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();
//
//        // Create an execution plan from an immutable task-graph
//        TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph);
//
//        // Execute the execution plan
//        executionPlan.withGridScheduler(gridScheduler)
//                .execute();
//    }

//    public static void run() {
//        String[] args = new String[1];
//        TaskGraph taskGraph = new TaskGraph("s0")
//                .transferToDevice(DataTransferMode.FIRST_EXECUTION) // Transfer data from host to device only in the first execution
//                .task("t0", BruteForceFastTornadoVm::bfs, args)              // Each task points to an existing Java method
//                .transferToHost(DataTransferMode.EVERY_EXECUTION);     // Transfer data from device to host
//        // Create an immutable task-graph
//        ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();
//
//        // Create an execution plan from an immutable task-graph
//        TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph);
//
//        // Execute the execution plan
//        TornadoExecutionResult executionResult = executionPlan.execute();
//    }

    public static int[] initializeWheel(int[] wheel) {
        for (@Parallel int I = 0; I < b; I++) {
            wheel[I] = random.nextInt(kSetsCount);
        }
        return wheel;
    }

    public static void checkWheel(int[] wheel, boolean[][] intersections) {
        test2:
        for (@Parallel int I = 0; I < mSetsCount; I++) {
            for (@Parallel int J = 0; J < b; J++) {
                if (intersections[I][wheel[J]]) {
                    continue test2;
                }
            }
            return;
        }
        System.out.println(b);
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
            fileWriter.flush();
            for (int i = 0; i < b; i++) {
                for (int j = 0; j < k; j++) {
//                    System.out.print(kSets[wheel[i]][j] + " ");
                    fileWriter.append(kSets[wheel[i]][j] + " ");
                }
//                System.out.println();
                fileWriter.append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j, count = 0;
        int[] wheel = new int[b];
        boolean[][] intersections = new boolean[mSetsCount][kSetsCount];
        long start = System.currentTimeMillis();

        Collections.shuffle(Arrays.asList(mSets));
        Collections.shuffle(Arrays.asList(kSets));
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                if (intersection(mSets[i], kSets[j]) >= t) {
                    intersections[i][j] = true;
                }
            }
        }
        TaskGraph taskGraph;
        while (true) {
            count++;
            if (count == 1000000) {
                System.out.println(System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                count = 0;
            }

            taskGraph = new TaskGraph("s0")
                    .transferToDevice(DataTransferMode.EVERY_EXECUTION, wheel, intersections)
                    .task("t0", BruteForceFastTornadoVm::initializeWheel, wheel)
                    .task("t1", BruteForceFastTornadoVm::checkWheel, wheel, intersections)
                    .transferToHost(DataTransferMode.EVERY_EXECUTION, wheel, intersections);

            TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(taskGraph.snapshot());
            executionPlan.execute();
        }
    }
}