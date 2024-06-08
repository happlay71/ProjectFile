import matplotlib.pyplot as plt
import random
import time

def bubble(arr):
    start = time.time()
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    end = time.time()
    elapsed_time = end - start
    return elapsed_time

def insert(arr):
    start = time.time()
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    end = time.time()
    elapsed_time = end - start
    return elapsed_time

def quick(arr):
    start = time.time()
    _quick(arr, 0, len(arr) - 1)
    end = time.time()
    elapsed_time = end - start
    return elapsed_time

def _quick(arr, low, high):
    if low < high:
        p = partition(arr, low, high)
        _quick(arr, low, p - 1)
        _quick(arr, p + 1, high)

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def select(arr):
    start = time.time()
    n = len(arr)
    for i in range(n):
        min_index = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index], arr[i]
    end = time.time()
    elapsed_time = end - start
    return elapsed_time

# 生成10万个随机整数
data = [random.randint(10000, 1000000) for _ in range(10000)]

# 创建空列表用于存储排序耗时
bubble_time = []
insert_times = []
quick_times = []
select_times = []

# 逐步增加数据量并计算排序耗时
for i in range(1000, 10000, 1000):
    bubble_time.append(bubble(data[:i]))
    insert_times.append(insert(data[:i]))
    quick_times.append(quick(data[:i]))
    select_times.append(select(data[:i]))

# 创建 x 轴数据（数据量）
# x_values = range(10000, 100000, 10000)
x_values = range(100000, 1000000, 100000)

# 创建折线图, 每个数据实心标记，实线相连
# plt.plot(x_values, bubble_time, label='Bubble Sort', marker='o', linestyle='-')
plt.plot(x_values, insert_times, label='Insert Sort', marker='o', linestyle='-')
plt.plot(x_values, quick_times, label='Quick Sort', marker='o', linestyle='-')
plt.plot(x_values, select_times, label='Select Sort', marker='o', linestyle='-')

# 添加标题和标签
plt.title('Example Line Chart')
plt.xlabel('data')
plt.ylabel('time')
# 显示网格线
plt.grid(True)
# 添加图例
plt.legend()
# 显示图形
plt.show()

