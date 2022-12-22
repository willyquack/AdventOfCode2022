import re

def main():
    switch = 1
    if switch == 0:
        f = open("./small.txt")
        num_cols = 3
    else:
        f = open("./large.txt")
        num_cols = 9
    cur_state = []
    for x in range(num_cols):
        cur_state.append([])
    line = f.readline()
    while line[1] != '1':
        for i in range(num_cols):
            if line[(i*4) + 1] != ' ':
                cur_state[i].insert(0,line[(i*4) + 1])
        line = f.readline()
    all_moves = f.readlines()
    for move in all_moves:
        # print(cur_state)
        if move == '\n':
            continue
        nums = [int(s) for s in re.findall(r'-?\d+\.?\d*',move)]
        amt = nums[0]
        src = nums[1]
        dest = nums[2]
        temp = []
        for x in range(amt):
            temp.append(cur_state[src - 1].pop())
        print(temp)
        for x in range(amt):
            cur_state[dest - 1].append(temp.pop())
    res = ""
    for col in cur_state:
        res += col[len(col)-1]   
    print(res)
    
    




if __name__ == "__main__":
    main()