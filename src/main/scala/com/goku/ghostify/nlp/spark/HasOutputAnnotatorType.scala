package com.goku.ghostify.nlp.spark

trait HasOutputAnnotatorType {
  type AnnotatorType = String
  val outputAnnotatorType: AnnotatorType
}
