import matplotlib.pyplot as plt
import random


def quick(arr):
    _quick(arr, 0, len(arr) - 1)


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

def average(income):
    sum = 0
    for i in income:
        sum += i
    return sum / len(income) 

def median(income):
    data = income
    quick(data)
    n = len(data)
    if n % 2 == 0:
        return (data[n // 2 - 1] + data[n // 2]) / 2
    else:
        return data[n // 2]
    
def mode(income):
    # 创建一个空字典用于统计每个元素的出现次数
    d = {}
    
    # 遍历数据集，统计每个元素的出现次数
    for num in income:
        if num in d:
            d[num] += 1
        else:
            d[num] = 1
    
    # 找到元素出现的最多次数
    max_count = max(d.values())
    # 通过出现的次数(值)count和最多次数比较，相等时把(键)num存入mode列表
    mode = [num for num, count in d.items() if count == max_count]
    
    return mode

# 初始化数据点和结果列表
x_values = []
averages = []
medians = []
modes = []

# 逐步增加数据量，并计算统计量
for i in range(100000, 200001, 10000):
    income = [random.randint(1000, 100000) for _ in range(i)]
    x_values.append(i)
    averages.append(average(income))
    medians.append(median(income))
    modes.append(mode(income))  

# 绘制折线图
plt.plot(x_values, averages, label='Average', marker='o', linestyle='-')
plt.plot(x_values, medians, label='Median', marker='o', linestyle='-')
# 添加所有众数到 modes 列表
i = 0
for m in modes:
    i += 1
    data = []
    for j in range(0, 11):
        if j >= len(m): data.append(0)
        else: data.append(m[j])

    plt.plot(x_values, data, label=f'Mode{i}', marker='o', markerfacecolor='none', linestyle='--')

# 添加标题和标签
plt.title('Income Statistics')
plt.xlabel('Population')
plt.ylabel('Income')
# 显示网格线
plt.grid(True)
# 添加图例
plt.legend()
# 显示图形
plt.show()
