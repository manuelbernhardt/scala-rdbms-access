package jooq

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import JOOQDatabaseAccess._
import jooq.generated.tables.records.ReceivableRecord
import org.jooq.Result
import org.jooq.codegen.GenerationTool

/**
  * Impressions
  *
  * - very well documented
  * - very close to actual SQL syntax
  * - result mapping is automatic (code generation based on the schema)
  * - interop with Scala needs to be taken care of (it isn't out-of-the-box)
  *   - OTOH it works rather well with implicit conversions
  */
object JOOQ extends App {

  // connection pool with HikariCP
  Class.forName("org.h2.Driver")
  val hikariConfig = new HikariConfig()
  hikariConfig.setJdbcUrl("jdbc:h2:mem:hello")
  hikariConfig.setUsername("user")
  hikariConfig.setPassword("pass")

  implicit val ds = new HikariDataSource(hikariConfig)



  // create schema
  withTransaction(ds) { c =>
    val statement = c.createStatement()
    statement.execute("""create table receivable (
                        |      id serial not null primary key,
                        |      version int not null default 0,
                        |      order_id varchar(128) not null,
                        |      price decimal not null,
                        |      customer_id varchar(128) not null,
                        |      currency varchar(3)
                        |)""".stripMargin)
    }

  // JOOQ codegen
  // this should be done only when the schema changes and is typically done by the build tool, not in code
  GenerationTool.main(Array("hello.xml"))

  import generated.Tables._

  // insert some values
  val insertion = transaction { sql =>
    sql.insertInto(RECEIVABLE, RECEIVABLE.ORDER_ID, RECEIVABLE.PRICE, RECEIVABLE.CUSTOMER_ID).values("foo1", BigDecimal(143.23), "bar1").execute()
    sql.insertInto(RECEIVABLE, RECEIVABLE.ORDER_ID, RECEIVABLE.PRICE, RECEIVABLE.CUSTOMER_ID).values("foo2", BigDecimal(23.23), "bar2").execute()
  }


  // simple query with a condition

  val records: Result[ReceivableRecord] = query { sql =>
    sql
      .selectFrom(RECEIVABLE)
      .where(RECEIVABLE.PRICE.gt(BigDecimal(100)))
      .fetch()
  }

  records.forEach(r => println(r))

  // fetching an option

  val cheapest: Option[ReceivableRecord] = query { sql =>
    sql
      .selectFrom(RECEIVABLE)
      .where(RECEIVABLE.PRICE.lt(BigDecimal(100)))
      .fetchOptional()
  }

  println(cheapest)

  // optimistic locking example

  val bar1Receivable: ReceivableRecord = query { sql =>
    sql
      .selectFrom(RECEIVABLE)
      .where(RECEIVABLE.CUSTOMER_ID.eq("bar1"))
      .fetchOne
  }

  // do something assuming the values just retrieved, such as calculating a new price
  val newPrice = BigDecimal(bar1Receivable.getPrice) - 10

  // update using version
  val updateResult = transaction { sql =>
    sql
      .update(RECEIVABLE)
      .set(RECEIVABLE.PRICE, newPrice.bigDecimal)
      .where(RECEIVABLE.CUSTOMER_ID.eq("bar1"))
      .and(RECEIVABLE.VERSION.eq(0)
      ).execute()
  }

  if (updateResult == 1) {
    // everything went according to plan
    println(s"Updated successful")
  } else {
    println("Update failed!")
  }

}
