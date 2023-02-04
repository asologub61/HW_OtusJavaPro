# The tables with mark of working time of different Heap size and GC type

## Table 1. Before optimization.

| HeapSize         |       GarbageCollectorType        |              WorkedTime               |
|------------------|:---------------------------------:|:-------------------------------------:|
| 256mb            |             Serial GC             |              OutOfMemory              |
| 512 mb           |             Serial GC             |       spend msec:28719, sec:28        |
| 1024 mb          |             Serial GC             |       spend msec:20332, sec:20        |
| 2048 mb          |             Serial GC             |       spend msec:18432, sec:18        |
| ---------------- | --------------------------------- | ------------------------------------- |
| 256mb            |            Parallel GC            |              OutOFMemory              |
| 512 mb           |            Parallel GC            |       spend msec:45440, sec:45        |
| 1024 mb          |            Parallel GC            |       spend msec:31239, sec:31        |
| 2048 mb          |            Parallel GC            |       spend msec:27683, sec:27        |
| ---------------- | --------------------------------- | ------------------------------------- |
| 256 mb           |               G1 GC               |              OutOFMemory              |
| 512 mb           |               G1 GC               |       spend msec:27325, sec:27        |
| 1024 mb          |               G1 GC               |       spend msec:23617, sec:23        |
| 2048 mb          |               G1 GC               |       spend msec:26146, sec:26        |
| ---------------- | --------------------------------- | ------------------------------------- |
| 256 mb           |               Z GC                |              OutOfMemory              |
| 512 mb           |               Z GC                |       spend msec:48347, sec:48        |
| 1024 mb          |               Z GC                |       spend msec:18707, sec:18        |
| 2048 mb          |               Z GC                |       spend msec:18129, sec:18        |

 Here we can see that more likely choice is **_512mb Z GC_**

## Table 2. After optimization.

| HeapSize         |       GarbageCollectorType        |              WorkedTime               |
|------------------|:---------------------------------:|:-------------------------------------:|
| 256mb            |             Serial GC             |        spend msec:3447, sec:3         |
| 512 mb           |             Serial GC             |        spend msec:3414, sec:3         |
| 1024 mb          |             Serial GC             |        spend msec:3316, sec:3         |
| 2048 mb          |             Serial GC             |        spend msec:3962, sec:3         |
| ---------------- | --------------------------------- | ------------------------------------- |
| 256mb            |            Parallel GC            |        spend msec:3617, sec:3         |
| 512 mb           |            Parallel GC            |        spend msec:3288, sec:3         |
| 1024 mb          |            Parallel GC            |        spend msec:3345, sec:3         |
| 2048 mb          |            Parallel GC            |        spend msec:3903, sec:3         |
| ---------------- | --------------------------------- | ------------------------------------- |
| 256 mb           |               G1 GC               |        spend msec:3174, sec:3         |
| 512 mb           |               G1 GC               |        spend msec:3334, sec:3         |
| 1024 mb          |               G1 GC               |        spend msec:3709, sec:3         |
| 2048 mb          |               G1 GC               |        spend msec:4208, sec:4         |
| ---------------- | --------------------------------- | ------------------------------------- |
| 256 mb           |               Z GC                |        spend msec:2852, sec:2         |
| 512 mb           |               Z GC                |        spend msec:2906, sec:2         |
| 1024 mb          |               Z GC                |        spend msec:3305, sec:3         |
| 2048 mb          |               Z GC                |        spend msec:4235, sec:4         |

Here we can see that more likely choice is **_256mb Z GC_** 