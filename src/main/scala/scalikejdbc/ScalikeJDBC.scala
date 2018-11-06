package scalikejdbc

import scalikejdbc._

/**
  * Impressions:
  * - documentation / examples on the page somewhat unclear. There's no link between the examples on the page and the linked specs
  * - the `update().apply()` / `execute().apply()` syntax at the end of each statement is somewhat odd
  * - result mapping needs to be done by hand
  * - a bit of an odd syntax / boilerplate to have to explicitly call the mapping every time
  * withSQL { ... }.map(r => Receivable(r)).list.apply()
  * - only one statement per withSQL { } block is possible (no multiple inserts in the same block)
  * - inconsistent DSL syntax
  *   - select(...).from(...).where(gt(r.field, 100)
  *   - select(...).from(...).where.eq(r.field, 100)
  * - no autocomplete on column names in IDEA (it's a macro / implicit / ...)
  */
object ScalikeJDBC extends App {

  // initialize JDBC driver & connection pool
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:hello", "user", "pass")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession

  // create schema

  sql"""
    create table receivable (
      id serial not null primary key,
      version int not null default 0,
      order_id varchar(128) not null,
      price decimal not null,
      customer_id varchar(128) not null,
      currency varchar(3)
    )
  """.execute.apply()


  // insert some values
  withSQL {
    val r = Receivable.column
    insert.into(Receivable).namedValues(
      r.orderId -> "foo0",
      r.price -> BigDecimal(128.94),
      r.customerId -> "bar0"
    )
  }.update().apply()
  withSQL {
    val r = Receivable.column
    insert.into(Receivable).namedValues(
      r.orderId -> "foo1",
      r.price -> BigDecimal(28.14),
      r.customerId -> "bar1"
    )
  }.update().apply()


  // simple query with a condition

  // this does not seem to be possible with the DSL
  //  val receivables = withSQL {
  //    select.from(Receivable).where(r.price.gt(BigDecimal(100)))
  //  }

  import SQLSyntax.{gt, lt, eq}

  val r = Receivable.syntax("r")
  val expensiveReceivables: List[Receivable] = withSQL {
    select.from(Receivable as r).where(gt(r.price, BigDecimal(100)))
  }.map(r => Receivable(r)).list.apply()

  println(expensiveReceivables)

  // option

  val cheapReceivable: Option[Receivable] = withSQL {
    select.from(Receivable as r).where(lt(r.price, BigDecimal(100)))
  }.map(r => Receivable(r)).first().apply()

  println(cheapReceivable)

  // optimistic locking example

  val bar1Receivable: Receivable = withSQL {
    select.from(Receivable as r).where.eq(r.customerId, "bar1")
  }.map(r => Receivable(r)).first().apply().get

  // do something assuming the values just retrieved, such as calculating a new price
  val newPrice = bar1Receivable.price - 10

  // update using version
  val updateResult = withSQL {
    update(Receivable as r)
      .set(r.price -> newPrice)
      .where
      .eq(r.customerId, "bar1")
      .and
      .eq(r.version, 0)
  }.map(r => r.underlying).update().apply()

  if (updateResult == 1) {
    // everything went according to plan
    println(s"Updated successful")
  } else {
    println("Update failed!")
  }

  // SELECT FOR UPDATE

  // not supported

  //
  // CRUD
  //


  import skinny.orm._, feature._
  import org.joda.time._

  // Definition

  sql"create table member (id serial, name varchar(64), created_at timestamp)".execute.apply()

  case class Member(id: Long, name: Option[String], createdAt: DateTime)
  object Member extends SkinnyCRUDMapper[Member] {
    override lazy val defaultAlias = createAlias("m")
    override def extract(rs: WrappedResultSet, n: ResultName[Member]): Member = new Member(
      id        = rs.get(n.id),
      name      = rs.get(n.name),
      createdAt = rs.get(n.createdAt))
  }

  // create
  Member.createWithAttributes('name -> "Alice", 'createdAt -> DateTime.now)

  // finders
  val member: Option[Member] = Member.findById(1)
  println(member)

  // update
  Member.updateById(1).withAttributes('name -> "Bob")

  // delete
  Member.deleteById(1)

}

case class Receivable(id: Long, version: Int, orderId: String, price: BigDecimal, customerId: String, currency: Option[String])

object Receivable extends SQLSyntaxSupport[Receivable] {
  def apply(r: WrappedResultSet): Receivable = Receivable(
    r.long(1),
    r.int(2),
    r.string(3),
    r.bigDecimal(4),
    r.string(5),
    Option(r.string(6)).filterNot(_.trim.isEmpty)
  )
}