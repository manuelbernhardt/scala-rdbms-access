/*
 * This file is generated by jOOQ.
 */
package jooq.generated


import javax.annotation.Generated

import jooq.generated.tables.Receivable

import org.jooq.Index
import org.jooq.OrderField
import org.jooq.impl.Internal

import scala.Array


/**
 * A class modelling indexes of tables of the <code>PUBLIC</code> schema.
 */
@Generated(
  value = Array(
    "http://www.jooq.org",
    "jOOQ version:3.11.5"
  ),
  comments = "This class is generated by jOOQ"
)
object Indexes {

  // -------------------------------------------------------------------------
  // INDEX definitions
  // -------------------------------------------------------------------------

  val PRIMARY_KEY_2 = Indexes0.PRIMARY_KEY_2

  // -------------------------------------------------------------------------
  // [#1459] distribute members to avoid static initialisers > 64kb
  // -------------------------------------------------------------------------

  private object Indexes0 {
    val PRIMARY_KEY_2 : Index = Internal.createIndex("PRIMARY_KEY_2", Receivable.RECEIVABLE, Array[OrderField [_] ](Receivable.RECEIVABLE.ID), true)
  }
}
