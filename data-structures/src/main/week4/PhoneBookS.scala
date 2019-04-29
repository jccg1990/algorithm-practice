
import java.util.StringTokenizer

import scala.collection.mutable._

class PhoneBookS {
  private lazy val in: FastScannerS = new FastScannerS
  private val intHash = new IntegerHashTable

  def processQueries(): Unit = {
    val queryCount = in.nextInt

    for (_ <- 0 until queryCount) {
      processQuery(readQuery)
    }
  }

  private def readQuery = {
    val typ = in.next
    val number = in.nextInt
    if (typ == "add") {
      val name = in.next
      new PhoneBookS.Query(typ, name, number)
    }
    else new PhoneBookS.Query(typ, number)
  }

  private def processQuery(query: PhoneBookS.Query): Unit = {
    if (query.typ == "add") {
      intHash.add(new Contact(query.name, query.number))
    } else if (query.typ == "del") {
      intHash.remove(query.number)
    } else {
      println(intHash.find(query.number))
    }
  }

  class Contact(var name: String, val number: Int)

  class IntegerHashTable {

    private val A = new Array[ArrayBuffer[Contact]](100000)
    private val p = 10000019
    private val a = getRandom(1, p - 1)
    private val b = getRandom(0, p - 1)

    private def getRandom(start: Int, end: Int) = {
      val rnd = new scala.util.Random
      start + rnd.nextInt((end - start) + 1)
    }

    def add(contact: Contact): Unit = {
      val l = getList(contact.number)
      val i = l.indexWhere(c => c.number == contact.number)

      if (i > -1) l(i) = contact
      else l += contact
    }

    private def getList(number: Int): ArrayBuffer[Contact] = {
      val h = ha(number)
      if (A(h) == null) A(h) = ArrayBuffer[Contact]()

      A(h)
    }

    private def ha(number: Int): Int = {
      (((a * number.toLong + b) % p) % A.length).toInt
    }

    def remove(number: Int): Unit = {
      if (find(number) == "not found") return
      else {
        val l = getList(number)
        val i = l.indexWhere(c => c.number == number)
        l.remove(i)
      }
    }

    def find(number: Int): String = {
      val l = getList(number)
      val i = l.indexWhere(c => c.number == number)

      if (i > -1) l(i).name
      else "not found"
    }
  }

  class FastScannerS {
    lazy val br = io.Source.stdin.getLines
    var st: StringTokenizer = _

    def nextInt: Int = next.toInt

    def next: String = {
      if (st == null || !st.hasMoreTokens) {
        st = new StringTokenizer(br.next())
      }
      st.nextToken
    }
  }

}

object PhoneBookS {
  def main(args: Array[String]): Unit = {
    new PhoneBookS().processQueries()
  }

  class Query {
    var typ: String = _
    var name: String = _
    var number = 0

    def this(typ: String, name: String, number: Int) {
      this()
      this.typ = typ
      this.name = name
      this.number = number
    }

    def this(typ: String, number: Int) {
      this()
      this.typ = typ
      this.number = number
    }
  }

}