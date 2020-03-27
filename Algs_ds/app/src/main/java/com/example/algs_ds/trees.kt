package com.example.algs_ds

import kotlin.*

data class Node<T>(var value: T?=null, var right:BinaryTree<T>?=null, var left:BinaryTree<T>?=null){

    override fun toString(): String {
        return "Node ${this.value.toString()}"
    }
}

interface BinaryTree<T>{
    var node: Node<T>?
    fun insert(value: T):Node<T>?
    fun insert(values:List<T>, index: Int = 0):Node<T>?
    fun insertMultiple(values: Collection<T>){
        for(value in values){
            this.insert(value)
        }
    }
    fun inorderTraversal(binTree : BinaryTree<T>?):List<T>{
        if(binTree == null || binTree.node == null){
            var toReturn : List<T> = emptyList()
            return toReturn
        }
        var left_traversal = this.inorderTraversal(this.node?.left)
        var value : List<T>  = listOf(node?.value as T)
        var right_traversal = this.inorderTraversal(this.node?.right)
        return left_traversal.plus(value).plus(right_traversal)
    }
    fun postOrderTraversal(binTree : BinaryTree<T>?):List<T>{
        if(binTree?.node == null){
            var toReturn : List<T> = emptyList()
            return toReturn
        }
        var left_traversal = this.postOrderTraversal(this.node?.left)
        var value : List<T> = listOf(node?.value as T)
        var right_traversal = this.postOrderTraversal(this.node?.right)
        return left_traversal.plus(right_traversal).plus(value)
    }
    fun preOrderTraersal(binTree : BinaryTree<T>?): List<T>{
        if(binTree == null || binTree.node == null){
            var toReturn : List<T> = emptyList()
            return toReturn
        }
        var left_traversal = this.preOrderTraersal(node?.left)
        var right_traversal = this.preOrderTraersal(node?.right)
        var value : List<T> = listOf(node?.value as T)
        return value.plus(left_traversal).plus(right_traversal)
    }
    fun numNodes(bst: BinaryTree<T>) : Int{
        return this.preOrderTraersal(bst).size
    }
    fun depth(bst:BinaryTree<T>?) : Int{
        if(bst == null){
            return 0
        }
        return kotlin.math.max(this.depth(this.node?.left), this.depth(this.node?.right))
    }
    fun isBalanced(binTree : BinaryTree<T>?) : Boolean{
        if (binTree == null){
            return true
        }
        return this.depth(this.node?.left) == this.depth(this.node?.right)
    }
}

class BaseBinarytree(override var node: Node<Int>?=null): BinaryTree<Int> {
    override fun insert(values: List<Int>, index: Int): Node<Int>? {
        var size :Int = values.size
        if (index < size){
            val tempNode: Node<Int> = Node(value=values[index])
            tempNode.value = values[index]
            if(this.node == null){
                this.node = tempNode
            }
            var leftTree = BaseBinarytree(node=this.insert(values, index*2+1))
            var rightTree = BaseBinarytree(node=this.insert(values,index*2+2))
            this.node?.left = leftTree
            this.node?.right = rightTree
            return tempNode
        }
        return null
    }

    override fun insert(value: Int): Node<Int> {
        var local = Node<Int>(value=value)
        if(this.node == null){
            this.node = local
            this.node?.left = BaseBinarytree()
            this.node?.right = BaseBinarytree()
        }
        else if(value > this.node!!.value!!){
            var right: BinaryTree<Int>? = this.node!!.right as BaseBinarytree
            if(right?.node != null){
                right?.insert(value)
                this.node!!.right = right
            }
            else{
                right?.node = local
                this.node!!.right = right
            }
        }else{
            var left: BinaryTree<Int>? = this.node!!.left as BaseBinarytree
            if(left?.node !=null){
                left?.insert(value)
                this.node!!.left = left
            }
            else{
                left?.node = local
                this.node!!.left = left
            }
        }
        return local
    }
}

