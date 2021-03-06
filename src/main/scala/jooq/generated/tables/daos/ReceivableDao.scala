/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.daos


import java.lang.Integer
import java.lang.String
import java.math.BigDecimal
import java.util.List

import javax.annotation.Generated

import jooq.generated.tables.Receivable
import jooq.generated.tables.records.ReceivableRecord

import org.jooq.Configuration
import org.jooq.impl.DAOImpl

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
class ReceivableDao(configuration : Configuration) extends DAOImpl[ReceivableRecord, jooq.generated.tables.pojos.Receivable, Integer](Receivable.RECEIVABLE, classOf[jooq.generated.tables.pojos.Receivable], configuration) {

  /**
   * Create a new ReceivableDao without any configuration
   */
  def this() = {
    this(null)
  }

  override protected def getId(o : jooq.generated.tables.pojos.Receivable) : Integer = {
    o.getId
  }

  /**
   * Fetch records that have <code>ID IN (values)</code>
   */
  def fetchById(values : Integer*) : List[jooq.generated.tables.pojos.Receivable] = {
    fetch(Receivable.RECEIVABLE.ID, values:_*)
  }

  /**
   * Fetch a unique record that has <code>ID = value</code>
   */
  def fetchOneById(value : Integer) : jooq.generated.tables.pojos.Receivable = {
    fetchOne(Receivable.RECEIVABLE.ID, value)
  }

  /**
   * Fetch records that have <code>VERSION IN (values)</code>
   */
  def fetchByVersion(values : Integer*) : List[jooq.generated.tables.pojos.Receivable] = {
    fetch(Receivable.RECEIVABLE.VERSION, values:_*)
  }

  /**
   * Fetch records that have <code>ORDER_ID IN (values)</code>
   */
  def fetchByOrderId(values : String*) : List[jooq.generated.tables.pojos.Receivable] = {
    fetch(Receivable.RECEIVABLE.ORDER_ID, values:_*)
  }

  /**
   * Fetch records that have <code>PRICE IN (values)</code>
   */
  def fetchByPrice(values : BigDecimal*) : List[jooq.generated.tables.pojos.Receivable] = {
    fetch(Receivable.RECEIVABLE.PRICE, values:_*)
  }

  /**
   * Fetch records that have <code>CUSTOMER_ID IN (values)</code>
   */
  def fetchByCustomerId(values : String*) : List[jooq.generated.tables.pojos.Receivable] = {
    fetch(Receivable.RECEIVABLE.CUSTOMER_ID, values:_*)
  }

  /**
   * Fetch records that have <code>CURRENCY IN (values)</code>
   */
  def fetchByCurrency(values : String*) : List[jooq.generated.tables.pojos.Receivable] = {
    fetch(Receivable.RECEIVABLE.CURRENCY, values:_*)
  }
}
