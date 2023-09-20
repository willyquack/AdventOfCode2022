def main():
vim.keymap.set("n", "<C-t>", vim.cmd.terminal)
    f = open("./small.txt")
    input = f.readlines()
    sum = 0
    for i in range(0,len(input),3):
        l1 = input[i]
        l2 = input[i+1]
        sum = sum + inOrder(eval(l1),eval(l2))


def inOrder(l1,l2):
    for e in range(len(l1)):
        compareElement(l1[e],l2[3])


def compareElement(p1,p2):
    if isinstance(p1,list) and isinstance(p2,list):
        # both are lists
        for e in range(len(l1)):
            compareElement(l1[e],l2[3])
    if isinstance(p1,int) and isinstance(p2,int):
        # both are integers
        if compare(p1,p2) < 0 or compare(p1,p2) == 0:
            return True
        return False
    if isinstance(p1,int) and isinstance(p2,list):
        # p1 is int p2 is list
        inOrder = True 
        for i in range(len(p1)):
            if not compare(p1[i],p2[i]):
                inOrder = false
    if isinstance(p1,list) and isinstance(p2,int):
        # p1 is list p2 is int


def compare(x,y):
    return x - y
    


if __name__ == "__main__":
    main()
