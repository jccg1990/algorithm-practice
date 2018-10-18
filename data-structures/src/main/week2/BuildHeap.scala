
import java.io._
import java.util.StringTokenizer

class BuildHeap {
  private var in: FastScanner = null
  private var data: Array[Int] = _
  private var swaps: List[Swap] = Nil

  def siftDown(i: Int): Unit = {
    var minIndex = i
    val l = leftChild(i)
    val r = rightChild(i)

    if (l <= data.length - 1 && data(l) < data(minIndex)) minIndex = l
    if (r <= data.length - 1 && data(r) < data(minIndex)) minIndex = r

    if (i != minIndex) {
      swap(i, minIndex)
      siftDown(minIndex)
    }
  }

  def buildHeap(): Unit = {
    for (i <- (data.length / 2) to 0 by -1) {
      siftDown(i)
    }
  }

  def rightChild(i: Int): Int = 2 * i + 2

  def leftChild(i: Int): Int = 2 * i + 1

  def swap(i: Int, maxIndex: Int) = {
    swaps = swaps :+ new Swap(i, maxIndex)
    val tmp = data(i)
    data(i) = data(maxIndex)
    data(maxIndex) = tmp
  }

  @throws[IOException]
  def solve(): Unit = {
    in = new FastScanner()
    readData()
    buildHeap()
    writeResponse()
  }


  @throws[IOException]
  def readData() = {
    val n = in.nextInt
    data = new Array[Int](n)
    for (i <- 0 until n) {
      data(i) = in.nextInt
    }
  }

  def writeResponse() = {
    println(swaps.size)
    for (swap <- swaps) {
      println(swap.index1 + " " + swap.index2)
    }
  }
}

private class FastScanner(var tokenizer: StringTokenizer = null) {
  private lazy val reader = new BufferedReader(new InputStreamReader(System.in))

  @throws[IOException]
  def nextInt: Int = next.toInt

  @throws[IOException]
  private def next: String = {
    while (tokenizer == null || !tokenizer.hasMoreTokens) {
      tokenizer = new StringTokenizer(reader.readLine)
    }
    tokenizer.nextToken
  }
}

private class Swap(var index1: Int, var index2: Int)

object BuildHeap {
  @throws[IOException]
  def main(args: Array[String]): Unit = new BuildHeap().solve()
}