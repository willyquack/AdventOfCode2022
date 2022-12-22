import re

def main():
    f = open("./large.txt")
    input = f.readlines()
    sum = 0
    for line in input:
        if overlaps(line):
            sum += 1
    print(len(input) - sum)


def overlaps(line):
    line = re.findall(r'[0-9][0-9][0-9]|[0-9][0-9]|[0-9]',line)
    sub1 = [int(line[0]),int(line[1])]
    sub2 = [int(line[2]),int(line[3])]
    sub1.sort()
    sub2.sort()
    print(sub1 + [" "] + sub2)
    return (sub1[0] < sub2[0] and sub1[1] < sub2[0]) or (sub1[0] > sub2[1] and sub1[1] > sub2[1])
    



if __name__ == "__main__":
    main()