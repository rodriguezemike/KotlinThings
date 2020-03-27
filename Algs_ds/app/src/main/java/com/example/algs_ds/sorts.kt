package com.example.algs_ds

import kotlin.random.*
import kotlin.collections.ArrayList
import kotlin.math.floor

fun swap(inputArray:ArrayList<Int>, posOne:Int, posTwo:Int):ArrayList<Int>{
    val temp = inputArray[posOne]
    inputArray[posOne] = inputArray[posTwo]
    inputArray[posTwo] = temp
    return inputArray
}
fun swap(inputArray:List<Int>, posOne:Int, posTwo:Int):List<Int>{
    val tempArrayList = ArrayList(inputArray)
    return swap(tempArrayList, posOne, posTwo).toList()
}

fun merge(leftArray:ArrayList<Int>, rightArray:ArrayList<Int>):ArrayList<Int>{
    val size = leftArray.size + rightArray.size
    println(size)
    val sortedArray = ArrayList<Int>()
    while(leftArray.size !=0 && rightArray.size !=0){
        if(leftArray[0] < rightArray[0]){
            sortedArray.add(leftArray.removeAt(0))
        }else{
            sortedArray.add(rightArray.removeAt(0))
        }
    }
    sortedArray.addAll(leftArray)
    sortedArray.addAll(rightArray)
    return sortedArray
}

fun merge(leftArray: List<Int>, rightArray: List<Int>) : List<Int>{
    return merge(ArrayList<Int>(leftArray), ArrayList<Int>(rightArray)).toList()
}

fun selectionSort(inputArray:ArrayList<Int>):ArrayList<Int>{
    var tempArray = inputArray
    for (x in 0 until inputArray.size){
        var lowestIndex = x
        for(y in lowestIndex until inputArray.size){
            if(inputArray[y] < inputArray[lowestIndex]){
                lowestIndex = y
            }
        }
        tempArray = swap(tempArray, x, lowestIndex)
    }
    return tempArray
}

fun selectionSort(inputArray: List<Int>):List<Int>{
    val tempArray = ArrayList(inputArray)
    return selectionSort(tempArray).toList()
}

fun bubbleSort(inputArray:ArrayList<Int>):ArrayList<Int>{
    var swaps = 1
    var tempArray = inputArray
    while(swaps>0){
        swaps = 0
        for (x in 0 until inputArray.size-1){
            if(inputArray[x] > inputArray[x+1]){
                tempArray = swap(tempArray, x, x+1)
                swaps += 1
            }
        }
    }
    return tempArray
}
fun bubbleSort(inputArray:List<Int>):List<Int>{
    val tempArray = ArrayList(inputArray)
    return bubbleSort(tempArray)
}

fun insertionSort(inputArray:ArrayList<Int>):ArrayList<Int>{
    var tempArray = inputArray
    for (x in 0 until tempArray.size){
        var y = x
        while (y > 0 && tempArray[y-1] > tempArray[y]){
            tempArray = swap(tempArray, y-1, y)
            y -= 1
        }
    }
    return tempArray
}

fun insertionSort(inputArray:List<Int>):List<Int>{
    val tempArray = ArrayList(inputArray)
    return insertionSort(tempArray).toList()
}

fun mergeSort(inputArray:ArrayList<Int>):ArrayList<Int>{
    if(inputArray.size < 2){
        return inputArray
    }
    val midpoint =  floor(inputArray.size/2.0).toInt()
    val leftSubArray = mergeSort(ArrayList(inputArray.slice(0 until midpoint)))
    val rightSubArray = mergeSort(ArrayList(inputArray.slice(midpoint until inputArray.size)))
    val tempArray = merge(leftSubArray,rightSubArray)
    return tempArray
}

fun mergeSort(inputArray:List<Int>):List<Int>{
    val tempArray = ArrayList(inputArray)
    return mergeSort(tempArray).toList()
}
