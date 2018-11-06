package jooq

import java.sql.Connection
import java.util.Optional

import javax.sql.DataSource
import org.jooq.{DSLContext, SQLDialect}
import org.jooq.impl.DSL

import scala.util.control.ControlThrowable

  object JOOQDatabaseAccess {

    def query[A](block: DSLContext => A)(implicit ds: DataSource): A = {
      JOOQDatabaseAccess.withConnection(ds, autocommit = true) { connection =>
        val sql = DSL.using(connection, SQLDialect.H2)
        block(sql)
      }
    }

    def transaction[A](block: DSLContext => A)(implicit ds: DataSource): A = {
      JOOQDatabaseAccess.withTransaction(ds) { connection =>
        val sql = DSL.using(connection, SQLDialect.H2)
        block(sql)
      }
    }

    def withConnection[A](ds: DataSource, autocommit: Boolean)(block: Connection => A): A = {
      val connection = ds.getConnection
      try {
        connection.setAutoCommit(autocommit)
        val result = block(connection)
        result
      } catch {
        case e: Throwable =>
          connection.close()
          throw e
      } finally {
        connection.close()
      }
    }

    def withTransaction[A](ds: DataSource)(block: Connection => A): A = {
      withConnection(ds, autocommit = false) { connection =>
        try {
          val r = block(connection)
          connection.commit()
          r
        } catch {
          case e: ControlThrowable =>
            connection.commit()
            throw e
          case e: Throwable =>
            connection.rollback()
            throw e
        }
      }
    }

    // implicit conversions to make this more scala-friendly

    implicit def bigDecimalConversion(d: BigDecimal): java.math.BigDecimal = d.bigDecimal

    implicit def optionalConversion[A](o: Optional[A]): Option[A] = if(o.isPresent) Some(o.get()) else None

}
