package com.wordcounter

import java.nio.file.{Files, Paths}
import java.io.File

import org.apache.commons.codec.Charsets
import org.scalatest._

import scala.io.Source



class wordCounterTest extends FlatSpec with Matchers{
  "A WordCounts" must  "count words in the document correctly" in {
    // создание временного каталога и получние траектории
    val path = Files.createTempDirectory(null).toAbsolutePath
    val pathStr = path.toString
    // создание объекта для файла который находится в временном каталоге
    val inputFile = new File(pathStr, "input.txt")
    // создание текстового файла (+кодирование строки в массив байтов)
    Files.write(Paths.get(inputFile.getAbsolutePath), "a b cc b a a a".getBytes(Charsets.UTF_8))
    val outputFile = new File(pathStr, "output")
    val outputDir = outputFile.getAbsoluteFile
    // запуск локально
    wordCounter.exe(
      input = inputFile.getAbsolutePath,
      output = outputFile.getAbsolutePath,
      master = Some("local")
    )
    // получение результата типа String из выходного файла part-0000
    val result = Source.fromFile(new File(outputDir, "part-00000"), "UTF-8").mkString
    inputFile.deleteOnExit()
    // сравнение ожидаемого и фактического результата
    assert(result === "(a,4)\n(b,2)\n(cc,1)\n")
  }
}
