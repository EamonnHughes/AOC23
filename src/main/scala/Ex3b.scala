package org.eamonnh.aoc23

import scala.io.Source

object Ex3b extends App {


  val fileName = "input/Ex3Input.txt"
  val inputArray = Source.fromFile(fileName).getLines().mkString

  val lineLength: Int = Math.sqrt(inputArray.length).toInt

  private var seqs: List[Sequence] = List.empty

  var sum = 0

  inputArray.zipWithIndex.foreach {
    case (c, i) =>
      if ((i == 0 || inputArray(i - 1) == '.' || (c == '*' && (inputArray(i-1) != '*')))|| (c.isDigit && !inputArray(i-1).isDigit) && c != '.') {
        var partNumber: String = ""
        var n = 0
        if (inputArray(i).isDigit) {
          while (inputArray(i + n).isDigit) {
            partNumber = partNumber + inputArray(i + n).toString
            n += 1
          }
        } else partNumber = inputArray(i).toString


        val x = i % lineLength
        val y = (i - x) / lineLength

        seqs = Sequence(x, y, partNumber) :: seqs
      }
  }

  seqs.filter(s => {
    s.isGear
  }).foreach(s => {
    var listOfNums: List[Sequence] = List.empty
    seqs.filter(s => s.isNumber).foreach(num => {
      if (s.touches(num)) {
        listOfNums = num :: listOfNums
      }
    })
    if (listOfNums.length == 2) {
      sum += listOfNums.head.str.toInt * listOfNums(1).str.toInt
    }
  })
  println(sum)
}

case class Sequence(
                     x: Int,
                     y: Int,
                     str: String
                   ) {
  def isNumber: Boolean = str.forall(_.isDigit)

  def isGear: Boolean = str == "*"

  def n: Int = str.length

  def touches(seq: Sequence): Boolean = {
    ((seq.y == y - 1 || seq.y == y + 1 || seq.y == y) && (seq.x + seq.n >= x) && (seq.x <= x + n))
  }
}