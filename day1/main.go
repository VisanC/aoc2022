package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func getMax(slice []int) (m int, i int) {
	m = 0
	i = 0
	for index, e := range slice {
		if e > m {
			m = e
			i = index
		}
	}
	return
}

func main() {

	f, err := os.Open("input.txt")
	if err != nil {
		print(err.Error())
	}
	scanner := bufio.NewScanner(f)
	values := make([]int, 1)
	index := 0
	for scanner.Scan() {
		if len(scanner.Text()) < 1 {
			index++
		} else {
			if len(values) > index {
				a, err := strconv.Atoi(scanner.Text())
				if err == nil {
					values[index] += a
				}
			} else {
				values = append(values, 0)
				a, err := strconv.Atoi(scanner.Text())
				if err == nil {
					values[index] += a
				}
			}
		}
	}
	for _, element := range values {
		fmt.Printf("%d\n", element)
	}

	max1, max1i := getMax(values)
	values[max1i] = 0
	max2, max2i := getMax(values)
	values[max2i] = 0
	max3, _ := getMax(values)
	fmt.Printf("%d\n", max1+max2+max3)

}
