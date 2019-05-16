array = [0, 5, 1, -10, 7, 1, 5, 1, 12]

def solve_n():
    sum = 0
    start = 0
    end = 0
    max = array[0]

    for i in range(0, len(array), 1):
        sum += array[i]

        if sum < 0:
            sum = 0
            start = i + 1
        if sum > max:
            end = i
            max = sum
    print "max sum=%s" % max
    print "start index=%s" % start
    print "end index=%s" % end

if __name__ == '__main__':
    solve_n()