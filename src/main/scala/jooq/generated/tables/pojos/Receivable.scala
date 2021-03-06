/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.pojos


import java.io.Serializable
import java.lang.Integer
import java.lang.String
import java.lang.StringBuilder
import java.math.BigDecimal

import javax.annotation.Generated

import scala.Array


/**
 * This class is generated by jOOQ.
 */
@Generated(
  value = Array(
    "http://www.jooq.org",
    "jOOQ version:3.11.5"
  ),
  comments = "This class is generated by jOOQ"
)
class Receivable(
    private var id : Integer
  , private var version : Integer
  , private var orderId : String
  , private var price : BigDecimal
  , private var customerId : String
  , private var currency : String
) extends Serializable {

  def this() = {
    this(null, null, null, null, null, null)
  }

  def this (value : Receivable) = {
    this(
        value.id
      , value.version
      , value.orderId
      , value.price
      , value.customerId
      , value.currency
    )
  }

  def getId : Integer = {
    this.id
  }

  def setId(id : Integer) : Unit = {
    this.id = id
  }

  def getVersion : Integer = {
    this.version
  }

  def setVersion(version : Integer) : Unit = {
    this.version = version
  }

  def getOrderId : String = {
    this.orderId
  }

  def setOrderId(orderId : String) : Unit = {
    this.orderId = orderId
  }

  def getPrice : BigDecimal = {
    this.price
  }

  def setPrice(price : BigDecimal) : Unit = {
    this.price = price
  }

  def getCustomerId : String = {
    this.customerId
  }

  def setCustomerId(customerId : String) : Unit = {
    this.customerId = customerId
  }

  def getCurrency : String = {
    this.currency
  }

  def setCurrency(currency : String) : Unit = {
    this.currency = currency
  }

  override def toString : String = {
    val sb = new StringBuilder("Receivable (")

    sb.append(id)
    sb.append(", ").append(version)
    sb.append(", ").append(orderId)
    sb.append(", ").append(price)
    sb.append(", ").append(customerId)
    sb.append(", ").append(currency)

    sb.append(")")
    return sb.toString
  }
}
