package com.example.algs_ds

interface abstractNode<T>{
    var value : T?
    override fun toString() : String
}
abstract class BaseNode<T> : abstractNode<T>{
    override fun toString(): String {
        return this.value.toString()
    }
}