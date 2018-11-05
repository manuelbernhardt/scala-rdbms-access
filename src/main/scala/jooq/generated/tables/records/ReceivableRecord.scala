/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.records


import java.lang.Integer
import java.lang.String
import java.math.BigDecimal

import javax.annotation.Generated

import jooq.generated.tables.Receivable

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record6
import org.jooq.Row6
import org.jooq.impl.UpdatableRecordImpl

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
class ReceivableRecord extends UpdatableRecordImpl[ReceivableRecord](Receivable.RECEIVABLE) with Record6[Integer, Integer, String, BigDecimal, String, String] {

  /**
   * Setter for <code>PUBLIC.RECEIVABLE.ID</code>.
   */
  def setId(value : Integer) : Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>PUBLIC.RECEIVABLE.ID</code>.
   */
  def getId : Integer = {
    val r = get(0)
    if (r == null) null else r.asInstanceOf[Integer]
  }

  /**
   * Setter for <code>PUBLIC.RECEIVABLE.VERSION</code>.
   */
  def setVersion(value : Integer) : Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>PUBLIC.RECEIVABLE.VERSION</code>.
   */
  def getVersion : Integer = {
    val r = get(1)
    if (r == null) null else r.asInstanceOf[Integer]
  }

  /**
   * Setter for <code>PUBLIC.RECEIVABLE.ORDER_ID</code>.
   */
  def setOrderId(value : String) : Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>PUBLIC.RECEIVABLE.ORDER_ID</code>.
   */
  def getOrderId : String = {
    val r = get(2)
    if (r == null) null else r.asInstanceOf[String]
  }

  /**
   * Setter for <code>PUBLIC.RECEIVABLE.PRICE</code>.
   */
  def setPrice(value : BigDecimal) : Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>PUBLIC.RECEIVABLE.PRICE</code>.
   */
  def getPrice : BigDecimal = {
    val r = get(3)
    if (r == null) null else r.asInstanceOf[BigDecimal]
  }

  /**
   * Setter for <code>PUBLIC.RECEIVABLE.CUSTOMER_ID</code>.
   */
  def setCustomerId(value : String) : Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>PUBLIC.RECEIVABLE.CUSTOMER_ID</code>.
   */
  def getCustomerId : String = {
    val r = get(4)
    if (r == null) null else r.asInstanceOf[String]
  }

  /**
   * Setter for <code>PUBLIC.RECEIVABLE.CURRENCY</code>.
   */
  def setCurrency(value : String) : Unit = {
    set(5, value)
  }

  /**
   * Getter for <code>PUBLIC.RECEIVABLE.CURRENCY</code>.
   */
  def getCurrency : String = {
    val r = get(5)
    if (r == null) null else r.asInstanceOf[String]
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------
  override def key : Record1[Integer] = {
    return super.key.asInstanceOf[ Record1[Integer] ]
  }

  // -------------------------------------------------------------------------
  // Record6 type implementation
  // -------------------------------------------------------------------------

  override def fieldsRow : Row6[Integer, Integer, String, BigDecimal, String, String] = {
    super.fieldsRow.asInstanceOf[ Row6[Integer, Integer, String, BigDecimal, String, String] ]
  }

  override def valuesRow : Row6[Integer, Integer, String, BigDecimal, String, String] = {
    super.valuesRow.asInstanceOf[ Row6[Integer, Integer, String, BigDecimal, String, String] ]
  }
  override def field1 : Field[Integer] = Receivable.RECEIVABLE.ID
  override def field2 : Field[Integer] = Receivable.RECEIVABLE.VERSION
  override def field3 : Field[String] = Receivable.RECEIVABLE.ORDER_ID
  override def field4 : Field[BigDecimal] = Receivable.RECEIVABLE.PRICE
  override def field5 : Field[String] = Receivable.RECEIVABLE.CUSTOMER_ID
  override def field6 : Field[String] = Receivable.RECEIVABLE.CURRENCY
  override def component1 : Integer = getId
  override def component2 : Integer = getVersion
  override def component3 : String = getOrderId
  override def component4 : BigDecimal = getPrice
  override def component5 : String = getCustomerId
  override def component6 : String = getCurrency
  override def value1 : Integer = getId
  override def value2 : Integer = getVersion
  override def value3 : String = getOrderId
  override def value4 : BigDecimal = getPrice
  override def value5 : String = getCustomerId
  override def value6 : String = getCurrency

  override def value1(value : Integer) : ReceivableRecord = {
    setId(value)
    this
  }

  override def value2(value : Integer) : ReceivableRecord = {
    setVersion(value)
    this
  }

  override def value3(value : String) : ReceivableRecord = {
    setOrderId(value)
    this
  }

  override def value4(value : BigDecimal) : ReceivableRecord = {
    setPrice(value)
    this
  }

  override def value5(value : String) : ReceivableRecord = {
    setCustomerId(value)
    this
  }

  override def value6(value : String) : ReceivableRecord = {
    setCurrency(value)
    this
  }

  override def values(value1 : Integer, value2 : Integer, value3 : String, value4 : BigDecimal, value5 : String, value6 : String) : ReceivableRecord = {
    this.value1(value1)
    this.value2(value2)
    this.value3(value3)
    this.value4(value4)
    this.value5(value5)
    this.value6(value6)
    this
  }

  /**
   * Create a detached, initialised ReceivableRecord
   */
  def this(id : Integer, version : Integer, orderId : String, price : BigDecimal, customerId : String, currency : String) = {
    this()

    set(0, id)
    set(1, version)
    set(2, orderId)
    set(3, price)
    set(4, customerId)
    set(5, currency)
  }
}
