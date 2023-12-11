package org.eamonnh.aoc23

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Ex5b extends App{

  val fileName = "input/Ex5Input.txt"

  val input = Source.fromFile(fileName).getLines.toList

  val mapsList: mutable.ListBuffer[Map] = ListBuffer.empty
  val seeds: List[Long] = input.head.split(": ").last.split(' ').toList.map(_.toLong)

  val allSeeds: ListBuffer[Long] = ListBuffer.empty
  seeds.zipWithIndex.foreach{
    case (num, id) => {
      if((id%2) == 0){
        for(i <- num until num + seeds(id+1)){
          allSeeds.addOne(i)
        }
      }
    }}

  makeMap()

  val result = allSeeds.map(seed => {
    var n = seed
    mapsList.foreach(m => {
      var done = false
      m.minimaps.foreach(min => {
        if (n >= min.sourceRangeStart && n < min.sourceRangeStart + min.rangeLength && !done) {
          n = min.destRangeStart + n - min.sourceRangeStart
          done = true
        }
      })
    })
    n
  }).min
  println(result)

  def makeMap(): Unit = {
    input.zipWithIndex.foreach{
      case(line, i) => {
        if(!line.isBlank && line.toList.head.isDigit && i != 0 && !input(i-1).toList.head.isDigit){
          var m = Map(ListBuffer.empty)
          var num = i
          while(!input(num).isBlank && input.length > num + 1){
            m.minimaps.addOne(toMiniMap(input(num).split(' ')))
            num += 1
          }
          mapsList.addOne(m)
        }
      }}
  }

  def toMiniMap(strings: Array[String]): miniMap = miniMap(strings.head.toLong, strings(1).toLong, strings(2).toLong)


}

case class Map (minimaps: ListBuffer[miniMap]) {



}

case class miniMap(destRangeStart: Long, sourceRangeStart: Long, rangeLength: Long)