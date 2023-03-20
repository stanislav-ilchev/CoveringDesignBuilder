package utils;

import uk.ac.manchester.tornado.api.*;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static utils.Library.*;

public class BruteForceFast {

    public void run() {
        String[] args = new String[1];
        // When using the kernel-parallel API, we need to create a Grid and a Worker

        WorkerGrid workerGrid = new WorkerGrid2D(100, 100);    // Create a 2D Worker
        GridScheduler gridScheduler = new GridScheduler("s0.t0", workerGrid);  // Attach the worker to the Grid
        KernelContext context = new KernelContext();             // Create a context
        workerGrid.setLocalWork(32, 32, 1);                      // Set the local-group size

        TaskGraph taskGraph = new TaskGraph("s0")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION) // Transfer data from host to device only in the first execution
                .task("t0", BruteForceFast::main, args)   // Each task points to an existing Java method
                .transferToHost(DataTransferMode.EVERY_EXECUTION);     // Transfer data from device to host

        // Create an immutable task-graph
        ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();

        // Create an execution plan from an immutable task-graph
        TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph);

        // Execute the execution plan
        executionPlan.withGridScheduler(gridScheduler)
                .execute();
    }

//    public void run() {
//        String[] args = new String[1];
//        TaskGraph taskGraph = new TaskGraph("s0")
//                .transferToDevice(DataTransferMode.FIRST_EXECUTION) // Transfer data from host to device only in the first execution
//                .task("t0", BruteForceFast::main, args)              // Each task points to an existing Java method
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


    public static void main(String[] args) {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j, count = 0;
        int[] wheel = new int[b];
        boolean[][] intersections = new boolean[mSetsCount][kSetsCount];
        long start = System.currentTimeMillis();

        Collections.shuffle(Arrays.asList(mSets));
        Collections.shuffle(Arrays.asList(kSets));
        Random random = new Random();
        for (int I = 0; I < mSetsCount; I++) {
            for (int J = 0; J < kSetsCount; J++) {
                if (intersection(mSets[I], kSets[J]) >= t) {
                    intersections[I][J] = true;
                }
            }
        }
        date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        test:
        while (kSetsCount > 0) {
            count++;
            if (count == 1000000) {
                System.out.println(System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                count = 0;
            }
            for (int I = 0; I < b; I++) {
                wheel[I] = random.nextInt(kSetsCount);
            }
            test2:
            for (int I = 0; I < mSetsCount; I++) {
                for (int J = 0; J < b; J++) {
                    if (intersections[I][wheel[J]]) {
                        continue test2;
                    }
                }
                continue test;
            }
            System.out.println(b);
            try {
                FileWriter fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                for (i = 0; i < b; i++) {
                    for (j = 0; j < k; j++) {
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
            return;
//            b--;
        }
    }
}