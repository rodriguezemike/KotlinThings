package com.example.algs_ds

interface Heap<T>{
    fun insert(value : T) : T
    fun constrain() : T
    fun swap(posOne : Int, posTwo : Int)
    fun insertMultiple(values : Collection<T>){
        for (value in values){
            this.insert(value)
        }
    }

}

abstract class BaseIntListHeap<Int> : Heap<Int>{
    var nodes = mutableListOf<Int>()
    fun getFirstElement() : Int{
        return this.nodes[0]
    }
    fun pop() : Int{
        val toReturn = this.nodes[0]
        this.nodes[0] = this.nodes[-1]
        this.nodes = this.nodes.subList(0,this.nodes.size-1)
        this.constrain()
        return toReturn
    }
    override fun swap(posOne: kotlin.Int, posTwo: kotlin.Int) {
        val tmp = this.nodes[posOne]
        this.nodes[posOne] = this.nodes[posTwo]
        this.nodes[posTwo] = tmp
    }
}

class MinHeap : BaseIntListHeap<Int>(){
    fun min():Int{
        return super.getFirstElement()
    }

    override fun insert(value: Int):Int{
        this.nodes.add(value)
        var index = this.nodes.size - 1
        while (index > 0){
            val parentIndex = (index - 1) / 2
            val parentValue = this.nodes[parentIndex]
            if ( value < parentValue){
                this.nodes[index] = value
                this.nodes[parentIndex] = parentValue
                index = parentIndex
            } else {
                return index
            }
        }
        return index
    }

    override fun constrain(): Int {
        var index = 0
        val size = this.nodes.size - 1
        while (index < size){
            val leftChildIndex = index * 2 + 1
            val rightChildIndex = index * 2 + 2
            if(this.nodes[index] > this.nodes[leftChildIndex]){
                this.swap(index, leftChildIndex)
                index = leftChildIndex
            } else if (this.nodes[index] > this.nodes[rightChildIndex]){
                this.swap(index, rightChildIndex)
                index = rightChildIndex
            } else{
                return index
            }

        }
        return index
    }
}
class MaxHeap : BaseIntListHeap<Int>() {
    override fun insert(value: Int): Int{
        this.nodes.add(value)
        var index = this.nodes.size - 1
        while (index > 0){
            val parentIndex = (index - 1) / 2
            val parentValue = this.nodes[parentIndex]
            if (value > parentValue){
                this.nodes[index] = value
                this.nodes[parentIndex] = parentValue
                index = parentIndex
            } else{
                return index
            }
        }
        return index
    }
    override fun constrain() : Int{
        var index = 0
        val size = this.nodes.size - 1
        while (index < size) {
            val leftChildIndex = index * 2 + 1
            val rightChildIndex = index * 2 + 2
            if (this.nodes[index] < this.nodes[leftChildIndex]) {
                this.swap(index, leftChildIndex)
                index = leftChildIndex
            } else if (this.nodes[index] < this.nodes[rightChildIndex]) {
                this.swap(index, leftChildIndex)
                index = rightChildIndex
            } else {
                return index

            }
        }
        return index
    }
    fun max():Int{
        return super.getFirstElement()
    }
}

