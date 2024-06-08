import numpy as np  # 计算指数分布，正态分布
from collections import deque  # 先进先出的队
import matplotlib.pyplot as plt


work_hours = 8 * 60  # 工作时长，单位：分钟
nums = 100  # 模拟次数
customers_all_days = 0  # 一天中的客户数
wait_time_all_days = 0  # 一天中客户等待的时长

def simulate():
    arrivals = np.random.exponential(1, size=work_hours)  # 生成顾客到来时间间隔，用数组表示
    arrivals = np.cumsum(arrivals)  # 转换为顾客到达时刻，cumsum方法将该数组转换为前方所有数的累加和的数组
    total_customers = 0  # 每天的客户数
    total_wait_time = 0  # 客户的总等待时间
    current_time = 0  # 服务结束时间
    queue = deque()  # python标准库collections中的双向队列

    for arrival in arrivals:
        if current_time > work_hours:
            break
        service_time = max(1, np.random.normal(10, 2))  # 生成顾客服务时间，至少1分钟
        if not queue:
            start_service_time = max(arrival, current_time)
            end_service_time = start_service_time + service_time
            total_customers += 1
            total_wait_time += (start_service_time - arrival)
            current_time = end_service_time
        else:
            queue.append((arrival, service_time))

        # 如果下一位客户到来时刻小于现客户服务结束时间
        while queue and queue[0][0] <= current_time and current_time <= work_hours:
            arrival, service_time = queue.popleft()
            start_service_time = max(arrival, current_time)
            end_service_time = start_service_time + service_time
            total_customers += 1
            total_wait_time += (start_service_time - arrival)  # 需要等待的时间为本次服务的结束时间-下一位客户到来时刻
            current_time = end_service_time

    return total_customers, total_wait_time


for _ in range(nums):
    total_customers, total_wait_time = simulate()
    customers_all_days += total_customers
    wait_time_all_days += total_wait_time
    print("每日接待客户数：", total_customers)
    print("每日客户平均等待时长：", total_wait_time / total_customers, "分钟")

avg_customers = customers_all_days // nums
avg_wait_time = wait_time_all_days / nums / avg_customers

print("每日平均接待客户数：", avg_customers)
print("每日平均客户等待时长：", avg_wait_time, "分钟")

# 进行模拟，获取每日数据
daily_customers = []
daily_wait_time = []
for _ in range(nums):
    total_customers, total_wait_time = simulate()
    daily_customers.append(total_customers)
    daily_wait_time.append(total_wait_time / total_customers)

# 横坐标：每日模拟次数
x = range(1, nums + 1)

# 绘制每日接待客户数的折线图
plt.plot(x, daily_customers, label='Daily Customers')
plt.xlabel('Days')
plt.ylabel('Number of Customers')
plt.title('Daily Customers in Simulation')
plt.legend()
plt.show()

# 绘制每日客户平均等待时长的折线图
plt.plot(x, daily_wait_time, label='Average Wait Time')
plt.xlabel('Days')
plt.ylabel('Average Wait Time (minutes)')
plt.title('Daily Average Wait Time in Simulation')
plt.legend()
plt.show()
