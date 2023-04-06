package com.goku.ghostify.nlp.spark

import com.goku.ghostify.util.SentenceExtractor
import com.johnsnowlabs.nlp.Annotation
import com.johnsnowlabs.nlp.AnnotatorType.DOCUMENT
import com.johnsnowlabs.nlp.annotators.common.SentenceSplit
import org.apache.spark.ml.util.{DefaultParamsReadable, Identifiable}

class SentenceDetector(override val uid: String)
    extends AnnotatorModel[SentenceDetector] with HasSimpleAnnotate[SentenceDetector] {

  override val outputAnnotatorType: AnnotatorType = DOCUMENT

  override val inputAnnotatorTypes: Array[AnnotatorType] = Array(DOCUMENT)

  def this() = this(Identifiable.randomUID("SentenceDetector"))

  def annotate(annotations: Seq[Annotation]): Seq[Annotation] = {
    val docs = annotations.map(_.result)
    docs.flatMap { doc =>
      val sentences = SentenceExtractor.split(doc)
      SentenceSplit.pack(sentences)
    }
  }
}

object SentenceDetector extends DefaultParamsReadable[SentenceDetector]
