package com.example.writershome

class orderSpecs {

    private var orderID: String = ""
    private var subject:String = ""
    private var taskTitle: String = ""
    private var deadline: String = ""
    private var pay: String = ""


    constructor()

    constructor(
        orderID1: String,
        subject1:String,
        taskTitle1: String,
        deadline1: String,
        pay1: String

    ) {
        this.orderID = orderID1
        this.subject=subject1
        this.taskTitle = taskTitle1
        this.deadline = deadline1
        this.pay = pay1

    }

    fun getOrderID(): String {
        return orderID
    }

    fun setOrderID(orderID1: String) {
        this.orderID = orderID1
    }

    fun getSubject(): String {
        return subject
    }

    fun setSubject(subject1: String) {
        this.subject = subject1
    }

    fun getTaskTitle(): String {
        return taskTitle
    }

    fun setTaskTitle(taskTitle1: String) {
        this.taskTitle = taskTitle1
    }

    fun getDeadline():String{
        return deadline
    }
    fun setDeadline(deadline1: String){
        this.deadline =deadline1
    }


    fun getPay(): String {
        return pay
    }

    fun setPay(pay1: String) {
        this.pay = pay1
    }



}
