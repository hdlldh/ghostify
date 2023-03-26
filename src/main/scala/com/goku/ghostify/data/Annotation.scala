package com.goku.ghostify.data

import scala.collection.Map

case class Annotation(
  annotatorType: String,
  begin: Int,
  end: Int,
  result: String,
  metadata: Map[String, String],
  embeddings: Array[Float] = Array.emptyFloatArray
) extends IAnnotation {

  override def equals(obj: Any): Boolean = {
    obj match {
      case annotation: Annotation =>
        this.annotatorType == annotation.annotatorType &&
          this.begin == annotation.begin &&
          this.end == annotation.end &&
          this.result == annotation.result &&
          this.metadata == annotation.metadata &&
          this.embeddings.sameElements(annotation.embeddings)
      case _ => false
    }
  }

  override def toString: String = {
    s"Annotation(type: $annotatorType, begin: $begin, end: $end, result: $result)"
  }

  def getAnnotatorType: String = {
    annotatorType
  }

  def getBegin: Int = {
    begin
  }

  def getEnd: Int = {
    end
  }

  def getResult: String = {
    result
  }

  def getMetadata: Map[String, String] = {
    metadata
  }

}
