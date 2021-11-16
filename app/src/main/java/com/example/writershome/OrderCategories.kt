package com.example.writershome

 class OrderCategories {
    private var order_type: String = ""
    private var order_number: String = ""
    private var color: String = ""



    constructor()

    constructor(
        order_type1: String,
        order_number1: String,
        color1: String
    ) {
        this.order_type = order_type1
        this.order_number = order_number1
        this.color =color1


    }

    fun getorder_type(): String {
        return order_type
    }

    fun setorder_type(order_type1: String) {
        this.order_type = order_type1
    }

    fun getColor(): String {
        return color
    }

    fun setColor(color1: String) {
        this.color = color1
    }

    fun getorder_number(): String {
        return order_number
    }

    fun setorder_number(order_number1: String) {
        this.order_number = order_number1
    }


}
