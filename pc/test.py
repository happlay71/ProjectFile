nums = [1,1,2]
stack = []
n = nums[0]
stack.append(n)
for i in range(len(nums)):
    if n == nums[i]:
        continue
    else:
        n = nums[i]
        stack.append(n)
print(stack)
print(len(stack))