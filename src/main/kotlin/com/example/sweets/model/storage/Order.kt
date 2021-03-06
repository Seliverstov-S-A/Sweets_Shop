package com.example.sweets.model.storage

import javax.persistence.*
import com.example.sweets.model.storage.Entity as DbEntity

@Entity
@Table(name = "orderrequest")
data class Order(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
        var status: OrderStatus = OrderStatus.WAIT_CONFIRM,

        @Basic
    var products: ArrayList<String> = ArrayList(),

        @ManyToOne @JoinColumn(name = "operator_id")
    val operator: Operator? = null,

        @ManyToOne @JoinColumn(name = "chef_id")
    var chef: Chef? = null,

        @ManyToOne @JoinColumn(name = "client_id")
    var client: Client
) : DbEntity


enum class OrderStatus {
    WAIT_CONFIRM, CONFIRMED, WAIT_WORKING, IN_WORKING, COMPLETE, CANCELED
}