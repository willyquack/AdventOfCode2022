def main():
    f = open("./large.txt")
    input = f.readlines()
    sum = 0
    cache = []
    for line in input:
        cache.append(line)
        if len(cache) == 3:
            sum += get_sum_of_group(cache)
            cache = []
        
    print(sum)

def get_sum_of_group(cache):
    for x in cache[0]:
        if cache[1].__contains__(x) and cache[2].__contains__(x):
            return priority(x)       

def priority(c):
    x = ord(c)
    if x > 96:
        return (x - 96)
    return (x - 38)




if __name__ == "__main__":
    main()