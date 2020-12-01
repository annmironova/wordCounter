package com.wordcounter
import org.apache.spark.{SparkConf, SparkContext}

object wordCounter {
  def main(args: Array[String]) = {
    // создание SparkConf и SparkContext
    val conf = new SparkConf().setAppName("WordCounter")
    val sc = new SparkContext(conf)
    // чтение файла для подсчёта слов в нем и создание RDD
    val file = sc.textFile(args(0))
    // файл, в котором будет сохранен результат
    val resultFile = args(1)
    // деление текста на слова
    val words = file.flatMap(text => text.split(" "))
    // подсчёт
    val count = words.map(oneWord => (oneWord, 1))
      .reduceByKey { case (x, y) => x + y }
    // сохранение результата
    count.saveAsTextFile(resultFile)
    // остановка SparkContext
    sc.stop
  }

  def exe(input: String, output: String, master: Option[String] = Some("loсal")): Unit = {
    val sc = {
      val conf = new SparkConf().setAppName("Spark WordCount")
      for (m <- master) {
        conf.setMaster(m)
      }
      new SparkContext(conf)
    }
    val file = sc.textFile(input)
    val resultFile = output
    val words = file.flatMap(text => text.split(" "))
    val count = words.map(oneWord => (oneWord, 1))
      .reduceByKey { case (x, y) => x + y }
    count.saveAsTextFile(resultFile)
    sc.stop
  }
}
