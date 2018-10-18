package week1

import java.io.{BufferedReader, IOException, InputStreamReader}

object CheckBrackets {
  @throws[IOException]
  def main(args: Array[String]): Unit = {
    val input_stream = new InputStreamReader(System.in)
    val reader = new BufferedReader(input_stream)
    val text = reader.readLine
    var openingBracketsStack = List[Bracket]()

    for (i <- 0 until text.length) {
      val next = text.charAt(i)
      if (next == '(' || next == '[' || next == '{') {
        openingBracketsStack = new Bracket(next, i) :: openingBracketsStack
      }

      if (next == ')' || next == ']' || next == '}') {
        openingBracketsStack match {
          case Nil =>
            println(i + 1)
            return
          case _ =>
            val h = openingBracketsStack.head
            openingBracketsStack = openingBracketsStack.tail
            if (!(h matches next)) {
              println(i + 1)
              return
            }
        }
      }
    }

    openingBracketsStack match {
      case Nil => println("Success")
      case x :: _ => println(x.position + 1)
    }
  }
}

class Bracket(val kind: Char, val position: Int) {
  def matches(c: Char): Boolean = {
    if (this.kind == '[' && c == ']') return true
    if (this.kind == '{' && c == '}') return true
    if (this.kind == '(' && c == ')') return true
    false
  }
}
