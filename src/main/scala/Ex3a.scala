package org.eamonnh.aoc23

import scala.io.Source

object Ex3a extends App {

  def isConnection(c: Char): Boolean = (c != '.' && !c.isDigit)

  def checkForConnection(i: Int, positions: List[Int]): Boolean = {
    var connected = false
    positions.foreach(p => {
      var adjPos: List[Int] = List.empty

      var x = (p%lineLength)
      var y = (p-x)/lineLength

      if (x > 0) {
        if(isConnection(inputArray(p-1))) connected = true
        if(y > 0 && isConnection(inputArray(p-(lineLength+1)))) connected = true
        if(y+1 < lineLength && isConnection(inputArray(p+(lineLength-1)))) connected = true
      }

      if (x + 1 < lineLength) {
        if (isConnection(inputArray(p + 1))) connected = true
        if (y > 0 && isConnection(inputArray(p - (lineLength - 1)))) connected = true
        if (y + 1 < lineLength && isConnection(inputArray(p + (lineLength + 1)))) connected = true
      }

      if (y > 0 && isConnection(inputArray(p - lineLength))) connected = true
      if (y + 1 < lineLength && isConnection(inputArray(p + lineLength))) connected = true

      adjPos.foreach(a => {if(inputArray(a) != '.') {connected = true}})
    })
    connected
  }

  val fileName = "input/Ex3Input.txt"
  val inputArray = Source.fromFile(fileName).getLines().mkString

  val lineLength: Int = Math.sqrt(inputArray.length).toInt

  var sum = 0

  inputArray.zipWithIndex.foreach{
    case (c, i) => {
      if(((i == 0 || !inputArray(i-1).isDigit)) && c.isDigit) {
        var connected = false
        var positions: List[Int] = List.empty

        var partNumber: String = ""
        var n = 0
        while(inputArray(i+n).isDigit) {
          positions = (i + n) :: positions
          partNumber = partNumber+inputArray(i+n).toString
          n+=1
        }

        if(checkForConnection(i, positions)){
          sum += partNumber.toInt
        }

      }
    }
  }
  println(sum)
}
