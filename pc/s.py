ls = [11, "22", 33, (44, 55), 66, [77, 88]]
s = 0
i = 0
while i:
    if type(ls[i]) == type(int):
        s += ls[i]
        i += 1
        #continue
#t = sum(ls)
print(s)