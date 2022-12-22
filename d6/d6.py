def main():
    switch = 1
    if switch == 0:
        f = open("./small.txt")
    else:
        f = open("./large.txt")
    line = f.readline()
    for x in range(14,len(line) - 1):
        start = x - 14
        if not are_diff(line[start:x]):
            break
    print(x)

def are_diff(str):
    for x in range(len(str) - 1):
        for c in str:
            if num_occur(str,c) > 1:
                return True
    return False

def num_occur(str,c):
    count = 0
    for ch in str:
        if ch == c:
            count += 1
    return count   


if __name__ == "__main__":
    main()