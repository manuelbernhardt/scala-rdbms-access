/*
 * This file is generated by jOOQ.
 */
package jooq.generated


import java.util.ArrayList
import java.util.Arrays
import java.util.List

import javax.annotation.Generated

import jooq.generated.tables.Receivable

import org.jooq.Catalog
import org.jooq.Table
import org.jooq.impl.SchemaImpl

import scala.Array


object Public {

  /**
   * The reference instance of <code>PUBLIC</code>
   */
  val PUBLIC = new Public
}

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
class Public extends SchemaImpl("PUBLIC", DefaultCatalog.DEFAULT_CATALOG) {

  override def getCatalog : Catalog = DefaultCatalog.DEFAULT_CATALOG

  override def getTables : List[Table[_]] = {
    val result = new ArrayList[Table[_]]
    result.addAll(getTables0)
    result
  }

  private def getTables0(): List[Table[_]] = {
    return Arrays.asList[Table[_]](
      Receivable.RECEIVABLE)
  }
}
