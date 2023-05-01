import math
n = int(input())
'''
두 사람이 예측한 류재명이 있을 위치는 각 사람의 기준으로 반지름이 r인 원을 그린다.
두 원의 접점의 개수를 구하는 문제
두 사람의 위치간 거리 d, 각 원의 반지름 r1, r2
'''
for _ in range(n):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    d = math.sqrt(pow((x1-x2),2) + pow((y1-y2),2))
    if d == 0:
        if r1 == r2:    # 완전히 겹침
            print(-1)
        else:    # 중심이 같고 반지름이 다름
            print(0)
    else:
        if d < r1+r2 and d>abs(r1-r2):    # 두 점에서 만남
            print(2)
        elif d == r1+r2 or d == abs(r1-r2):
            print(1)
        else:
            print(0)