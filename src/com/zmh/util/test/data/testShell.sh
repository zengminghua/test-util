#!/usr/bin/env bash

touch test.txt

echo "我是内容哈哈哈" > test.txt
echo $1 >> test.txt
echo $2 >> test.txt
echo "上面是入参" >> test.txt